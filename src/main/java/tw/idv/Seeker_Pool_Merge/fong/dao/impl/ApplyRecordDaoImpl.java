package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.ApplyRecordShowVo;

public class ApplyRecordDaoImpl implements ApplyRecordDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);
	PreparedStatement pstmt = null;

	@Override
	public int findTotalCount(int memId, String keyWord, int filterNum) {

		String sql = """
				select count(*) from JOB J, company_member C , apply_record A
				where J.JOB_NO = A.JOB_NO AND C.COM_MEM_ID = A.COM_MEM_ID AND MEM_ID = ?
				and( J.JOB_NAME like ? or C.COM_NAME like ? )
				""";

		if (filterNum != 100) { //100就是全部顯示
			sql += " and hire_status = ?";
			return template.queryForObject(sql, Integer.class, memId, "%" + keyWord + "%", "%" + keyWord + "%",
					filterNum);
		} else {
			return template.queryForObject(sql, Integer.class, memId, "%" + keyWord + "%", "%" + keyWord + "%");
		}

	}

	@Override
	public List<ApplyRecordShowVo> findByPage(int memId, int start, int pageSize, String keyWord, int filterNum) {
		String sql = """
				select C.COM_NAME, J.JOB_NO, J.JOB_NAME, A.APPLY_DATE, A.INTER_DATE, A.HIRE_STATUS
				from JOB J, company_member C , apply_record A
				where J.JOB_NO = A.JOB_NO AND C.COM_MEM_ID = A.COM_MEM_ID AND MEM_ID = ?
				and( J.JOB_NAME like ? or C.COM_NAME like ? )
				""";
		ResultSet rs = null;
		try (Connection con = dataSource.getConnection()) {
			// 動態拼接
			StringBuilder sb = new StringBuilder(sql);

			if (filterNum != 100) {
				sb.append(" and hire_status = ?");
			}
			sb.append(" ORDER BY A.APPLY_DATE limit ? , ?;");

			sql = sb.toString();
			pstmt = con.prepareStatement(sql);

			// 給?賦值
			pstmt.setInt(1, memId);
			pstmt.setString(2, "%" + keyWord + "%");
			pstmt.setString(3, "%" + keyWord + "%");
			if (filterNum != 100) {
				pstmt.setInt(4, filterNum);
				pstmt.setInt(5, start);
				pstmt.setInt(6, pageSize);
			} else {
				pstmt.setInt(4, start);
				pstmt.setInt(5, pageSize);
			}

			// 執行SQL
			rs = pstmt.executeQuery();
			List<ApplyRecordShowVo> applyRecords = new ArrayList<ApplyRecordShowVo>();

			//存入applyRecords中
			while (rs.next()) {
				ApplyRecordShowVo applyRecord = new ApplyRecordShowVo();
				applyRecord.setComName(rs.getString("COM_NAME"));
				applyRecord.setJobNo(rs.getInt("JOB_No"));
				applyRecord.setJobName(rs.getString("JOB_NAME"));
				applyRecord.setApplyDate(rs.getDate("APPLY_DATE"));
				applyRecord.setInterDate(rs.getString("INTER_DATE"));

				// 判斷HIRE_STATUS的編號 轉成文字
				String hireStatuString = null;
				switch (rs.getInt("HIRE_STATUS")) {
				case 0:
					hireStatuString = "已投履歷，未有面試";
					break;
				case 1:
					hireStatuString = "已安排面試";
					break;
				case 2:
					hireStatuString = "面試已完成，等待通知";
					break;
				case 3:
					hireStatuString = "企業發面試，待回覆";
					break;
				case 4:
					hireStatuString = "取消面試";
					break;
				case 8:
					hireStatuString = "未錄取";
					break;
				case 9:
					hireStatuString = "錄取成功";
					break;
				}

				applyRecord.setHireStatus(hireStatuString);
				applyRecords.add(applyRecord);
			}
			return applyRecords;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public void cancelInterview(String comName, String jobName, Integer memId) {
		String sql = """
				UPDATE apply_record a SET a.hire_status = 4,  a.inter_date = null
				WHERE mem_id = ?
				and job_no = (select distinct job_no from job where job_name = ? )
				and com_mem_id = (select distinct COM_MEM_ID from company_member where com_name = ? );
				""";
		template.update(sql, memId, jobName, comName);

	}

	@Override
	public String getComEmailByComName(String comName) {
		String sql = "select com_email from company_member where com_name = ?";
		return template.queryForObject(sql, String.class, comName);
	}

	@Override
	public void updateInterviewTime(Integer jobId, Integer memId, String dateTime) {
		String sql = """
				UPDATE apply_record  SET hire_status = 1, inter_date = ? 
				WHERE mem_id = ? and job_no = ?;
				""";
		template.update(sql, dateTime, memId, jobId);
	}
}
