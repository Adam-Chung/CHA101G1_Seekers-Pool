package tw.idv.Seeker_Pool_Merge.collect.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;

public class CollectDaoImpl implements CollectDao {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = HikariCPUtil.getDataSource();
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	private static final String INSERT_STMT = "INSERT INTO COLLECT (MEM_ID,AR_NO) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM COLLECT WHERE MEM_ID = ? and ar_no = ?";

	// 新增一筆收藏文章的資料
	@Override
	public CollectVo addCollect(CollectVo collectVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, collectVo.getMemId());
			pstmt.setInt(2, collectVo.getArNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			// 釋放資源
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
		return collectVo;
	}

	// 查詢會員收藏過的文章
	@Override
	public CollectVo selectColArticleByMemId(Integer memId, Integer arNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CollectVo collectVo = null;

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM COLLECT WHERE MEM_ID = ? and ar_no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memId);
			pstmt.setInt(2, arNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				collectVo = new CollectVo();
				collectVo.setMemId(rs.getInt("MEM_ID"));
				collectVo.setMemId(rs.getInt("AR_NO"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("找不到這個資料庫" + se.getMessage());
		} finally {
			// 關閉資源的程式碼
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

		return collectVo;
	}

	// 會員取消收藏文章
	@Override
	public void deleteColArticle(Integer memId, Integer arNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, arNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			// 釋放資源
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

}
