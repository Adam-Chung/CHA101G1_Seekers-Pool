package tw.idv.Seeker_Pool_Merge.Administrator.dao.Impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tw.idv.Seeker_Pool_Merge.Administrator.dao.AdministratorDao;
import tw.idv.Seeker_Pool_Merge.Administrator.vo.AdministratorVO;


@Repository
public class AdministratorDaoImpl implements AdministratorDao {
	
	@PersistenceContext
	private Session session;

	@Transactional
	@Override
	public boolean insert(AdministratorVO adm) {

//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.save(adm);
//			tx.commit();
//		}
//		return true;
		
		try {
//			Transaction tx = session.beginTransaction();
			session.save(adm);
//			tx.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
		
	}

	@Transactional
	@Override
	public void update(AdministratorVO adm) {

//		try (Session session = getSession()) {
		try {
//			Transaction tx = session.beginTransaction();
			session.update(adm);
			System.out.println("我很對================");
			System.out.println("我是servlet=====" + adm);
//			tx.commit();
		}catch (Exception e) {
			System.out.println("我錯了update=====================");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	
	@Override
	public void delete(AdministratorVO adm) {
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.delete(adm);
//			tx.commit();
//		}
	}

//	@Override
//	public AdministratorVO findByPrimaryKey(Integer admId) {
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//		Object obj = session.get(AdministratorVO.class, admId);
//		
//System.out.println("----------------------------------");	
//System.out.println(obj);	
//System.out.println("----------------------------------");	
//			AdministratorVO admVo = (AdministratorVO)obj;
//			tx.commit();
//			return admVo;
//		}
//	}
	
//	@Override
//	public AdministratorVO findByPrimaryKey(Integer admId) {
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			Criteria cr = session.createCriteria(AdministratorVO.class);
//			cr.add(Restrictions.eq("admId",admId));
//			List<AdministratorVO> list = cr.list();
//			System.out.println("----------------------------------");
//			System.out.println(list.get(0).getAdmName());
//			
//			
//				
////			System.out.println(obj);	
//			System.out.println("----------------------------------");	
////			AdministratorVO admVo = (AdministratorVO)obj;
//			tx.commit();
//			return null;
//		}
//	}
	
	@Override
	public AdministratorVO findByPrimaryKey(Integer admId) {
		try  {
//			Transaction tx = session.beginTransaction();
			AdministratorVO admVo = session.get(AdministratorVO.class, admId);
//			tx.commit();
			return admVo;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			AdministratorVO admVo = session.get(AdministratorVO.class, admId);
//			tx.commit();
//			return admVo;
//		}
		
	}

	
	

	@Override
	public List<AdministratorVO> getAll() {
//		System.out.println("impl~~~~~~~~~~~~`");
//		try (Session session = getSession()) {
//			System.out.println(new Date());
//			Transaction tx=session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<AdministratorVO> cq = cb.createQuery(AdministratorVO.class);
//			Root<AdministratorVO> root = cq.from(AdministratorVO.class);
//			cq.select(root);
//			System.out.println(new Date());
//			Query<AdministratorVO> query = session.createQuery(cq);
//			List<AdministratorVO> resultList=query.getResultList();
//			System.out.println(new Date());
//			tx.commit();
//			return resultList;
//		}
		
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			final String hql ="from AdministratorVO";
			Query<AdministratorVO> query = session.createQuery(hql,AdministratorVO.class);
			List<AdministratorVO> resultList = query.getResultList();

			tx.commit();
			return resultList;
		}
		
	}


	@Override
	public AdministratorVO selectByUsername(String admName) {
//		try (Session session = getSession()) {
//			Transaction tx=session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<AdministratorVO> cq = cb.createQuery(AdministratorVO.class);
//			Root<AdministratorVO> root = cq.from(AdministratorVO.class);
//			cq.where(cb.equal(root.get("admName"), admName));
//			AdministratorVO userName=session.createQuery(cq).uniqueResult();
//			tx.commit();
//			return userName;
//		}
		
		try{
			Transaction tx=session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<AdministratorVO> cq = cb.createQuery(AdministratorVO.class);
			Root<AdministratorVO> root = cq.from(AdministratorVO.class);
			cq.where(cb.equal(root.get("admName"), admName));
			AdministratorVO userName=session.createQuery(cq).uniqueResult();
			tx.commit();
			return userName;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
