package tw.idv.Seeker_Pool_Merge.jamie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.jamie.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ApplyRecordVo;

public class ApplyRecordDaoImpl implements ApplyRecordDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);
//	PreparedStatement pstmt = null;

	@Override
	public List<ApplyRecordVo> findApplicantsByComMemId(int comMemId) {
		String sql = """
				    SELECT m.MEM_ID, m.MEM_NAME, m.MEM_COLLEGE, m.MEM_DEPARTMENT, j.JOB_NO, j.JOB_NAME, ar.APPLY_DATE, ar.INTER_DATE, ar.HIRE_STATUS
					FROM member m
					JOIN apply_record ar ON m.MEM_ID = ar.MEM_ID
					JOIN job j ON ar.JOB_NO = j.JOB_NO
					WHERE ar.COM_MEM_ID = ?
					ORDER BY ar.APPLY_DATE DESC
				""";

		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, comMemId);

			try (ResultSet rs = pstmt.executeQuery()) {
				List<ApplyRecordVo> applyRecords = new ArrayList<ApplyRecordVo>();

				while (rs.next()) {
					ApplyRecordVo applyRecord = new ApplyRecordVo();
					applyRecord.setMemId(rs.getInt("MEM_ID"));
					applyRecord.setMemName(rs.getString("MEM_NAME"));
					applyRecord.setMemCollege(rs.getString("MEM_COLLEGE"));
					applyRecord.setMemDepartment(rs.getString("MEM_DEPARTMENT"));
					applyRecord.setJobNo(rs.getInt("JOB_NO"));
					applyRecord.setJobName(rs.getString("JOB_NAME"));
					applyRecord.setApplyDate(rs.getDate("APPLY_DATE"));
					applyRecord.setInterDate(rs.getString("INTER_DATE"));
					applyRecord.setHireStatus(rs.getInt("hire_status"));
					applyRecords.add(applyRecord);
				}
				return applyRecords;
			}

		} catch (SQLException e) {
			e.printStackTrace();
//			    return Collections.emptyList();
		}
		return null;
	}

	@Override
	public ApplyRecordVo findMemberById(int memId) {
		try {
			String sql = """
					SELECT MEM_ID, MEM_NAME, MEM_PIC, MEM_COLLEGE, MEM_DEPARTMENT, MEM_ADDRESS, MEM_EMAIL, MEM_BIO, SK_NO, MEM_LANG
					FROM member WHERE MEM_ID = ?
					""";

			ApplyRecordVo member = template.queryForObject(sql,
					new BeanPropertyRowMapper<ApplyRecordVo>(ApplyRecordVo.class), memId);

			return member;

		} catch (EmptyResultDataAccessException e) {
			// 沒有找到符合條件的記錄
			System.out.println("No record found for MEM_ID: " + memId);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

//	@Override
//	public List<ApplyRecordVo> findJobsByComMemId(int comMemId) {
//		String sql = """
//				SELECT JOB_NO, JOB_NAME
//				FROM job
//				WHERE COM_MEM_ID = ? AND JOB_STATUS = 1
//				""";
//
//		List<ApplyRecordVo> jobs = template.query(sql, new BeanPropertyRowMapper<ApplyRecordVo>(ApplyRecordVo.class));
//
//		return jobs;
//	}

	@Override
	public int updateHireStatus(int memId, int comMemId, int jobNo) {
		String sql = """
				UPDATE apply_record SET HIRE_STATUS = 2
				WHERE MEM_ID = ? AND COM_MEM_ID = ? AND JOB_NO = ?
				""";

		int rows = template.update(sql, memId, comMemId, jobNo);

		return rows;
	}

	@Override
	public int cancelInterview(int memId, int comMemId, int jobNo) {
		String sql = """
				UPDATE apply_record
				SET HIRE_STATUS = 4, INTER_DATE = NULL
				WHERE MEM_ID = ? AND COM_MEM_ID = ? AND JOB_NO = ?
				""";
		int rows = template.update(sql, memId, comMemId, jobNo);

		return rows;
	}

}
