package tw.idv.Seeker_Pool_Merge.jamie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.jamie.dao.CompanyMemberDao;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

public class CompanyMemberDaoImpl implements CompanyMemberDao {

//	private final JdbcTemplate template;
//	
//	@Autowired
//	public CompanyMemberDaoImpl(JdbcTemplate template) {
//		this.template = template;
//	}

	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public boolean registerCompanyMember(CompanyMemberVo companyMember) {
		String sql = """
				insert into company_member
				(COM_MEM_ACCOUNT, COM_MEM_PASSWORD, COM_NAME, TAX_NUM, COM_EMAIL, COM_TEL, COM_ADDRESS, COM_PICTURE)
				values (?, ?, ?, ?, ?, ?, ?, ?);
				""";
		template.update(sql, companyMember.getComMemAccount(), companyMember.getComMemPassword(),
				companyMember.getComName(), companyMember.getTaxNum(), companyMember.getComEmail(),
				companyMember.getComTel(), companyMember.getComAddress(), companyMember.getComPicture());

		return true;
	}

	@Override
	public boolean findComMemByAccount(String comMemAccount) {
		try {
			String sql = "select * from company_member where COM_MEM_ACCOUNT = ?";
			CompanyMemberVo companyMember = template.queryForObject(sql,
					new BeanPropertyRowMapper<CompanyMemberVo>(CompanyMemberVo.class), comMemAccount);
			// 有重複的話
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return false;
	}

	// 登入功能：根據帳號和密碼查詢用戶是否存在
	@Override
	public CompanyMemberVo findByAccountAndPassword(String comMemAccount, String comMemPassword) {
		try {
			String sql = "select * from company_member where COM_MEM_ACCOUNT = ? and COM_MEM_PASSWORD = ?";
//			System.out.println("Starting query for account: " + comMemAccount + ", password: " + comMemPassword);
			CompanyMemberVo companyMember = template.queryForObject(sql,
					new BeanPropertyRowMapper<CompanyMemberVo>(CompanyMemberVo.class), comMemAccount, comMemPassword);
	        return companyMember;
	    } catch (EmptyResultDataAccessException e) {
	    	System.out.println("EmptyResultDataAccessException caught: " + e.getMessage());
	        return null;
	    }
	}

	@Override
	public void updateCompanyMember(CompanyMemberVo companyMember) {
		String sql = """
				update company_member
				set COM_MEM_PASSWORD = ?,
				    COM_NAME = ?,
				    TAX_NUM = ?,
				    COM_EMAIL = ?,
				    COM_TEL = ?,
				    COM_ADDRESS = ?,
				    COM_PICTURE = ?
				where COM_MEM_ACCOUNT = ?;
				""";

		template.update(sql, companyMember.getComMemPassword(), companyMember.getComName(), companyMember.getTaxNum(),
				companyMember.getComEmail(), companyMember.getComTel(), companyMember.getComAddress(),
				companyMember.getComPicture(), companyMember.getComMemAccount());
	}

	@Override
	public void updateCompanyStatus(CompanyMemberVo companyMember) {
		String sql = "update company_member set COM_STATUS = ? where COM_MEM_ACCOUNT = ?;";
		template.update(sql, companyMember.getComStatus(), companyMember.getComMemAccount());
	}

	@Override
	public CompanyMemberVo getComMemById(int id) {
		try {
			String sql = "select * from company_member where COM_MEM_ID = ?";
			CompanyMemberVo companyMember = template.queryForObject(sql,
					new BeanPropertyRowMapper<CompanyMemberVo>(CompanyMemberVo.class), id);

			// 檢查endSuspendedTime
			Timestamp timeStamp = companyMember.getEndSuspendedTime();
			if (timeStamp != null) {
				// 將 Timestamp 轉換為想要的日期時間格式
				LocalDateTime localDateTime = timeStamp.toLocalDateTime();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String formattedTime = localDateTime.format(formatter);
				companyMember.setFormattedTime(formattedTime);
			}
			return companyMember;

		} catch (EmptyResultDataAccessException e) {
			// 沒有找到符合條件的記錄
			System.out.println("No record found for COM_MEM_ID: " + id);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<CompanyMemberVo> findAll() {
		String sql = """
				SELECT COM_MEM_ID, COM_NAME, COM_MEM_ACCOUNT, COM_STATUS
				FROM company_member;
				""";
		
		List<CompanyMemberVo> companyMembers = template.query(sql,
				new BeanPropertyRowMapper<CompanyMemberVo>(CompanyMemberVo.class));

		return companyMembers;
	}

	@Override
	public CompanyMemberVo getInfoForApplicants(int id) {
		String sql = """
				SELECT COM_NAME, COM_TEL, COM_ADDRESS, COM_PICTURE
				FROM company_member WHERE COM_MEM_ID = ?
				""";
		
		CompanyMemberVo companyMember = template.queryForObject(sql,
				new BeanPropertyRowMapper<CompanyMemberVo>(CompanyMemberVo.class), id);
		
		return companyMember;
	}
	
}
