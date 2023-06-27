package tw.idv.Seeker_Pool_Merge.song.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.dao.JobFairDao;
import tw.idv.Seeker_Pool_Merge.song.vo.JobFairVo;

public class JobFairDaoImpl implements JobFairDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/seeker_pool_schemas?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "root";

	private static final String INSERT_STMT =
		"INSERT INTO job_fair (adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG) VALUES (?, ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG,jr_status  FROM job_fair where jr_status=1 order by jf_no";
	private static final String GET_ALL_STMT3 =
			"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG,jr_status  FROM job_fair order by jf_no";
	private static final String GET_ALL_STMT2 =
			"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG,jr_status  FROM job_fair order by jf_no";
	private static final String GET_ONE_STMT =
		"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG FROM job_fair where jf_name LIKE ?;";
	private static final String GET_ONE_STMT2 =
			"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG,jr_status  FROM job_fair where jr_status = ?;";
	private static final String GET_ONE_STMT3 =
			"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG,jr_status  FROM job_fair where jf_no = ?;";
	private static final String GET_ONE_STMT4 =
			"SELECT jf_no,adm_id,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG FROM job_fair where jf_start_time >= ? AND jf_start_time <= ? AND jr_status =1;";
	private static final String DELETE =
		"UPDATE job_fair set jr_status=? where jf_no = ?";
	private static final String SAFE1 = 
			"SET SQL_SAFE_UPDATES=0;";
	private static final String SAFE2 = 
			"SET SQL_SAFE_UPDATES=1;";
	private static final String DELETE2 =		
		       "UPDATE job_fair set jr_status= 0 where reg_end_time < current_date();";      
	private static final String UPDATE =
		"UPDATE job_fair set jf_no=?, adm_id=?, jf_name=?, jf_start_time=?, jf_end_time=?, reg_start_time=?, reg_end_time=? ,jf_activity=? ,JR_IMG=? where jf_no = ?";

	@Override
	public boolean insert(JobFairVo jobFairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
           
			
			pstmt.setInt(1, jobFairVO.getAdmId());
			pstmt.setString(2, jobFairVO.getJfName());
			pstmt.setDate(3, jobFairVO. getJfStartTime());
			pstmt.setDate(4, jobFairVO.getJfEndTime());
			pstmt.setDate(5, jobFairVO.getRegStartTime());
			pstmt.setDate(6, jobFairVO. getRegEndTime());
			pstmt.setString(7, jobFairVO. getJfActivity());
			pstmt.setString(8, jobFairVO.getJrArImg());
		

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
		return true;

	}

	@Override
	public void update(JobFairVo jobFairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, jobFairVO.getJfNo());
			pstmt.setInt(2, jobFairVO.getAdmId());
			pstmt.setString(3, jobFairVO.getJfName());
			pstmt.setDate(4, jobFairVO. getJfStartTime());
			pstmt.setDate(5, jobFairVO.getJfEndTime());
			pstmt.setDate(6, jobFairVO.getRegStartTime());
			pstmt.setDate(7, jobFairVO. getRegEndTime());
			pstmt.setString(8, jobFairVO. getJfActivity());
			pstmt.setString(9, jobFairVO.getJrArImg());
			pstmt.setInt(10, jobFairVO.getJfNo());

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
	public void delete(Integer jfNo,Integer jrStatus) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, jrStatus);
			pstmt.setInt(2, jfNo);
			

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
	public JobFairVo findByPrimaryKey(Integer jfNo) {

		
		JobFairVo  jobFairVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT3);

			pstmt.setInt(1, jfNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
			
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				
				
			}

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
		return jobFairVO ;
	}
	@Override
	public List<JobFairVo> findBySeason(String season) {

		List<JobFairVo> list=new ArrayList<JobFairVo>();
		JobFairVo  jobFairVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ("%"+season+"%"));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
			
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				list.add(jobFairVO);
				
			}

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
		return list ;
	}
	public List<JobFairVo> findBySeason2(String season) {

		List<JobFairVo> list=new ArrayList<JobFairVo>();
		JobFairVo  jobFairVO = null;
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
			pstmt = con.prepareStatement(GET_ONE_STMT4);
			

			pstmt.setString(1, start);
			pstmt.setString(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
			
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				list.add(jobFairVO);
				
			}

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
		return list ;
	}
	public List<JobFairVo> findByJrStatus(Integer jrStatus) {

		 List<JobFairVo> list = new ArrayList<JobFairVo>();;
		 JobFairVo jobFairVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setInt(1, jrStatus);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				jobFairVO.setJrStatus(rs.getInt("jr_status"));
				
				list.add(jobFairVO);
				
			}

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
		return list ;
	}

	@Override
	public List<JobFairVo> getAll() {
		List<JobFairVo> list = new ArrayList<JobFairVo>();
		JobFairVo jobFairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				jobFairVO.setJrStatus(rs.getInt("jr_status"));
				list.add(jobFairVO);
				
			}

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
	public List<JobFairVo> getAll3() {
		List<JobFairVo> list = new ArrayList<JobFairVo>();
		JobFairVo jobFairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT3);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				jobFairVO.setJrStatus(rs.getInt("jr_status"));
				list.add(jobFairVO);
				
			}

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
	public List<JobFairVo> getAll2() {
		List<JobFairVo> list = new ArrayList<JobFairVo>();
		JobFairVo jobFairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				jobFairVO = new JobFairVo();
				jobFairVO.setJfNo( rs.getInt("Jf_No"));
				jobFairVO.setAdmId( rs.getInt("adm_id"));
				jobFairVO.setJfName(rs.getString("Jf_Name"));
				jobFairVO.setJfStartTime(rs.getDate("Jf_Start_Time"));
				jobFairVO.setJfEndTime(rs.getDate("Jf_End_Time"));
				jobFairVO.setRegStartTime(rs.getDate("Reg_Start_Time"));
				jobFairVO.setRegEndTime(rs.getDate("Reg_End_Time"));
				jobFairVO.setJfActivity(rs.getString("Jf_Activity"));
				jobFairVO.setJrArImg(rs.getString("JR_IMG"));
				jobFairVO.setJrStatus(rs.getInt("jr_status"));
				list.add(jobFairVO);
				
			}

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
}

	