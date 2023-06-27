package tw.idv.Seeker_Pool_Merge.fong.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tw.idv.Seeker_Pool_Merge.common.util.HikariCPUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.ArticleDao;
import tw.idv.Seeker_Pool_Merge.fong.vo.ArticleVo;

public class ArticleDaoImpl implements ArticleDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public int findTotalCount(int memId) {
		String sql = "select count(*) from collect where mem_id = ? ";
		return template.queryForObject(sql, Integer.class, memId);
	}

	@Override
	public List<ArticleVo> findByPage(int memId, int start, int pageSize) {
		String sql = "select article.AR_NO,AR_TITLE,AR_content from article,collect where article.AR_NO = collect.AR_NO and collect.MEM_ID = ? ";

		StringBuilder sb = new StringBuilder(sql);
		List<Integer> params = new ArrayList<Integer>(); // ?對應的值

		sb.append(" limit ? , ? "); // 分頁條建 從0開始，共幾個
		sql = sb.toString();
		params.add(memId);
		params.add(start);
		params.add(pageSize);

		return template.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class), params.toArray());
	}

	@Override
	public void deletColArtByMemIdAndArNo(Integer arNo, Integer memId) {
		String sql = "DELETE FROM collect WHERE MEM_ID = ? AND AR_NO = ?;";
		template.update(sql, memId, arNo);

	}

}
