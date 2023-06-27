package tw.idv.Seeker_Pool_Merge.song.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.dao.JobRecruitmentArticleDao;
import tw.idv.Seeker_Pool_Merge.song.vo.JobRecruitmentArticleVo;

public class JobRecruitmentArticleDaoImpl implements JobRecruitmentArticleDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/seeker_pool_schemas?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "root";

	private static final String INSERT_STMT = "INSERT INTO job_recruitment_article (adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_start_time,jr_ar_end_time,jr_ar_com\r\n"
			+ ") VALUES ( ?, ?, ?, ?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT jr_ar_no,adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_status,jr_ar_start_time,jr_ar_end_time,jr_ar_com\r\n"
			+ " FROM job_recruitment_article order by jr_ar_no;";
	private static final String GET_ALL_STMT2 = "SELECT jr_ar_no,adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_status,jr_ar_start_time,jr_ar_end_time,jr_ar_com\r\n"
			+ " FROM job_recruitment_article  where jr_ar_status=? order by jr_ar_no;";
	private static final String GET_ALL_STMT3 = "SELECT jr_ar_no,adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_status,jr_ar_start_time,jr_ar_end_time,jr_ar_com\r\n"
			+ " FROM job_recruitment_article  where  jr_ar_title = ? order by jr_ar_no;";
	private static final String GET_ALL_STMT4 = "SELECT jr_ar_no,adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_status,jr_ar_start_time,jr_ar_end_time,jr_ar_com\r\n"
			+ " FROM job_recruitment_article  where jr_ar_status=1 and  jr_ar_end_time >= ? AND jr_ar_end_time <= ? order by jr_ar_no;";
	private static final String GET_ONE_STMT = "SELECT jr_ar_no,adm_id,jr_ar_title,jr_ar_content,jr_ar_img,jr_pub_time,jr_ar_status,jr_ar_start_time,jr_ar_end_time, jr_ar_com\r\n"
			+ "  FROM job_recruitment_article where jr_ar_no = ?";
	private static final String DELETE = "UPDATE job_recruitment_article set jr_ar_status=? where jr_ar_no = ?";
	private static final String SAFE1 = 
			"SET SQL_SAFE_UPDATES=0;";
	private static final String SAFE2 = 
			"SET SQL_SAFE_UPDATES=1;";
	private static final String DELETE2 = "UPDATE job_recruitment_article set jr_ar_status=0 where jr_ar_end_time < current_date();";
	private static final String UPDATE = "UPDATE jr_ar_no=?,jf_no=?,adm_id=?,jr_ar_title=?,jr_ar_content=?,jr_ar_img=?,jr_pub_time=? where jr_ar_no = ?";

	@Override
	public boolean insert(JobRecruitmentArticleVo jobRecruitmentArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
		
		
			pstmt.setInt(1, jobRecruitmentArticleVO.getAdmId());
			pstmt.setString(2, jobRecruitmentArticleVO.getJrArTitle());
			pstmt.setString(3, jobRecruitmentArticleVO.getJrArContent());
			pstmt.setString(4, jobRecruitmentArticleVO.getJrArImg());
			pstmt.setDate(5, jobRecruitmentArticleVO.getJrPubTime());
			pstmt.setDate(6, jobRecruitmentArticleVO.getJrArStartTime());
			pstmt.setDate(7, jobRecruitmentArticleVO.getJrArEndTime());
			pstmt.setString(8, jobRecruitmentArticleVO.getJrArCom());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return true;

	}

	@Override
	public void update(JobRecruitmentArticleVo jobRecruitmentArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, jobRecruitmentArticleVO.getJrArNo());
			pstmt.setInt(2, jobRecruitmentArticleVO.getAdmId());
			pstmt.setString(3, jobRecruitmentArticleVO.getJrArTitle());
			pstmt.setString(4, jobRecruitmentArticleVO.getJrArContent());
			pstmt.setString(5, jobRecruitmentArticleVO.getJrArImg());
			pstmt.setDate(6, jobRecruitmentArticleVO.getJrPubTime());
			pstmt.setInt(7, jobRecruitmentArticleVO.getJrArStatus());
			pstmt.setDate(8, jobRecruitmentArticleVO.getJrArStartTime());
			pstmt.setDate(9, jobRecruitmentArticleVO.getJrArEndTime());
			pstmt.setString(10, jobRecruitmentArticleVO.getJrArCom());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer jobRecruitmentArticle) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, jobRecruitmentArticle);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public JobRecruitmentArticleVo findByPrimaryKey(Integer jrArNo) {

		JobRecruitmentArticleVo jobRecruitmentArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, jrArNo);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {

				jobRecruitmentArticleVO = new JobRecruitmentArticleVo();
				jobRecruitmentArticleVO.setJrArNo(rs.getInt("Jr_Ar_No"));
				jobRecruitmentArticleVO.setAdmId(rs.getInt("Adm_Id"));
				jobRecruitmentArticleVO.setJrArTitle(rs.getString("Jr_Ar_Title"));
				jobRecruitmentArticleVO.setJrArContent(rs.getString("Jr_Ar_Content"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));
				jobRecruitmentArticleVO.setJrPubTime(rs.getDate("Jr_Pub_Time"));
				jobRecruitmentArticleVO.setJrArStatus(rs.getInt("Jr_Ar_Status"));
				jobRecruitmentArticleVO.setJrArStartTime(rs.getDate("Jr_Ar_Start_Time"));
				jobRecruitmentArticleVO.setJrArEndTime(rs.getDate("Jr_Ar_End_Time"));
				jobRecruitmentArticleVO.setJrArCom(rs.getString("Jr_Ar_Com"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return jobRecruitmentArticleVO;
	}
	public JobRecruitmentArticleVo findByTitle(String title) {

		JobRecruitmentArticleVo jobRecruitmentArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT3);

			pstmt.setString(1, title);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {

				jobRecruitmentArticleVO = new JobRecruitmentArticleVo();
				jobRecruitmentArticleVO.setJrArNo(rs.getInt("Jr_Ar_No"));
				jobRecruitmentArticleVO.setAdmId(rs.getInt("Adm_Id"));
				jobRecruitmentArticleVO.setJrArTitle(rs.getString("Jr_Ar_Title"));
				jobRecruitmentArticleVO.setJrArContent(rs.getString("Jr_Ar_Content"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));
				jobRecruitmentArticleVO.setJrPubTime(rs.getDate("Jr_Pub_Time"));
				jobRecruitmentArticleVO.setJrArStatus(rs.getInt("Jr_Ar_Status"));
				jobRecruitmentArticleVO.setJrArStartTime(rs.getDate("Jr_Ar_Start_Time"));
				jobRecruitmentArticleVO.setJrArEndTime(rs.getDate("Jr_Ar_End_Time"));
				jobRecruitmentArticleVO.setJrArCom(rs.getString("Jr_Ar_Com"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return jobRecruitmentArticleVO;
	}
	public void delete(Integer jfArNo,Integer jrArStatus) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, jrArStatus);
			pstmt.setInt(2, jfArNo);
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	public void delete2() {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SAFE1);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(DELETE2);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(SAFE2);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public List<JobRecruitmentArticleVo> getAll() {
		List<JobRecruitmentArticleVo> list = new ArrayList<JobRecruitmentArticleVo>();
		JobRecruitmentArticleVo jobRecruitmentArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobRecruitmentArticleVO = new JobRecruitmentArticleVo();
				jobRecruitmentArticleVO.setJrArNo(rs.getInt("Jr_Ar_No"));
				jobRecruitmentArticleVO.setAdmId(rs.getInt("Adm_Id"));
				jobRecruitmentArticleVO.setJrArTitle(rs.getString("Jr_Ar_Title"));
				jobRecruitmentArticleVO.setJrArContent(rs.getString("Jr_Ar_Content"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));
				jobRecruitmentArticleVO.setJrPubTime(rs.getDate("Jr_Pub_Time"));
				jobRecruitmentArticleVO.setJrArStatus(rs.getInt("Jr_Ar_Status"));
				jobRecruitmentArticleVO.setJrArStartTime(rs.getDate("Jr_Ar_Start_Time"));
				jobRecruitmentArticleVO.setJrArEndTime(rs.getDate("Jr_Ar_End_Time"));
				jobRecruitmentArticleVO.setJrArCom(rs.getString("Jr_Ar_Com"));
				list.add(jobRecruitmentArticleVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public List<JobRecruitmentArticleVo> getAll2(Integer jrArStatus) {
		List<JobRecruitmentArticleVo> list = new ArrayList<JobRecruitmentArticleVo>();
		JobRecruitmentArticleVo jobRecruitmentArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			pstmt.setInt(1, jrArStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobRecruitmentArticleVO = new JobRecruitmentArticleVo();
				jobRecruitmentArticleVO.setJrArNo(rs.getInt("Jr_Ar_No"));
				jobRecruitmentArticleVO.setAdmId(rs.getInt("Adm_Id"));
				jobRecruitmentArticleVO.setJrArTitle(rs.getString("Jr_Ar_Title"));
				jobRecruitmentArticleVO.setJrArContent(rs.getString("Jr_Ar_Content"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));
				jobRecruitmentArticleVO.setJrPubTime(rs.getDate("Jr_Pub_Time"));
				jobRecruitmentArticleVO.setJrArStatus(rs.getInt("Jr_Ar_Status"));
				jobRecruitmentArticleVO.setJrArStartTime(rs.getDate("Jr_Ar_Start_Time"));
				jobRecruitmentArticleVO.setJrArEndTime(rs.getDate("Jr_Ar_End_Time"));
				jobRecruitmentArticleVO.setJrArCom(rs.getString("Jr_Ar_Com"));
				list.add(jobRecruitmentArticleVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public List<JobRecruitmentArticleVo> getAll3(String season) {
		List<JobRecruitmentArticleVo> list = new ArrayList<JobRecruitmentArticleVo>();
		JobRecruitmentArticleVo jobRecruitmentArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String start = "";
		String end ="";
		if("春季".equals(season)) {
			start="2023-03-01";
			end="2023-05-31";
		}else if("夏季".equals(season)) {
			start="2023-06-01";
			end="2023-08-31";
		}else if("秋季".equals(season)) {
			start="2023-09-01";
			end="2023-10-31";
		}else if("冬季".equals(season)) {
			start="2023-11-01";
			end="2023-12-31";
		}


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT4);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobRecruitmentArticleVO = new JobRecruitmentArticleVo();
				jobRecruitmentArticleVO.setJrArNo(rs.getInt("Jr_Ar_No"));
				jobRecruitmentArticleVO.setAdmId(rs.getInt("Adm_Id"));
				jobRecruitmentArticleVO.setJrArTitle(rs.getString("Jr_Ar_Title"));
				jobRecruitmentArticleVO.setJrArContent(rs.getString("Jr_Ar_Content"));
				jobRecruitmentArticleVO.setJrArImg(rs.getString("Jr_Ar_Img"));
				jobRecruitmentArticleVO.setJrPubTime(rs.getDate("Jr_Pub_Time"));
				jobRecruitmentArticleVO.setJrArStatus(rs.getInt("Jr_Ar_Status"));
				jobRecruitmentArticleVO.setJrArStartTime(rs.getDate("Jr_Ar_Start_Time"));
				jobRecruitmentArticleVO.setJrArEndTime(rs.getDate("Jr_Ar_End_Time"));
				jobRecruitmentArticleVO.setJrArCom(rs.getString("Jr_Ar_Com"));
				list.add(jobRecruitmentArticleVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

	
		
		String str=" 7月 16, 2023";
		String str1=str.substring(8,12)+"-"+str.substring(1,2)+"-"+str.substring(4,6);
		System.out.printf(str1); 

		
	}
}