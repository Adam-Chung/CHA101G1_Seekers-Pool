package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.JobVo;

public class JobDaoImpl implements JobDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public int findTotalCount(int memId) {
		String sql = "select count(*) from collect_job where mem_id = ?; ";
		return template.queryForObject(sql, Integer.class, memId);
	}

	@Override
	public List<JobVo> findByPage(int memId, int start, int pageSize) {
		String sql = "select JOB.JOB_NO, JOB_NAME, JOB_CONTENT, c.COLLECT_DATE from JOB, COLLECT_JOB c where JOB.JOB_NO = c.JOB_NO AND MEM_ID = ? limit ? , ? ;"
				+ " ";
		return template.query(sql, new BeanPropertyRowMapper<JobVo>(JobVo.class), memId, start, pageSize);
	}

	@Override
	public void deletColJobByMemIdAndArNo(Integer jobNo, Integer memId) {
		String sql = "DELETE FROM collect_job WHERE MEM_ID = ? AND JOB_NO = ?;";
		template.update(sql, memId, jobNo);
	}

	@Override
	public String getJobNameByJobNo(Integer jobNo) {
		String sql = "select job_name from job where job_no = ?; ";
		return template.queryForObject(sql, String.class, jobNo);
	}

}
