package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.SkillTypeVo;

public class SkillTypeDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public List<SkillTypeVo> SelectAll() {

		String sql = "select * from skill_type ; ";

		List<SkillTypeVo> list = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);)

		{

			while (rs.next()) {
				SkillTypeVo vo = new SkillTypeVo();
				vo.setSkNo(rs.getInt("SK_NO"));
				vo.setSkType(rs.getString("SK_TYPE"));
				vo.setSkName(rs.getString("SK_NAME"));
				vo.setSkSearchTimes(rs.getInt("SK_SEARCH_TIMES"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int skillAdd(String skType, String skName) {

		String sql = "insert into skill_type set SK_TYPE = ? , SK_NAME = ? , SK_SEARCH_TIMES = 0 ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, skType);
			ps.setString(2, skName);

			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowcount;
	}

	public int skillEdit(String skType, String skName, int skNo) {

		String sql = "update skill_type set SK_TYPE = ? , SK_NAME = ?  where SK_NO = ? ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, skType);
			ps.setString(2, skName);
			ps.setInt(3, skNo);

			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowcount;
	}

	public SkillTypeVo skillEditShow(int skNo) {

		String sql = "select * from skill_type where SK_NO = ? ;";
		ResultSet rs = null;
		SkillTypeVo vo = new SkillTypeVo();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, skNo);
			rs = ps.executeQuery();

			while (rs.next()) {

				vo.setSkNo(rs.getInt("SK_NO"));
				vo.setSkType(rs.getString("SK_TYPE"));
				vo.setSkName(rs.getString("SK_NAME"));
				vo.setSkSearchTimes(rs.getInt("SK_SEARCH_TIMES"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public List<SkillTypeVo> skillSelect(String keyword) {

		String sql = "SELECT * FROM skill_type WHERE SK_TYPE LIKE CONCAT('%', ?, '%') OR SK_NAME LIKE CONCAT('%', ?, '%') OR Sk_NO LIKE CONCAT('%', ?, '%');";
		ResultSet rs = null;

		List<SkillTypeVo> list = new ArrayList<>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setString(3, keyword);

			rs = ps.executeQuery();

			while (rs.next()) {
				SkillTypeVo vo = new SkillTypeVo();
				vo.setSkNo(rs.getInt("SK_NO"));
				vo.setSkType(rs.getString("SK_TYPE"));
				vo.setSkName(rs.getString("SK_NAME"));
				vo.setSkSearchTimes(rs.getInt("SK_SEARCH_TIMES"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int skillDelete(int skNo) {

		String sql = "DELETE FROM SKILL_TYPE WHERE SK_NO = ? ;";
		int rowcount = 0 ;
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, skNo);
			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowcount;
	}

}
