package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.Report_EnterpriseVo;

public class Report_EnterpriseDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();

	public int insert(Report_EnterpriseVo vo) {
		

		Connection conn = null;
		PreparedStatement ps = null;
//
//		String url = "jdbc:mysql://localhost:3306/seeker_pool_schemas";
//		String user = "root";
//		String password = "root";
		String sql = "INSERT INTO report_enterprise (rjt_no,RE_CONTENT,RE_END_TIME,re_status,re_result,re_upload) VALUES (?, ?, ?, ?, ?, ?)";

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);

			InputStream is = (vo.getReUpload()).getInputStream();

			ps.setInt(1, vo.getRjtNo());
			ps.setString(2, vo.getReContent());
			ps.setDate(3, vo.getReEndTime());
			ps.setInt(4, vo.getReStatus());
			ps.setInt(5, vo.getReResult());
			ps.setBinaryStream(6, is);

			int rowcount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return 1;
	}

}
