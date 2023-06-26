package tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobCaseDao;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

public class JobCaseDaoImpl implements JobCaseDao {

	@Override
	public boolean insert(JobCaseVO jc) {
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(jc);
			tx.commit();
			return true;
		} catch (Exception e) {
			// 錯誤處理代碼，例如記錄錯誤日誌或回滾事務
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(JobCaseVO jc) {
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			session.update(jc);
			tx.commit();
		}
	}

	@Override
	public void delete(JobCaseVO jc) {

		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(jc);
			tx.commit();
		}
	}

	@Override
	public JobCaseVO selectByNo(Integer jcNo) {
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			JobCaseVO jcno = session.get(JobCaseVO.class, jcNo);
			tx.commit();
			return jcno;
		}
	}

	@Override
	public List<JobCaseVO> selectAll() {
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<JobCaseVO> cq = cb.createQuery(JobCaseVO.class);
//			Root<JobCaseVO> root = cq.from(JobCaseVO.class);
//			cq.select(root);
			final String hql ="from JobCaseVO";
			Query<JobCaseVO> query = session.createQuery(hql,JobCaseVO.class);
			List<JobCaseVO> resultList = query.getResultList();
			tx.commit();
			return resultList;
		}
	}

	@Override
	public JobCaseVO selectByCaseName(String jcName) {
		try (Session session = getSession()) {
			Transaction tx = session.beginTransaction();
			Query<JobCaseVO> query = session.createQuery("FROM JobCaseVO WHERE jcName = :JC_NAME", JobCaseVO.class);
			query.setParameter("name", jcName);
			JobCaseVO result = query.uniqueResult();
			tx.commit();
			return result;
		}
	}

//	@Override
//	public List<ComOrderQueryVO> selectOrderCase() {
//		List<ComOrderQueryVO> list = new ArrayList<>();
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "SELECT jo.JO_NO,jc.JC_NAME,jc.JOB_AVAILABLE_NUM,jc.JC_TOP,jc.JC_EXP_TIME,jo.JC_DEADLINE,jc.JC_PRICE,am.COM_NAME,am.COM_MEM_ID,jc.JC_NO FROM job_case as jc\r\n"
//					+ "JOIN job_order as jo on jc.JC_NO = jo.JC_NO\r\n" 
//					+ "JOIN company_member as am on am.COM_MEM_ID=jo.COM_MEM_ID\r\n" 
//					+ "ORDER BY jo.JO_NO";
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
//
//	}

}
