package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.MemberDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;


public class MemberDaoImpl implements MemberDao{

	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);
	//測試用
//	boolean isUsingPool = HikariCPUtil.isUsingConnectionPool();


	public MemberVo getMemberById(int id) {
		
		try {
			// 定義SQL語句
			String sql = "select * from member where mem_id = ?";
			
			// ====connection寫法=====
//			Connection connection = dataSource.getConnection();
//			//獲取執行SQL語句的對象
//			PreparedStatement pstmt = connection.prepareStatement(sql);
//			//給?賦值
//			pstmt.setInt(1, id);
//			member = new MemberVo();
//			//執行SQL
//			ResultSet rs = pstmt.executeQuery(); 
//			rs.next();
//			member.setMemName(rs.getString(3));
			
			//用springframework寫法取代以上 直接將DB來的資料封裝成物件，不影響結構
			MemberVo member = template.queryForObject(sql, new BeanPropertyRowMapper<MemberVo>(MemberVo.class),id);
			return member;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean registerMember(MemberVo member) {
		String sql = """
				INSERT INTO member
				( MEM_PIC, MEM_ADDRESS, MEM_NAME, MEM_GENDER, MEM_EMAIL, MEM_MOBILE,  MEM_ACCOUNT, MEM_PASSWORD, NL_SUB, MEM_STATUS, CV_STATUS) 
				VALUES (?,?,?,?,?,?,?,?,?,?,?)""";
		template.update(sql,
				member.getMemPic(),
				member.getMemAddress(),
				member.getMemName(),
				member.getMemGender(),
				member.getMemEmail(),
				member.getMemMobile(),
				member.getMemAccount(),
				member.getMemPassword(),
				member.getNlSub(),
				0,0);
		
		return true;
	}


	@Override
	public Boolean findByMemAccount(String memAccount) {
		try {
			String sql = "select * from member where mem_account = ?";
			template.queryForObject(sql, new BeanPropertyRowMapper<MemberVo>(MemberVo.class), memAccount);
			//有重複帳號
			return true;
		} catch (Exception e) {
			//沒有重複帳號
			return false;
		}
	}


	@Override
	public MemberVo findByAccountAndPassword(String memAccount, String memPassword) {
		try {
			String sql = "select * from member where mem_account = ? and mem_password = ?";
			MemberVo memberlogin = template.queryForObject(sql, new BeanPropertyRowMapper<MemberVo>(MemberVo.class), memAccount, memPassword);
			
			//測試用
//			if (isUsingPool) {
//			    System.out.println("已使用连接池");
//			} else {
//			    System.out.println("未使用连接池");
//			}
			
			//有此人 登入成功
			return memberlogin;
			
		} catch (Exception e) {
			return null;
		}
	}


	@Override
	public void updateMember(MemberVo member) {
		String sql = """
				update member set 
				MEM_PIC = ?,
				MEM_ADDRESS = ?,
				MEM_NAME = ?,
				MEM_GENDER = ?,
				MEM_EMAIL = ?,
				MEM_MOBILE = ?,
				MEM_PASSWORD = ?,
				NL_SUB = ?,
				MEM_STATUS = ?,
				CV_STATUS = ?,
				MEM_COLLEGE = ?,
				MEM_DEPARTMENT = ?,
				MEM_LANG = ?,
				MEM_BIO = ?,
				SK_NO = ?				
				WHERE MEM_ACCOUNT = ?;
				""";
		
		template.update(sql,
				member.getMemPic(),
				member.getMemAddress(),
				member.getMemName(),
				member.getMemGender(),
				member.getMemEmail(),
				member.getMemMobile(),
				member.getMemPassword(),
				member.getNlSub(),
				member.getMemStatus(),
				member.getCvStatus(),
				member.getMemCollege(),
				member.getMemDepartment(),
				member.getMemLang(),
				member.getMemBio(),
				member.getSkNo(),
				member.getMemAccount());
	}
}
