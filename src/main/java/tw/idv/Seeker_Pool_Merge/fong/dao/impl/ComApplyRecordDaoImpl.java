package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.ComApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;

public class ComApplyRecordDaoImpl implements ComApplyRecordDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public void addInterviewInvite(Integer memId, CompanyMemberShowVo company, Integer jobNo) {
		String sql1 = "select count(*) from  apply_record where COM_MEM_ID = ? and  mem_id = ? and job_no = ?;";
		Integer count = template.queryForObject(sql1, Integer.class, company.getComMemId(), memId, jobNo);
		if (count > 0) {
			String sql2 = "update apply_record set hire_status = 3 where COM_MEM_ID = ? and  mem_id = ? and job_no = ?;";
			template.update(sql2, company.getComMemId(), memId, jobNo);
			
		} else {
			String sql2 = "INSERT INTO apply_record (COM_MEM_ID, mem_id, job_no, hire_status) VALUES ( ? , ? , ? ,3);";
			template.update(sql2, company.getComMemId(), memId, jobNo);
		}
	}

}
