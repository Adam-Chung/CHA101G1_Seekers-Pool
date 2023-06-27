package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ApplyRecordVo;

public class JobApplyDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public int insert(int memId, int comMemId, int jobNo) {

		String sql = "INSERT INTO APPLY_RECORD SET " + "COM_MEM_ID = ? ,MEM_ID = ? ,JOB_NO = ? , HIRE_STATUS = 0 ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, comMemId);
			ps.setInt(2, memId);
			ps.setInt(3, jobNo);

			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;
	}

	public List<ApplyRecordVo> selectAll() {

		String sql = "SELECT * FROM APPLY_RECORD;";

		List<ApplyRecordVo> list = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {
				ApplyRecordVo vo = new ApplyRecordVo();
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setJobNo(rs.getInt("JOB_NO"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
