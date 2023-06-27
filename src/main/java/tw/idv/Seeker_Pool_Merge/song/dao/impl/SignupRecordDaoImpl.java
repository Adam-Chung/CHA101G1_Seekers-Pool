package tw.idv.Seeker_Pool_Merge.song.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.dao.SignupRecordDao;
import tw.idv.Seeker_Pool_Merge.song.vo.SignupRecordVo;

public class SignupRecordDaoImpl implements SignupRecordDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/seeker_pool_schemas?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "root";
	
	
	
	
	private static final String GET_ALL_STMT4 =
			"select b.jf_no,adm_id,sr_no,com_name,jf_name,jf_activity,reg_start_time,reg_end_time,JR_IMG,sr_time\r\n"
			+ "from signup_record b \r\n"
			+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
			+ "join job_fair o on b.jf_no = o.jf_no\r\n"
			+ "where  p.com_name LIKE ? and b.sr_status=1;";
	private static final String GET_ALL_STMT2 =
			"select sr_no,com_name,jf_name,jf_activity,reg_start_time,reg_end_time,JR_IMG\r\n"
			+ "from signup_record b \r\n"
			+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
			+ "join job_fair o on b.jf_no = o.jf_no\r\n"
			+ "where  b.com_mem_id = ? and b.sr_status=1;";
	private static final String GET_ALL_STMT3 =
			"select  b.jf_no,adm_id,group_concat(com_name) ,jf_name,jf_start_time,jf_end_time,reg_start_time,reg_end_time,jf_activity,JR_IMG\r\n"
					+ "from signup_record b \r\n"
					+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
					+ "join job_fair o on b.jf_no = o.jf_no\r\n"
					+"group by jf_no;";
	private static final String GET_ONE_STMT2 =
			"select com_name, tax_num, com_email, com_tel, com_address,jf_name,reg_start_time,reg_end_time,JR_IMG\r\n"
			+ "from signup_record b \r\n"
			+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
			+ "join job_fair o on b.jf_no = o.jf_no\r\n"
			+ " where  b.com_mem_id = ?;";

	private static final String GET_ONE_STMT3 =
			" select b.jf_no,group_concat(com_name) ,jf_name,jf_start_time,jf_end_time,jf_activity,JR_IMG\r\n"
			+ "from signup_record b \r\n"
			+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
			+ "join job_fair o on b.jf_no = o.jf_no\r\n "
			+"where  b.jf_no = ?\r\n"
			+ "group by jf_no;";
	private static final String GET_ONE_STMT4 =
			" select b.jf_no,group_concat(com_name) ,jf_name,jf_start_time,jf_end_time,jf_activity,JR_IMG\r\n"
					+ "from signup_record b \r\n"
					+ "join company_member p on b.com_mem_id = p.com_mem_id\r\n"
					+ "join job_fair o on b.jf_no = o.jf_no\r\n "
					+"where o.jf_name LIKE ?\r\n"
					+ "group by jf_no;";
	private static final String GET_ONE_STMT5 =
			" select  sr_no,com_mem_id,jf_no,sr_time from signup_record where jf_no= ? and com_mem_id= ?;";				
	private static final String INSERT_STMT =
		"INSERT INTO signup_record (com_mem_id,jf_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT sr_no,com_mem_id,jf_no,sr_status  FROM signup_record order by sr_no";
	private static final String GET_ONE_STMT =
		"SELECT com_name, tax_num, com_email, com_tel, com_address FROM company_member where  com_mem_id = ?";
	private static final String DELETE =
		"UPDATE signup_record set sr_status=?  where sr_no = ?";
	private static final String UPDATE =
		"UPDATE signup_record set com_mem_id=? ,jf_no=? ,sr_status=?  where sr_no = ?";

	@Override
	public boolean insert(SignupRecordVo  signupRecordVO ) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, signupRecordVO.getComMemId());
			pstmt.setInt(2, signupRecordVO.getJfNo());
			
			
		

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
	public void update(SignupRecordVo  signupRecordVO ) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, signupRecordVO.getComMemId());
			pstmt.setInt(2, signupRecordVO.getJfNo());
			pstmt.setInt(3, signupRecordVO.getSrStatus());
			pstmt.setInt(4, signupRecordVO.getSrNo());
			

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
	public void delete(Integer srNo,Integer srStatus  ) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, srStatus);
			pstmt.setInt(2, srNo);
			
			

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
	public List<SignupRecordVo> lookUp() {
		List<SignupRecordVo> list = new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT2);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setComName(rs.getString("com_name"));
				signupRecordVO.setTaxNum( rs.getString("tax_num"));
				signupRecordVO.setComEmail(rs.getString("com_email"));
				signupRecordVO.setComAddress(rs.getString("com_address"));
				signupRecordVO.setComTel(rs.getString("com_tel"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setRegStartTime(rs.getDate("reg_start_time"));
				signupRecordVO.setRegEndTime(rs.getDate("reg_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
			list.add(signupRecordVO);
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
	
	public List<SignupRecordVo> lookUp2() {
		List<SignupRecordVo> list = new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT3);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setJfNo(rs.getInt("jf_no"));
				signupRecordVO.setAdmId(rs.getInt("adm_Id"));
				signupRecordVO.setComName(rs.getString("group_concat(com_name)"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setJfStartTime(rs.getDate("jf_start_time"));
				signupRecordVO.setJfEndTime(rs.getDate("jf_end_time"));
				signupRecordVO.setRegStartTime(rs.getDate("reg_start_time"));
				signupRecordVO.setRegEndTime(rs.getDate("reg_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				signupRecordVO.setJfActivity(rs.getString("jf_activity"));
			list.add(signupRecordVO);
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
	public List<SignupRecordVo> lookUp1(Integer comMemId) {
		List<SignupRecordVo> list=new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setInt(1, comMemId);
			

			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setComName(rs.getString("com_name"));
				signupRecordVO.setTaxNum( rs.getString("tax_num"));
				signupRecordVO.setComEmail(rs.getString("com_email"));
				signupRecordVO.setComAddress(rs.getString("com_address"));
				signupRecordVO.setComTel(rs.getString("com_tel"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setRegStartTime(rs.getDate("reg_start_time"));
				signupRecordVO.setRegEndTime(rs.getDate("reg_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				list.add(signupRecordVO);
			
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
	public List<SignupRecordVo> lookUp4(Integer comMemId) {
		List<SignupRecordVo> list=new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT2);

			pstmt.setInt(1, comMemId);
			

			rs = pstmt.executeQuery();
		
			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setSrNo(rs.getInt("sr_no"));
				signupRecordVO.setComName(rs.getString("com_name"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setJfActivity(rs.getString("jf_activity"));
				signupRecordVO.setRegStartTime(rs.getDate("reg_start_time"));
				signupRecordVO.setRegEndTime(rs.getDate("reg_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				list.add(signupRecordVO);
			
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
	public List<SignupRecordVo> lookCom(String comName) {
		List<SignupRecordVo> list=new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT4);

			pstmt.setString(1, ("%"+comName+"%"));
			

			rs = pstmt.executeQuery();
		
			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setSrNo(rs.getInt("sr_no"));
				signupRecordVO.setAdmId(rs.getInt("adm_id"));
				signupRecordVO.setJfNo(rs.getInt("jf_no"));
				signupRecordVO.setComName(rs.getString("com_name"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setJfActivity(rs.getString("jf_activity"));
				signupRecordVO.setRegStartTime(rs.getDate("reg_start_time"));
				signupRecordVO.setRegEndTime(rs.getDate("reg_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				signupRecordVO.setSrTime(rs.getDate("sr_time"));
				list.add(signupRecordVO);
			
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
	public List<SignupRecordVo> lookUp3(Integer jfNo) {
		List<SignupRecordVo> list=new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;
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
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setJfNo(rs.getInt("jf_no"));
				signupRecordVO.setComName(rs.getString("group_concat(com_name)"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setJfStartTime(rs.getDate("jf_start_time"));
				signupRecordVO.setJfEndTime(rs.getDate("jf_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				signupRecordVO.setJfActivity(rs.getString("jf_activity"));
				list.add(signupRecordVO);
			
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
	public SignupRecordVo findByPrimaryKey(Integer signupRecord) {

		SignupRecordVo  signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, signupRecord);

			rs = pstmt.executeQuery();

			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setComName(rs.getString("com_name"));
				signupRecordVO.setTaxNum( rs.getString("tax_num"));
				signupRecordVO.setComEmail(rs.getString("com_email"));
				signupRecordVO.setComAddress(rs.getString("com_address"));
			
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
		return signupRecordVO ;
	}
	public SignupRecordVo findByPrimaryKey2(Integer jfNo,Integer comMemId) {

		SignupRecordVo  signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT5);

			pstmt.setInt(1, jfNo);
			pstmt.setInt(2, comMemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setSrNo(rs.getInt("sr_no"));
				signupRecordVO.setJfNo( rs.getInt("jf_no"));
				signupRecordVO.setComMemId(rs.getInt("com_mem_id"));
				signupRecordVO.setSrTime(rs.getDate("sr_time"));
			
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
		return signupRecordVO ;
	}
	public List<SignupRecordVo> findBySeason(String season) {
		List<SignupRecordVo> list = new ArrayList<SignupRecordVo>();;
		SignupRecordVo  signupRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT4);

			
			pstmt.setString(1, ("%"+season+"%"));

			rs = pstmt.executeQuery();

			while (rs.next()) {	
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setJfNo(rs.getInt("jf_no"));
				signupRecordVO.setComName(rs.getString("group_concat(com_name)"));
				signupRecordVO.setJfName(rs.getString("jf_name"));
				signupRecordVO.setJfStartTime(rs.getDate("jf_start_time"));
				signupRecordVO.setJfEndTime(rs.getDate("jf_end_time"));
				signupRecordVO.setJrArImg(rs.getString("JR_IMG"));
				signupRecordVO.setJfActivity(rs.getString("jf_activity"));
				list.add(signupRecordVO);
			
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
	public List<SignupRecordVo> getAll() {
		List<SignupRecordVo> list = new ArrayList<SignupRecordVo>();
		SignupRecordVo signupRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				signupRecordVO = new SignupRecordVo();
				signupRecordVO.setSrNo(rs.getInt("sr_no"));
				signupRecordVO.setComMemId( rs.getInt("com_mem_id"));
				signupRecordVO.setJfNo(rs.getInt("jf_no"));
				signupRecordVO.setSrStatus(rs.getInt("sr_status"));
				list.add(signupRecordVO);
				
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

	public static void main(String[] args) {

		SignupRecordDaoImpl dao = new SignupRecordDaoImpl();

		// �s�W
//		AdministratorVO administratorVO1 = new AdministratorVO();
//		administratorVO1.setAdmId(2);
//		administratorVO1.setAdmName("MANAGER");
//		administratorVO1.setAdmPosition("騎士");
//		administratorVO1.setAdmAccount("2345");
//		administratorVO1.setAdmPassword("23456");
//		administratorVO1.setAdmStatus(1);
//		dao.insert(administratorVO1);

		// �ק�
//		AdministratorVO administratorVO2 = new AdministratorVO();
//		administratorVO2.setAdmId(2);
//		administratorVO2.setAdmName("MANAGER");
//		administratorVO2.setAdmPosition("騎士");
//		administratorVO2.setAdmAccount("2345");
//		administratorVO2.setAdmPassword("23456");
//		administratorVO2.setAdmStatus(1);
//		dao.update(administratorVO2);



		// �d��
		SignupRecordVo administratorVO3 = dao.findByPrimaryKey(1);
		System.out.print(administratorVO3.getSrNo() + ",");
		System.out.print(administratorVO3.getComMemId() + ",");
		System.out.print(administratorVO3.getJfNo() + ",");
		System.out.print(administratorVO3.getSrStatus());
		System.out.println("---------------------");

		// �d��
		List<SignupRecordVo> list = dao.getAll();
		for (SignupRecordVo administratorVO4 : list) {
			System.out.print(administratorVO4.getSrNo() + ",");
			System.out.print(administratorVO4.getComMemId() + ",");
			System.out.print(administratorVO4.getJfNo() + ",");
			System.out.print(administratorVO4.getSrStatus());
			System.out.println("---------------------");
			System.out.println();
		}
	}
}