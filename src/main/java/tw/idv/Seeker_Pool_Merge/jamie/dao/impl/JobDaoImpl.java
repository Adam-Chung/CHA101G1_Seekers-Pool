package tw.idv.Seeker_Pool_Merge.jamie.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.jamie.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.jamie.vo.JobVo;

public class JobDaoImpl implements JobDao {
	
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public List<JobVo> findJobsByComMemId(int comMemId) {
		String sql = """
				SELECT JOB_NO, JOB_NAME
				FROM job
				WHERE COM_MEM_ID = ? AND JOB_STATUS = 1
				""";
		
		List<JobVo> jobs = template.query(sql, new BeanPropertyRowMapper<JobVo>(JobVo.class), comMemId);
		
		return jobs;
	}

	@Override
	public JobVo findJobName(int jobNo, int comMemId) {
		String sql = "SELECT job_name FROM job WHERE COM_MEM_ID = ? AND JOB_NO = ?";
		
		JobVo jobName =
				template.queryForObject(sql, new BeanPropertyRowMapper<JobVo>(JobVo.class), comMemId, jobNo);
		
		return jobName;
	}

}
