package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.CompanyDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;

public class CompanyDaoImpl implements CompanyDao {

	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public List<CompanyMemberShowVo> findBlockComsByMemId(Integer memId) {
		String sql = "select b.com_mem_id, com_name from block_company b, company_member c where mem_id = ? and b.com_mem_id = c.com_mem_id;";
		List<CompanyMemberShowVo> blockComs = template.query(sql,
				new BeanPropertyRowMapper<CompanyMemberShowVo>(CompanyMemberShowVo.class), memId);
		return blockComs;
	}


	@Override
	public CompanyMemberShowVo findComByName(String addCompanyName) {
		String sql = "select com_mem_id, com_name from company_member where com_name = ?";
		try {
			CompanyMemberShowVo CompanyMember = template.queryForObject(sql,
					new BeanPropertyRowMapper<CompanyMemberShowVo>(CompanyMemberShowVo.class), addCompanyName);
			return CompanyMember;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean findBlockComByMemIdAndComMemId(Integer memId, Integer comMemId) {
		try {
			String sql = "select * from block_company where MEM_ID = ? and COM_MEM_ID = ?;";
			template.queryForObject(sql, new BeanPropertyRowMapper<CompanyMemberShowVo>(CompanyMemberShowVo.class), memId, comMemId);
			//屏蔽企業重複
			return false;
		} catch (Exception e) {
			//屏蔽企業沒重複
			return true;
		}
	}

	@Override
	public void addBlockComBy(Integer memId, Integer comMemId) {
		String sql = "insert into block_company(MEM_ID, COM_MEM_ID) values(?, ?);";
		template.update(sql, memId, comMemId);
	}

	@Override
	public void deletBlockComByName(Integer memId, String deleteBlockCom) {
		String sql = "select DISTINCT  c.COM_MEM_ID from block_company b, company_member c where c.com_name = ?;";
		Integer comMemId = template.queryForObject(sql,Integer.class, deleteBlockCom);
		
		String sql2 = "DELETE FROM block_company WHERE mem_id = ? and COM_MEM_ID = ?;";
		template.update(sql2, memId, comMemId);
	}


	@Override
	public String getComNameBycomId(Integer comId) {
		String sql = "select com_name from company_member where com_mem_id = ?";
		try {
			String comName = template.queryForObject(sql, String.class, comId);
			return comName;
		} catch (Exception e) {
			return null;
		}
	}
	
}
