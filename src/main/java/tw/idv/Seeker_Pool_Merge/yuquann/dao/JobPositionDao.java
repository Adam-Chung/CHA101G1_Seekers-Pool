package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.PositionTypeVo;

public class JobPositionDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public int add(String ptType, String ptName) {

		// 建立sql語句
		String sql = "insert into position_type (PT_TYPE,PT_NAME,PT_SEARCH_TIMES) VALUES (?,?,?);";
		int rowcount = 0;

		// 建立資料庫連線以及預編譯語句 使用try-with-resource
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			// 設定編譯語句的內容
			ps.setString(1, ptType);
			ps.setString(2, ptName);
			ps.setInt(3, 0);

			// 回傳新增行數
			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowcount;
	}

	public List<PositionTypeVo> showAll() {

		String sql = "select * from position_type;";

		List<PositionTypeVo> list = new ArrayList<PositionTypeVo>();

		try (Connection conn = dataSource.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {
				PositionTypeVo vo = new PositionTypeVo();
				vo.setPtNO(rs.getInt("PT_NO"));
				vo.setPtType(rs.getString("PT_TYPE"));
				vo.setPtName(rs.getString("PT_NAME"));
				vo.setPtSearchTimes(rs.getInt("PT_SEARCH_TIMES"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public PositionTypeVo EditShow(int ptNo) {

		String sql = "select * from position_type where pt_no = ? ;";
		ResultSet rs = null;
		PositionTypeVo vo = new PositionTypeVo();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, ptNo);
			rs = ps.executeQuery();

			while (rs.next()) {

				vo.setPtNO(rs.getInt("PT_NO"));
				vo.setPtType(rs.getString("PT_TYPE"));
				vo.setPtName(rs.getString("PT_NAME"));
				vo.setPtSearchTimes(rs.getInt("PT_SEARCH_TIMES"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public int update(String ptType, String ptName, int ptNO) {

		String sql = "update position_type set PT_TYPE = ? , PT_NAME = ? where PT_NO = ? ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, ptType);
			ps.setString(2, ptName);
			ps.setInt(3, ptNO);
			rowcount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;
	}

	public List<PositionTypeVo> typeSelect(String ptType) {

		String sql = "select * from position_type where PT_TYPE LIKE CONCAT('%', ? , '%');";
		ResultSet rs = null;

		List<PositionTypeVo> list = new ArrayList<PositionTypeVo>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, ptType);
			rs = ps.executeQuery();

			while (rs.next()) {
				PositionTypeVo vo = new PositionTypeVo();
				vo.setPtNO(rs.getInt("PT_NO"));
				vo.setPtType(rs.getString("PT_TYPE"));
				vo.setPtName(rs.getString("PT_NAME"));
				vo.setPtSearchTimes(rs.getInt("PT_SEARCH_TIMES"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<PositionTypeVo> nameSelect(String ptName) {

		String sql = "select * from position_type where PT_NAME LIKE CONCAT('%', ? , '%');";
		ResultSet rs = null;

		List<PositionTypeVo> list = new ArrayList<PositionTypeVo>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, ptName);
			rs = ps.executeQuery();

			while (rs.next()) {
				PositionTypeVo vo = new PositionTypeVo();
				vo.setPtNO(rs.getInt("PT_NO"));
				vo.setPtType(rs.getString("PT_TYPE"));
				vo.setPtName(rs.getString("PT_NAME"));
				vo.setPtSearchTimes(rs.getInt("PT_SEARCH_TIMES"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int delete(int ptNO) {

		String sql = "delete from position_type where PT_NO = ? ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, ptNO);
			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;
	}

}
