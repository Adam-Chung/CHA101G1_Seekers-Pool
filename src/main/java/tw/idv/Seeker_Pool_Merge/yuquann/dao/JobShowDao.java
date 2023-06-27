package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;

public class JobShowDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public Map<String, Object> jobShow(int jobNo) {

		String sql = "SELECT * FROM JOB "
				+ "LEFT JOIN COMPANY_MEMBER ON JOB.COM_MEM_ID = COMPANY_MEMBER.COM_MEM_ID "
				+ "LEFT JOIN POSITION_TYPE ON JOB.PT_NO = POSITION_TYPE.PT_NO " 
				+ "WHERE JOB_NO = ? ;";

		Map<String, Object> job = new HashMap<>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			
			ps.setInt(1, jobNo);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) {
				job.put("jobName", rs.getString("JOB_NAME"));
				job.put("comName", rs.getString("COM_NAME"));
				job.put("comMemId", rs.getInt("COM_MEM_ID"));
				job.put("jobContent", rs.getString("JOB_CONTENT"));
				job.put("jobOther",rs.getString("JOB_OTHER"));
				job.put("ptName", rs.getString("PT_NAME"));
				job.put("jobSalary", rs.getInt("JOB_SALARY"));
				job.put("jobAddress", rs.getString("JOB_ADDRESS"));
				job.put("comPicture", rs.getString("COM_PICTURE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

}
