package tw.idv.Seeker_Pool_Merge.xuan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.xuan.dao.OnlineCourseDao;
import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;

public class OnlineCourseDaoImpl implements OnlineCourseDao {
	
	private DataSource dataSource = HikariCPUtil.getDataSource();
	
	//	新增課程
	@Override
	public void createOnlineCourse(OnlineCourseVo onlineCourse) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO ONLINE_COURSE (ON_TITLE, ON_INDEX, ON_STATUS, ON_PIC, ON_VIDEO) VALUES (?, ?, ?, ?, ?)")) {

			pstmt.setString(1, onlineCourse.getOnTitle());
			pstmt.setString(2, onlineCourse.getOnIndex());
			pstmt.setInt(3, onlineCourse.getOnStatus());
			pstmt.setString(4, onlineCourse.getOnPic());
			pstmt.setString(5, onlineCourse.getOnVideo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//	後台列表
	@Override
	public List<OnlineCourseVo> getAllOnlineCourses() {
		List<OnlineCourseVo> courses = new ArrayList<>();

	    try (Connection conn = dataSource.getConnection();
	    		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM online_course");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            OnlineCourseVo course = new OnlineCourseVo();
	            course.setOnNo(rs.getInt("ON_NO"));
				course.setOnTitle(rs.getString("ON_TITLE"));
				course.setOnIndex(rs.getString("ON_INDEX"));
				course.setOnStatus(rs.getInt("ON_STATUS"));
				course.setOnPic(rs.getString("ON_PIC"));
				course.setOnVideo(rs.getString("ON_VIDEO"));

	            courses.add(course);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return courses;
	}
	public List<OnlineCourseVo> getAllOnlineCoursesOnStatus() {
		List<OnlineCourseVo> courses = new ArrayList<>();

	    try (Connection conn = dataSource.getConnection();
	    		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM online_course where on_status=1;");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            OnlineCourseVo course = new OnlineCourseVo();
	            course.setOnNo(rs.getInt("ON_NO"));
				course.setOnTitle(rs.getString("ON_TITLE"));
				course.setOnIndex(rs.getString("ON_INDEX"));
				course.setOnStatus(rs.getInt("ON_STATUS"));
				course.setOnPic(rs.getString("ON_PIC"));
				course.setOnVideo(rs.getString("ON_VIDEO"));

	            courses.add(course);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return courses;
	}
	public OnlineCourseVo getOneOnlineCourses(String onNo) {
		OnlineCourseVo course = new OnlineCourseVo();

	    try (		
	    		Connection conn = dataSource.getConnection();
	    		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM online_course where on_no= ?;");	
	         ) {
	    	stmt.setString(1, onNo);
	    	ResultSet rs = stmt.executeQuery();
	    	System.out.println(rs);
	    	

	        while (rs.next()) {
	            course.setOnNo(rs.getInt("ON_NO"));
				course.setOnTitle(rs.getString("ON_TITLE"));
				course.setOnIndex(rs.getString("ON_INDEX"));
				course.setOnStatus(rs.getInt("ON_STATUS"));
				course.setOnPic(rs.getString("ON_PIC"));
				course.setOnVideo(rs.getString("ON_VIDEO"));
  
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println(course);

	    return course;
	}
	
	//	上架列表
	public List<OnlineCourseVo> getOnlineCourses() {
	    List<OnlineCourseVo> courses = new ArrayList<>();

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM online_course WHERE ON_STATUS = 1");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            OnlineCourseVo course = new OnlineCourseVo();
	            course.setOnNo(rs.getInt("ON_NO"));
	            course.setOnTitle(rs.getString("ON_TITLE"));
	            course.setOnIndex(rs.getString("ON_INDEX"));
	            course.setOnStatus(rs.getInt("ON_STATUS"));
	            course.setOnPic(rs.getString("ON_PIC"));
	            course.setOnVideo(rs.getString("ON_VIDEO"));

	            courses.add(course);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return courses;
	}
	
	// 查詢課程
	@Override
	public List<OnlineCourseVo> searchOnlineCoursesByTitle(String onTitle) {
		List<OnlineCourseVo> courses = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ONLINE_COURSE WHERE ON_TITLE LIKE ?")) {
			//進行模糊比對
			pstmt.setString(1, "%" + onTitle + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				OnlineCourseVo course = new OnlineCourseVo();
				course.setOnNo(rs.getInt("ON_NO"));
				course.setOnTitle(rs.getString("ON_TITLE"));
				course.setOnIndex(rs.getString("ON_INDEX"));
				course.setOnStatus(rs.getInt("ON_STATUS"));
				course.setOnPic(rs.getString("ON_PIC"));
				course.setOnVideo(rs.getString("ON_VIDEO"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	// 修改課程狀態
	public void updateOnlineCourse(OnlineCourseVo onlineCourse) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"UPDATE ONLINE_COURSE SET  ON_STATUS = ? WHERE ON_NO = ?")) {

			pstmt.setInt(1, onlineCourse.getOnStatus());
			pstmt.setInt(2, onlineCourse.getOnNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteOnlineCourse(int onNo) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ONLINE_COURSE WHERE ON_NO = ?")) {

			pstmt.setInt(1, onNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
