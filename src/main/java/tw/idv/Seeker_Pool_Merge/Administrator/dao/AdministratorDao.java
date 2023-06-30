package tw.idv.Seeker_Pool_Merge.Administrator.dao;

import java.util.List;

import org.hibernate.Session;

import tw.idv.Seeker_Pool_Merge.Administrator.util.HibernateUtil;
import tw.idv.Seeker_Pool_Merge.Administrator.vo.AdministratorVO;

public interface AdministratorDao {
	
	// 新增
	public boolean insert(AdministratorVO adm);
	
	// 更新
	public void update(AdministratorVO adm);

	// 刪除
	public void delete(AdministratorVO adm);

	// 查詢
	public AdministratorVO findByPrimaryKey(Integer admId);

	// 查詢全部
	public List<AdministratorVO> getAll();
	
	// 查姓名
	public AdministratorVO selectByUsername(String admName);
	
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

}
