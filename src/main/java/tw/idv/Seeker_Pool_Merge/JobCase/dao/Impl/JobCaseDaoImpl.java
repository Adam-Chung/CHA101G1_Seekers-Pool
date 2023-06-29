package tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobCaseDao;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

@Repository
public class JobCaseDaoImpl implements JobCaseDao {
	
	
	@PersistenceContext
	private Session session;

	@Transactional
	@Override
	public boolean insert(JobCaseVO jc) {
		try  {
//			try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
			session.save(jc);
//			tx.commit();
			return true;
		} catch (Exception e) {
			// 錯誤處理代碼，例如記錄錯誤日誌或回滾事務
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public void update(JobCaseVO jc) {
		try{
//			Transaction tx = session.beginTransaction();
			session.update(jc);
//			tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.update(jc);
//			tx.commit();
//		}
	}

	
	@Override
	public void delete(JobCaseVO jc) {
//
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.delete(jc);
//			tx.commit();
//		}
	}

	@Transactional
	@Override
	public JobCaseVO selectByNo(Integer jcNo) {
		
		
		try {
//			Transaction tx = session.beginTransaction();
//			JobCaseVO jcno = session.get(JobCaseVO.class, jcNo);

			final String hql = "from JobCaseVO where jcNo = :jcNo";
			Query<JobCaseVO> query = session.createQuery(hql, JobCaseVO.class);
			query.setParameter("jcNo", jcNo);
			JobCaseVO resultList = query.getSingleResult();
			
//			tx.commit();
			return resultList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			JobCaseVO jcno = session.get(JobCaseVO.class, jcNo);
//
////			final String hql = "from JobCaseVO where jcNo = :jcNo ";
////			Query<JobCaseVO> query = session.createQuery(hql, JobCaseVO.class);
////			query.setParameter("jcNo", jcNo);
////			JobCaseVO resultList = query.getSingleResult();
//			
//			tx.commit();
//			return jcno;
//		}
	}

	@Transactional
	@Override
	public List<JobCaseVO> selectAll() {
		try {
//			Transaction tx = session.beginTransaction();
			final String hql ="from JobCaseVO";
			Query<JobCaseVO> query = session.createQuery(hql,JobCaseVO.class);
			List<JobCaseVO> resultList = query.getResultList();

//			tx.commit();
			return resultList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql ="from JobCaseVO";
//			Query<JobCaseVO> query = session.createQuery(hql,JobCaseVO.class);
//			List<JobCaseVO> resultList = query.getResultList();
//			
//			tx.commit();
//			return resultList;
//		}
	}

	@Transactional
	@Override
	public JobCaseVO selectByCaseName(String jcName) {
		try {
//			Transaction tx = session.beginTransaction();
			Query<JobCaseVO> query = session.createQuery("FROM JobCaseVO WHERE jcName = :JC_NAME", JobCaseVO.class);
			query.setParameter("JC_NAME", jcName);
			JobCaseVO result = query.uniqueResult();
//			tx.commit();
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			Query<JobCaseVO> query = session.createQuery("FROM JobCaseVO WHERE jcName = :JC_NAME", JobCaseVO.class);
//			query.setParameter("name", jcName);
//			JobCaseVO result = query.uniqueResult();
//			tx.commit();
//			return result;
//		}
	}
	
	@Transactional
	@Override
	public List<ComOrderQueryVO> selectOrderCase(ComOrderQueryVO covo) {
		List<ComOrderQueryVO> list = new ArrayList<>();
		try{
//			Transaction tx = session.beginTransaction();
			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n" 
					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n" 
					+ "ORDER BY jo.JO_NO";
		
			Query<Object[]> query = session.createNativeQuery(hql);
			List<Object[]> results = query.getResultList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Object[] result : results) {
				ComOrderQueryVO CoVO = new ComOrderQueryVO();
				CoVO.setJoNo((Integer) result[0]);
				CoVO.setJcName((String) result[1]);
				CoVO.setJcAvailableNum((Integer) result[2]);
				CoVO.setJcTop((Integer) result[3]);
				CoVO.setJcExpTime((Integer) result[4]);
				Timestamp timestamp = (Timestamp) result[5];
				String formattedDate = dateFormat.format(timestamp);
				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
				CoVO.setJcDeadline(convertedTimestamp);
				CoVO.setJcPrice((Integer) result[6]);
				CoVO.setComName((String) result[7]);
				CoVO.setComMemId((Integer) result[8]);
				CoVO.setJcNo((Integer) result[9]);
				list.add(CoVO);
			}
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
//		List<ComOrderQueryVO> list = new ArrayList<>();
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
//					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n" 
//					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n" 
//					+ "ORDER BY jo.JO_NO";
//			
//			Query<Object[]> query = session.createNativeQuery(hql);
//			List<Object[]> results = query.getResultList();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (Object[] result : results) {
//				ComOrderQueryVO CoVO = new ComOrderQueryVO();
//				CoVO.setJoNo((Integer) result[0]);
//				CoVO.setJcName((String) result[1]);
//				CoVO.setJcAvailableNum((Integer) result[2]);
//				CoVO.setJcTop((Integer) result[3]);
//				CoVO.setJcExpTime((Integer) result[4]);
//				Timestamp timestamp = (Timestamp) result[5];
//				String formattedDate = dateFormat.format(timestamp);
//				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
//				CoVO.setJcDeadline(convertedTimestamp);
//				CoVO.setJcPrice((Integer) result[6]);
//				CoVO.setComName((String) result[7]);
//				CoVO.setComMemId((Integer) result[8]);
//				CoVO.setJcNo((Integer) result[9]);
//				list.add(CoVO);
//			}
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;

	}
	
	@Transactional
	@Override
	public List<ComOrderQueryVO> selectOrderNo(Integer joNo) {
		
		List<ComOrderQueryVO> list = new ArrayList<>();
		
		try {
//			Transaction tx = session.beginTransaction();
			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n" 
					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n" 
					+ "WHERE jo.JO_NO = :JO_NO";
			
			Query<Object[]> query = session.createNativeQuery(hql);
			query.setParameter("JO_NO", joNo); 
			List<Object[]> results = query.getResultList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Object[] result : results) {
				ComOrderQueryVO CoVO = new ComOrderQueryVO();
				CoVO.setJoNo((Integer) result[0]);
				CoVO.setJcName((String) result[1]);
				CoVO.setJcAvailableNum((Integer) result[2]);
				CoVO.setJcTop((Integer) result[3]);
				CoVO.setJcExpTime((Integer) result[4]);
				Timestamp timestamp = (Timestamp) result[5];
				String formattedDate = dateFormat.format(timestamp);
				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
				CoVO.setJcDeadline(convertedTimestamp);
				CoVO.setJcPrice((Integer) result[6]);
				CoVO.setComName((String) result[7]);
				CoVO.setComMemId((Integer) result[8]);
				CoVO.setJcNo((Integer) result[9]);
				list.add(CoVO);
			}
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
//					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n" 
//					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n" 
//					+ "WHERE jo.JO_NO = :JO_NO";
//			
//			Query<Object[]> query = session.createNativeQuery(hql);
//			query.setParameter("JO_NO", joNo); 
//			List<Object[]> results = query.getResultList();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (Object[] result : results) {
//				ComOrderQueryVO CoVO = new ComOrderQueryVO();
//				CoVO.setJoNo((Integer) result[0]);
//				CoVO.setJcName((String) result[1]);
//				CoVO.setJcAvailableNum((Integer) result[2]);
//				CoVO.setJcTop((Integer) result[3]);
//				CoVO.setJcExpTime((Integer) result[4]);
//				Timestamp timestamp = (Timestamp) result[5];
//				String formattedDate = dateFormat.format(timestamp);
//				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
//				CoVO.setJcDeadline(convertedTimestamp);
//				CoVO.setJcPrice((Integer) result[6]);
//				CoVO.setComName((String) result[7]);
//				CoVO.setComMemId((Integer) result[8]);
//				CoVO.setJcNo((Integer) result[9]);
//				list.add(CoVO);
//			}
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
		
	}

}
