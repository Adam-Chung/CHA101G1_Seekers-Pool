package tw.idv.Seeker_Pool_Merge.yuquann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.JobVo;

public class JobSearchDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();

	public List<JobVo> searchResult(String keyword, String city, String town) {

		String sql = "SELECT * FROM JOB "
				+ "LEFT JOIN COMPANY_MEMBER ON JOB.COM_MEM_ID = COMPANY_MEMBER.COM_MEM_ID "
				+ "LEFT JOIN POSITION_TYPE ON JOB.PT_NO = POSITION_TYPE.PT_NO "
				+ "WHERE JOB_NAME LIKE concat('%',?,'%') " + "AND JOB_UPLOAD = 1 "
				+ "AND CITY_NAME LIKE concat('%',?,'%') " + "AND DISTRICT_NAME LIKE concat('%',?,'%') "
				+ "ORDER BY JOB_TOP DESC;";

		// 建立集合把物件包裝起來
		List<JobVo> list = new ArrayList<>();
		ResultSet rs = null;

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, keyword);
			ps.setString(2, city);
			ps.setString(3, town);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				// 建立vo實體以包裝物件內容
				JobVo vo = new JobVo();
				vo.setJobNo(rs.getInt("JOB_NO"));
				vo.setJobName(rs.getString("JOB_NAME"));
				vo.setCityName(rs.getString("CITY_NAME"));
				vo.setDistrictName(rs.getString("DISTRICT_NAME"));
				vo.setComName(rs.getString("COM_NAME"));
				vo.setComMemId(rs.getInt("COM_MEM_ID"));
				vo.setJobTop(rs.getInt("JOB_TOP"));
				vo.setComPicture(rs.getString("COM_PICTURE"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
