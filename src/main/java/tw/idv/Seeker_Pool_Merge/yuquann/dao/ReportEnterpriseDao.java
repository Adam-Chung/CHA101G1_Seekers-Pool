package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ReportEnterpriseVo;

public class ReportEnterpriseDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public int insert(ReportEnterpriseVo vo) {

		String sql = "INSERT INTO report_enterprise (rjt_no,RE_CONTENT,RE_END_TIME,re_status,re_result,re_upload,MEM_ID,COM_MEM_ID,JOB_NO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int rowcount = 0;
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, vo.getRjtNo());
			ps.setString(2, vo.getReContent());
			ps.setDate(3, vo.getReEndTime());
			ps.setInt(4, vo.getReStatus());
			ps.setInt(5, vo.getReResult());
			ps.setString(6, vo.getReUpload());
			ps.setInt(7, vo.getMemId());
			ps.setInt(8, vo.getComMemId());
			ps.setInt(9, vo.getJobNo());

			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;
	}

	public int update(ReportEnterpriseVo vo) {

		String sql = "update report_enterprise set re_status = ? , re_result = ? where re_no = ?;";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, vo.getReStatus());
			ps.setInt(2, vo.getReResult());
			ps.setInt(3, vo.getReNo());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	public ReportEnterpriseVo selectNumber(int reNo) {

		String sql = "SELECT * FROM REPORT_ENTERPRISE "
				+ "LEFT JOIN MEMBER ON REPORT_ENTERPRISE.MEM_ID = MEMBER.MEM_ID  "
				+ "LEFT JOIN COMPANY_MEMBER ON REPORT_ENTERPRISE.COM_MEM_ID = COMPANY_MEMBER.COM_MEM_ID  "
				+ "WHERE RE_NO = ?;";
		
		ReportEnterpriseVo vo = new ReportEnterpriseVo();
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = null;
			ps.setInt(1, reNo);
//				System.out.println("傳入vo的reNo值 : " + vo.getReNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				
				vo.setReNo(rs.getInt("RE_NO"));
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setReStartTime(rs.getDate("RE_START_TIME"));
				vo.setReStatus(rs.getInt("RE_STATUS"));
				vo.setReResult(rs.getInt("RE_RESULT"));
				vo.setMemAccount(rs.getString("MEM_ACCOUNT"));
				vo.setComName(rs.getString("COM_NAME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public List<ReportEnterpriseVo> selectResult(int reResult) {

		String sql = "SELECT * FROM REPORT_ENTERPRISE "
				+ "LEFT JOIN MEMBER ON REPORT_ENTERPRISE.MEM_ID = MEMBER.MEM_ID  "
				+ "LEFT JOIN COMPANY_MEMBER ON REPORT_ENTERPRISE.COM_MEM_ID = COMPANY_MEMBER.COM_MEM_ID  "
				+ "WHERE RE_RESULT = ?;";

		// 建立包裝資料的實體List容器
		List<ReportEnterpriseVo> list = new ArrayList<ReportEnterpriseVo>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = null;

			ps.setInt(1, reResult);

			rs = ps.executeQuery();

			while (rs.next()) {
				ReportEnterpriseVo vo = new ReportEnterpriseVo();
				vo.setReNo(rs.getInt("RE_NO"));
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setReStartTime(rs.getDate("RE_START_TIME"));
				vo.setReStatus(rs.getInt("RE_STATUS"));
				vo.setReResult(rs.getInt("RE_RESULT"));
				vo.setMemAccount(rs.getString("MEM_ACCOUNT"));
				vo.setComName(rs.getString("COM_NAME"));

				list.add(vo);
//				System.out.println("list : " + list);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ReportEnterpriseVo> selectAll() {

		String sql = "SELECT * FROM REPORT_ENTERPRISE "
				+ "LEFT JOIN MEMBER ON MEMBER.MEM_ID = REPORT_ENTERPRISE.MEM_ID LEFT JOIN "
				+ "COMPANY_MEMBER ON COMPANY_MEMBER.COM_MEM_ID = REPORT_ENTERPRISE.COM_MEM_ID ;";

		// 建立包裝資料的實體List容器
		List<ReportEnterpriseVo> list = new ArrayList<ReportEnterpriseVo>();

		try (Connection conn = dataSource.getConnection(); Statement st = conn.createStatement();) {
			ResultSet rs = null;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				ReportEnterpriseVo vo = new ReportEnterpriseVo();
				vo.setReNo(rs.getInt("RE_NO"));
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setMemAccount(rs.getString("MEM_ACCOUNT"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setComName(rs.getString("COM_NAME"));
				vo.setReStartTime(rs.getDate("RE_START_TIME"));
				vo.setReStatus(rs.getInt("RE_STATUS"));
				vo.setReResult(rs.getInt("RE_RESULT"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ReportEnterpriseVo statusShow(int reNo) {

		// 動態生成 SQL 查詢語句
		String sql = "SELECT * FROM REPORT_ENTERPRISE "
				+ "LEFT JOIN MEMBER ON REPORT_ENTERPRISE.MEM_ID = MEMBER.MEM_ID "
				+ "LEFT JOIN COMPANY_MEMBER ON REPORT_ENTERPRISE.COM_MEM_ID = COMPANY_MEMBER.COM_MEM_ID "
				+ "WHERE RE_NO = ?;";

		ReportEnterpriseVo vo = new ReportEnterpriseVo();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = null;
			ps.setInt(1, reNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				// 取得整數型態資料
				vo.setReNo(rs.getInt("RE_NO"));
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setRjtNo(rs.getInt("RJT_NO"));
				vo.setReContent(rs.getString("RE_CONTENT"));
				vo.setReUpload(rs.getString("RE_UPLOAD"));
				vo.setMemAccount(rs.getString("MEM_ACCOUNT"));
				vo.setComName(rs.getString("COM_NAME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

}
