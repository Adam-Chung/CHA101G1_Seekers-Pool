package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.CollectJobVo;

public class JobCollectDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public int insert(int memId, int jobNo) {

		String sql = "INSERT INTO COLLECT_JOB SET MEM_ID = ? , JOB_NO = ? ;";
		int rowcount = 0;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, memId);
			ps.setInt(2, jobNo);

			rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowcount;
	}

	public List<CollectJobVo> selectAll() {

		String sql = "SELECT * FROM COLLECT_JOB ;";
		List<CollectJobVo> list = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);) {
			
			while (rs.next()) {
				CollectJobVo vo = new CollectJobVo();
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setJobNo(rs.getInt("JOB_NO"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
