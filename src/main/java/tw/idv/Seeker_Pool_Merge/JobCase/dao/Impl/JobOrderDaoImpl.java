package tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;

@Transactional
@Repository
public class JobOrderDaoImpl implements JobOrderDao {
	
	@PersistenceContext
	private Session session;


	@Override
	public boolean insert(JobOrderVO jo) {
		try{
//			Transaction tx = session.beginTransaction();
			session.save(jo);
//			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.save(jo);
//			tx.commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}

	}

	
	@Override
	public void update(JobOrderVO jo) {
		try {
//			Transaction tx = session.beginTransaction();
			session.update(jo);
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.update(jo);
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void delete(JobOrderVO jo) {
		try{
//			Transaction tx = session.beginTransaction();
			session.delete(jo);
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.delete(jo);
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	@Override
	public JobOrderVO selectByNo(Integer joNo) {
		try {
//			Transaction tx = session.beginTransaction();
			JobOrderVO jono = session.get(JobOrderVO.class, joNo);
//			tx.commit();
			return jono;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			JobOrderVO jono = session.get(JobOrderVO.class, joNo);
//			tx.commit();
//			return jono;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;

	}

	@Override
	public List<JobOrderVO> selectAll() {
		try{
//			Transaction tx = session.beginTransaction();
			// 初始化 jobOrders 集合
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<JobOrderVO> cq = cb.createQuery(JobOrderVO.class);
			Root<JobOrderVO> root = cq.from(JobOrderVO.class);
			cq.select(root);
			Query<JobOrderVO> query = session.createQuery(cq);
			List<JobOrderVO> resultList = query.getResultList();
//			tx.commit();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			// 初始化 jobOrders 集合
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<JobOrderVO> cq = cb.createQuery(JobOrderVO.class);
//			Root<JobOrderVO> root = cq.from(JobOrderVO.class);
//			cq.select(root);
//			Query<JobOrderVO> query = session.createQuery(cq);
//			List<JobOrderVO> resultList = query.getResultList();
//			tx.commit();
//			return resultList;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public JobOrderVO selectByOrderComMemId(Integer comMemId) {
		try{
//			Transaction tx = session.beginTransaction();
			JobOrderVO commemid = session.get(JobOrderVO.class, comMemId);
//			tx.commit();
			return commemid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			JobOrderVO commemid = session.get(JobOrderVO.class, comMemId);
//			tx.commit();
//			return commemid;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// 前台刊登訂單查詢(企業)companyjobordersearch.html
	@Override
	public List<ComOrderQueryVO> comMemIdCase(ComOrderQueryVO covo) {
		List<ComOrderQueryVO> list = new ArrayList<>();
		try{
//			Transaction tx = session.beginTransaction();
			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n"
					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n"
					+ "WHERE jo.COM_MEM_ID = :comMemId";

			Query<Object[]> query = session.createNativeQuery(hql);
			query.setParameter("comMemId", covo.getComMemId());
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
//					+ "WHERE jo.COM_MEM_ID = :comMemId";
//			
//			Query<Object[]> query = session.createNativeQuery(hql);
//			query.setParameter("comMemId", covo.getComMemId());
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

	// 前台刊登訂單查詢(企業)companyjobordersearch.html
	@Override
	public List<ComOrderQueryVO> selectComOrderOne(ComOrderQueryVO covo) {
		List<ComOrderQueryVO> list = new ArrayList<>();
		try  {
//			Transaction tx = session.beginTransaction();
			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n"
					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n"
					+ "WHERE jo.COM_MEM_ID = :comMemId and jo.JO_NO = :joNo";

			Query<Object[]> query = session.createNativeQuery(hql);
			query.setParameter("comMemId", covo.getComMemId());
			query.setParameter("joNo", covo.getJoNo());
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
//					+ "WHERE jo.COM_MEM_ID = :comMemId and jo.JO_NO = :joNo";
//			
//			Query<Object[]> query = session.createNativeQuery(hql);
//			query.setParameter("comMemId", covo.getComMemId());
//			query.setParameter("joNo", covo.getJoNo());
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

	/*
	 * 更新企業已上架數量
	 */
	public List<ComOrderQueryVO> updateJobUploadNum(ComOrderQueryVO coqv) {
	    try{
//	        Transaction tx = session.beginTransaction();
	        
	        final String sql1 = "UPDATE job_order AS jo "
	                + "JOIN ( "
	                + "    SELECT j.COM_MEM_ID, SUM(j.JOB_UPLOAD) AS upload_Num "
	                + "    FROM job AS j "
	                + "    WHERE j.JOB_UPLOAD = 1 "
	                + "    GROUP BY j.COM_MEM_ID "
	                + ") AS d ON jo.COM_MEM_ID = d.COM_MEM_ID "
	                + "SET jo.JOB_PUBLISHED_NUM = d.upload_Num "
	                + "WHERE jo.COM_MEM_ID = ?";
	        
	        Query<?> query = session.createNativeQuery(sql1);
	        query.setParameter(1, coqv.getComMemId());
	        query.executeUpdate();
	        
	        final String sql2 = "SELECT j.COM_MEM_ID, jo.JOB_PUBLISHED_NUM, jo.JOB_PUBLISHED_TOP_NUM, jc.JOB_AVAILABLE_NUM, jc.JC_TOP "
	                + "FROM job AS j "
	                + "JOIN job_order AS jo ON j.JO_NO = jo.JO_NO "
	                + "JOIN job_case AS jc ON jc.JC_NO = jo.JC_NO "
	                + "WHERE j.COM_MEM_ID = ?";
	        
	        Query<?> selectQuery = session.createNativeQuery(sql2);
	        selectQuery.setParameter(1, coqv.getComMemId());
	        
	        List<?> results = selectQuery.getResultList();
	        List<ComOrderQueryVO> list = new ArrayList<>();
	        for (Object result : results) {
	            Object[] row = (Object[]) result;
	            ComOrderQueryVO coVO = new ComOrderQueryVO();
	            coVO.setComMemId(Integer.parseInt(row[0].toString()));
	            coVO.setJobPublishedNum(Integer.parseInt(row[1].toString()));
	            coVO.setJobPublishedTopNum(Integer.parseInt(row[2].toString()));
	            coVO.setJcAvailableNum(Integer.parseInt(row[3].toString()));
	            coVO.setJcTop(Integer.parseInt(row[4].toString()));
	            list.add(coVO);
	        }

//	        tx.commit();
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
//	    try (Session session = getSession()) {
//	    	Transaction tx = session.beginTransaction();
//	    	
//	    	final String sql1 = "UPDATE job_order AS jo "
//	    			+ "JOIN ( "
//	    			+ "    SELECT j.COM_MEM_ID, SUM(j.JOB_UPLOAD) AS upload_Num "
//	    			+ "    FROM job AS j "
//	    			+ "    WHERE j.JOB_UPLOAD = 1 "
//	    			+ "    GROUP BY j.COM_MEM_ID "
//	    			+ ") AS d ON jo.COM_MEM_ID = d.COM_MEM_ID "
//	    			+ "SET jo.JOB_PUBLISHED_NUM = d.upload_Num "
//	    			+ "WHERE jo.COM_MEM_ID = ?";
//	    	
//	    	Query<?> query = session.createNativeQuery(sql1);
//	    	query.setParameter(1, coqv.getComMemId());
//	    	query.executeUpdate();
//	    	
//	    	final String sql2 = "SELECT j.COM_MEM_ID, jo.JOB_PUBLISHED_NUM, jo.JOB_PUBLISHED_TOP_NUM, jc.JOB_AVAILABLE_NUM, jc.JC_TOP "
//	    			+ "FROM job AS j "
//	    			+ "JOIN job_order AS jo ON j.JO_NO = jo.JO_NO "
//	    			+ "JOIN job_case AS jc ON jc.JC_NO = jo.JC_NO "
//	    			+ "WHERE j.COM_MEM_ID = ?";
//	    	
//	    	Query<?> selectQuery = session.createNativeQuery(sql2);
//	    	selectQuery.setParameter(1, coqv.getComMemId());
//	    	
//	    	List<?> results = selectQuery.getResultList();
//	    	List<ComOrderQueryVO> list = new ArrayList<>();
//	    	for (Object result : results) {
//	    		Object[] row = (Object[]) result;
//	    		ComOrderQueryVO coVO = new ComOrderQueryVO();
//	    		coVO.setComMemId(Integer.parseInt(row[0].toString()));
//	    		coVO.setJobPublishedNum(Integer.parseInt(row[1].toString()));
//	    		coVO.setJobPublishedTopNum(Integer.parseInt(row[2].toString()));
//	    		coVO.setJcAvailableNum(Integer.parseInt(row[3].toString()));
//	    		coVO.setJcTop(Integer.parseInt(row[4].toString()));
//	    		list.add(coVO);
//	    	}
//	    	
//	    	tx.commit();
//	    	return list;
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    	return new ArrayList<>();
//	    }
	}

	// 更新企業已置頂數量
	public List<ComOrderQueryVO> updateJobTopNum(ComOrderQueryVO coqv) {
	    List<ComOrderQueryVO> list = new ArrayList<>();
		try {
//			Transaction tx = session.beginTransaction();
			final String sql1 = "UPDATE job_order AS jo "
					+ "JOIN ( "
			        + "    SELECT j.COM_MEM_ID, SUM(j.JOB_TOP) AS top_num "
			        + "    FROM job AS j "
			        + "    WHERE j.JOB_TOP = 1 "
			        + "    GROUP BY j.COM_MEM_ID "
			        + ") AS d ON jo.COM_MEM_ID = d.COM_MEM_ID "
			        + "SET jo.JOB_PUBLISHED_TOP_NUM = d.top_num "
			        + "WHERE jo.COM_MEM_ID = ?";
			 Query<?> query = session.createNativeQuery(sql1);
		        query.setParameter(1, coqv.getComMemId());
		        query.executeUpdate();
		        
		        final String sql2 = "SELECT j.COM_MEM_ID, jo.JOB_PUBLISHED_NUM, jo.JOB_PUBLISHED_TOP_NUM, jc.JOB_AVAILABLE_NUM, jc.JC_TOP "
		                + "FROM job AS j "
		                + "JOIN job_order AS jo ON j.JO_NO = jo.JO_NO "
		                + "JOIN job_case AS jc ON jc.JC_NO = jo.JC_NO "
		                + "WHERE j.COM_MEM_ID = ?";
		        
		        Query<?> selectQuery = session.createNativeQuery(sql2);
		        selectQuery.setParameter(1, coqv.getComMemId());
		        
		        List<?> results = selectQuery.getResultList();
		        List<ComOrderQueryVO> list1 = new ArrayList<>();
		        for (Object result : results) {
		            Object[] row = (Object[]) result;
		            ComOrderQueryVO coVO = new ComOrderQueryVO();
		            coVO.setComMemId(Integer.parseInt(row[0].toString()));
		            coVO.setJobPublishedNum(Integer.parseInt(row[1].toString()));
		            coVO.setJobPublishedTopNum(Integer.parseInt(row[2].toString()));
		            coVO.setJcAvailableNum(Integer.parseInt(row[3].toString()));
		            coVO.setJcTop(Integer.parseInt(row[4].toString()));
		            list1.add(coVO);
		        }

//		        tx.commit();
		        return list;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return new ArrayList<>();
		    }
//		List<ComOrderQueryVO> list = new ArrayList<>();
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String sql1 = "UPDATE job_order AS jo "
//					+ "JOIN ( "
//					+ "    SELECT j.COM_MEM_ID, SUM(j.JOB_TOP) AS top_num "
//					+ "    FROM job AS j "
//					+ "    WHERE j.JOB_TOP = 1 "
//					+ "    GROUP BY j.COM_MEM_ID "
//					+ ") AS d ON jo.COM_MEM_ID = d.COM_MEM_ID "
//					+ "SET jo.JOB_PUBLISHED_TOP_NUM = d.top_num "
//					+ "WHERE jo.COM_MEM_ID = ?";
//			Query<?> query = session.createNativeQuery(sql1);
//			query.setParameter(1, coqv.getComMemId());
//			query.executeUpdate();
//			
//			final String sql2 = "SELECT j.COM_MEM_ID, jo.JOB_PUBLISHED_NUM, jo.JOB_PUBLISHED_TOP_NUM, jc.JOB_AVAILABLE_NUM, jc.JC_TOP "
//					+ "FROM job AS j "
//					+ "JOIN job_order AS jo ON j.JO_NO = jo.JO_NO "
//					+ "JOIN job_case AS jc ON jc.JC_NO = jo.JC_NO "
//					+ "WHERE j.COM_MEM_ID = ?";
//			
//			Query<?> selectQuery = session.createNativeQuery(sql2);
//			selectQuery.setParameter(1, coqv.getComMemId());
//			
//			List<?> results = selectQuery.getResultList();
//			List<ComOrderQueryVO> list1 = new ArrayList<>();
//			for (Object result : results) {
//				Object[] row = (Object[]) result;
//				ComOrderQueryVO coVO = new ComOrderQueryVO();
//				coVO.setComMemId(Integer.parseInt(row[0].toString()));
//				coVO.setJobPublishedNum(Integer.parseInt(row[1].toString()));
//				coVO.setJobPublishedTopNum(Integer.parseInt(row[2].toString()));
//				coVO.setJcAvailableNum(Integer.parseInt(row[3].toString()));
//				coVO.setJcTop(Integer.parseInt(row[4].toString()));
//				list1.add(coVO);
//			}
//			
//			tx.commit();
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ArrayList<>();
//		}
	}
}
