package tw.idv.Seeker_Pool_Merge.article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;

public class ArticleDaoImpl implements ArticleDao {

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

	private static final String GET_ALL_STMT = "SELECT AR_NO, MEM_ID ,AR_PUB_TIME ,AR_TITLE, AR_CONTENT, AR_IMG FROM article order by AR_NO";

	// 新增文章
	@Override
	public void insert(ArticleVo articleVO) {
		// TODO Auto-generated method stub

	}

	// 修改文章
	@Override
	public void update(ArticleVo articleVO) {
		// TODO Auto-generated method stub

	}

	// 找到所有文章
	@Override
	public List<ArticleVo> getAllArticles() {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		ArticleVo articleVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				articleVo = new ArticleVo();
				articleVo.setArNo(rs.getInt("AR_NO"));
				articleVo.setMemId(rs.getInt("MEM_ID"));
				articleVo.setArPubTime(rs.getTimestamp("AR_PUB_TIME"));
				articleVo.setArTitle(rs.getString("AR_TITLE"));
				articleVo.setArContent(rs.getString("AR_CONTENT"));
				articleVo.setArImg(rs.getBytes("AR_IMG"));

				list.add(articleVo); // Store the row in the list
			}

			// Handle any driver errors
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

	// 透過文章編號找到文章
	@Override
	public ArticleVo getArticleById(Integer arNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVo articleVo = null;

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM ARTICLE WHERE AR_NO = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, arNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleVo = new ArticleVo();
				articleVo.setArNo(rs.getInt("AR_NO"));
				articleVo.setMemId(rs.getInt("MEM_ID"));
				articleVo.setArPubTime(rs.getTimestamp("AR_PUB_TIME"));
				articleVo.setArTitle(rs.getString("AR_TITLE"));
				articleVo.setArContent(rs.getString("AR_CONTENT"));
				articleVo.setArImg(rs.getBytes("AR_IMG"));

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

		return articleVo;
	}

	@Override
	public ArticleVo getSaveCountByMemId(Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVo articleVo = null;

		try {
			con = ds.getConnection();
			String query = "SELECT COUNT (*) FROM ARTICLE WHERE MEM_ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleVo = new ArticleVo();
				articleVo.setArNo(rs.getInt("AR_NO"));
				articleVo.setMemId(rs.getInt("MEM_ID"));
				articleVo.setArPubTime(rs.getTimestamp("AR_PUB_TIME"));
				articleVo.setArTitle(rs.getString("AR_TITLE"));
				articleVo.setArContent(rs.getString("AR_CONTENT"));
				articleVo.setArImg(rs.getBytes("AR_IMG"));

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

		return articleVo;
	}

}