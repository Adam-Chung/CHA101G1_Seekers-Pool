package tw.idv.Seeker_Pool_Merge.song.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.dao.AdministratorDao;
import tw.idv.Seeker_Pool_Merge.song.vo.AdministratorVo;

public class AdministratorDaoImpl implements AdministratorDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/seeker_pool_schemas?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "root";

	private static final String INSERT_STMT =
		"INSERT INTO administrator (adm_id,adm_name,adm_position,adm_account,adm_password,adm_status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT adm_id,adm_name,adm_position,adm_account,adm_password,adm_status  FROM cha101_g1.administrator order by adm_id";
	private static final String GET_ONE_STMT =
		"SELECT adm_id,adm_name,adm_position,adm_account,adm_password,adm_status FROM administrator where adm_id = ?";
	private static final String GET_ONE_STMT3 =
			"SELECT adm_id,adm_name,adm_position,adm_account,adm_password,adm_status FROM administrator where adm_id = ? and ADM_POSITION=0;";
	private static final String GET_ONE_STMT2 =
			"SELECT adm_id,adm_name,adm_position,adm_account,adm_password,adm_status FROM administrator where adm_account = ? AND adm_password=?;";
	private static final String DELETE =
		"DELETE FROM administrator where adm_id = ?";
	private static final String UPDATE =
		"UPDATE administrator set adm_id=?, adm_name=?, adm_position=?, adm_account=?, adm_password=?, adm_status=? where adm_id = ?";

	@Override
	public void insert(AdministratorVo administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, administratorVO.getAdmId());
			pstmt.setString(2, administratorVO.getAdmName());
			pstmt.setString(3, administratorVO.getAdmPosition());
			pstmt.setString(4, administratorVO.getAdmAccount());
			pstmt.setString(5, administratorVO.getAdmPassword());
			pstmt.setInt(6, administratorVO.getAdmStatus());

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
	public void update(AdministratorVo administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, administratorVO.getAdmId());
			pstmt.setString(2, administratorVO.getAdmName());
			pstmt.setString(3, administratorVO.getAdmPosition());
			pstmt.setString(4, administratorVO.getAdmAccount());
			pstmt.setString(5, administratorVO.getAdmPassword());
			pstmt.setInt(6, administratorVO.getAdmStatus());

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
	public void delete(Integer administrator) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, administrator);

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
	public AdministratorVo findByPrimaryKey(Integer administrator) {

		AdministratorVo administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, administrator);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				administratorVO = new AdministratorVo();
				administratorVO.setAdmId( rs.getInt("adm_id"));
				administratorVO.setAdmName(rs.getString("adm_name"));
				administratorVO.setAdmPosition(rs.getString("adm_position"));
				administratorVO.setAdmAccount(rs.getString("adm_account"));
				administratorVO.setAdmPassword(rs.getString("adm_password"));
				administratorVO.setAdmStatus(rs.getInt("adm_status"));
				
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
		return administratorVO ;
	}
	public AdministratorVo findByPrimaryKey2(Integer administrator) {

		AdministratorVo administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT3);

			pstmt.setInt(1, administrator);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				administratorVO = new AdministratorVo();
				administratorVO.setAdmId( rs.getInt("adm_id"));
				administratorVO.setAdmName(rs.getString("adm_name"));
				administratorVO.setAdmPosition(rs.getString("adm_position"));
				administratorVO.setAdmAccount(rs.getString("adm_account"));
				administratorVO.setAdmPassword(rs.getString("adm_password"));
				administratorVO.setAdmStatus(rs.getInt("adm_status"));
				
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
		return administratorVO ;
	}
	public AdministratorVo findByKey(String admAccount,String admPassword) {

		AdministratorVo administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setString(1, admAccount);
			pstmt.setString(2, admPassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				administratorVO = new AdministratorVo();
				administratorVO.setAdmId( rs.getInt("adm_id"));
				administratorVO.setAdmName(rs.getString("adm_name"));
				administratorVO.setAdmPosition(rs.getString("adm_position"));
				administratorVO.setAdmAccount(rs.getString("adm_account"));
				administratorVO.setAdmPassword(rs.getString("adm_password"));
				administratorVO.setAdmStatus(rs.getInt("adm_status"));
				
			}

		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
		return administratorVO ;
	}

	@Override
	public List<AdministratorVo> getAll() {
		List<AdministratorVo> list = new ArrayList<AdministratorVo>();
		AdministratorVo administratorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// administratorVO �]�٬� Domain objects
				administratorVO = new AdministratorVo();
				administratorVO.setAdmId( rs.getInt("adm_id"));
				administratorVO.setAdmName(rs.getString("adm_name"));
				administratorVO.setAdmPosition(rs.getString("adm_position"));
				administratorVO.setAdmAccount(rs.getString("adm_account"));
				administratorVO.setAdmPassword(rs.getString("adm_password"));
				administratorVO.setAdmStatus(rs.getInt("adm_status"));
				list.add(administratorVO);
				
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

		AdministratorDaoImpl dao = new AdministratorDaoImpl();

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

		// �R��
//		dao.delete(2);

		// �d��
		AdministratorVo administratorVO3 = dao.findByPrimaryKey(1);
		System.out.print(administratorVO3.getAdmId() + ",");
		System.out.print(administratorVO3.getAdmName() + ",");
		System.out.print(administratorVO3.getAdmPosition() + ",");
		System.out.print(administratorVO3.getAdmAccount() + ",");
		System.out.print(administratorVO3.getAdmPassword() + ",");
		System.out.println(administratorVO3.getAdmStatus());
		System.out.println("---------------------");

		// �d��
		List<AdministratorVo> list = dao.getAll();
		for (AdministratorVo aEmp : list) {
			System.out.print(aEmp.getAdmId() + ",");
			System.out.print(aEmp.getAdmName() + ",");
			System.out.print(aEmp.getAdmPosition() + ",");
			System.out.print(aEmp.getAdmAccount() + ",");
			System.out.print(aEmp.getAdmPassword() + ",");
			System.out.print(aEmp.getAdmStatus());
			System.out.println();
		}
	}
}