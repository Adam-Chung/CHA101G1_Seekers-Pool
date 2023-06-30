package tw.idv.Seeker_Pool_Merge.JobCase.dao;

import java.util.List;

import org.hibernate.Session;

import tw.idv.Seeker_Pool_Merge.JobCase.util.HibernateUtil;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;

public interface JobOrderDao {

	// 新增
	public boolean insert(JobOrderVO jo);

	// 更新
	public void update(JobOrderVO jo);

	// 刪除
	public void delete(JobOrderVO jo);

	// 查詢
	public JobOrderVO selectByNo(Integer joNo);

	// 查詢全部
	public List<JobOrderVO> selectAll();

	// 查企業訂單
	public JobOrderVO selectByOrderComMemId(Integer comMemId);

	// 前台訂單查詢(企業)
	public List<ComOrderQueryVO> comMemIdCase(ComOrderQueryVO covo);

	// 前台單一訂單查詢(企業)
	public List<ComOrderQueryVO> selectComOrderOne(ComOrderQueryVO covo);

	// 更新企業已上架數量
	public List<ComOrderQueryVO> updateJobUploadNum(ComOrderQueryVO coqv);

	// 更新企業已置頂數量
	public List<ComOrderQueryVO> updateJobTopNum(ComOrderQueryVO coqv);

	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

}
