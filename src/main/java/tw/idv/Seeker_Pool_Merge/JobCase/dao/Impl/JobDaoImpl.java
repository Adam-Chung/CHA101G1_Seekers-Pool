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

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComJobQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobVO;

@Repository
public class JobDaoImpl implements JobDao {
	
	@PersistenceContext
	private Session session;

	@Transactional
	@Override
	public boolean insert(JobVO job) {
		
		try {
//			Transaction tx = session.beginTransaction();
			session.save(job);
//			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.save(job);
//			tx.commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	@Transactional
	@Override
	public void update(JobVO job) {
		try {
//			Transaction tx = session.beginTransaction();
			session.update(job);
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.update(job);
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	
	@Transactional
	@Override
	public void delete(JobVO job) {
		try {
//			Transaction tx = session.beginTransaction();
			session.delete(job);
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			session.delete(job);
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	@Transactional
	@Override
	public JobVO selectByNo(Integer jobNo) {
		try  {
//			Transaction tx = session.beginTransaction();
			JobVO jobno = session.get(JobVO.class, jobNo);
//			tx.commit();
			return jobno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			JobVO jobno = session.get(JobVO.class, jobNo);
//			tx.commit();
//			return jobno;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;

	}
	
	@Transactional
	@Override
	public List<JobVO> selectAll() {
		try{
//			Transaction tx = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<JobVO> cq = cb.createQuery(JobVO.class);
			Root<JobVO> root = cq.from(JobVO.class);
			cq.select(root);
			Query<JobVO> query = session.createQuery(cq);
			List<JobVO> resultList = query.getResultList();
//			tx.commit();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<JobVO> cq = cb.createQuery(JobVO.class);
//			Root<JobVO> root = cq.from(JobVO.class);
//			cq.select(root);
//			Query<JobVO> query = session.createQuery(cq);
//			List<JobVO> resultList = query.getResultList();
//			tx.commit();
//			return resultList;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public List<ComJobQueryVO> selectJobStatus(ComJobQueryVO vo) {
		
		List<ComJobQueryVO> list = new ArrayList<>();
		try {
//			Transaction tx = session.beginTransaction();
			final String hql = "SELECT j.JOB_NO,j.JOB_NAME,jo.COM_MEM_ID,jo.JO_NO,j.JOB_UPLOAD,j.JOB_TOP,jo.JC_DEADLINE,jc.JC_NO,jc.JOB_AVAILABLE_NUM,jc.JC_TOP FROM job as j\r\n"
					+ "JOIN job_order as jo on jo.JO_NO=j.JO_NO\r\n" 
					+ "JOIN job_case as jc on jc.JC_NO=jo.JC_NO\r\n"
					+ "where j.COM_MEM_ID = :COM_MEM_ID  "
					+ "ORDER BY j.JOB_NO";
		
			Query<Object[]> query = session.createNativeQuery(hql);
			 query.setParameter("COM_MEM_ID", vo.getComMemId()); 
			
			List<Object[]> results = query.getResultList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Object[] result : results) {
				ComJobQueryVO CjVO = new ComJobQueryVO();
				CjVO.setJobNo((Integer) result[0]);
				CjVO.setJobName((String) result[1]);
				CjVO.setComMemId((Integer) result[2]);
				CjVO.setJoNo((Integer) result[3]);
				CjVO.setJobUpload(((Byte) result[4]).intValue());
				CjVO.setJobTop(((Byte) result[5]).intValue());
				Timestamp timestamp = (Timestamp) result[6];
				String formattedDate = dateFormat.format(timestamp);
				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
				CjVO.setJcDeadline(convertedTimestamp);
				CjVO.setJcNo((Integer) result[7]);
				CjVO.setJcAvailableNum((Integer) result[8]);
				CjVO.setJcTop((Integer) result[9]);
				list.add(CjVO);
			}
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
//		List<ComJobQueryVO> list = new ArrayList<>();
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "SELECT j.JOB_NO,j.JOB_NAME,jo.COM_MEM_ID,jo.JO_NO,j.JOB_UPLOAD,j.JOB_TOP,jo.JC_DEADLINE,jc.JC_NO,jc.JOB_AVAILABLE_NUM,jc.JC_TOP FROM job as j\r\n"
//					+ "JOIN job_order as jo on jo.JO_NO=j.JO_NO\r\n" 
//					+ "JOIN job_case as jc on jc.JC_NO=jo.JC_NO\r\n"
//					+ "where j.COM_MEM_ID = :COM_MEM_ID  "
//					+ "ORDER BY j.JOB_NO";
//			
//			Query<Object[]> query = session.createNativeQuery(hql);
//			query.setParameter("COM_MEM_ID", vo.getComMemId()); 
//			
//			List<Object[]> results = query.getResultList();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (Object[] result : results) {
//				ComJobQueryVO CjVO = new ComJobQueryVO();
//				CjVO.setJobNo((Integer) result[0]);
//				CjVO.setJobName((String) result[1]);
//				CjVO.setComMemId((Integer) result[2]);
//				CjVO.setJoNo((Integer) result[3]);
//				CjVO.setJobUpload(((Byte) result[4]).intValue());
//				CjVO.setJobTop(((Byte) result[5]).intValue());
//				Timestamp timestamp = (Timestamp) result[6];
//				String formattedDate = dateFormat.format(timestamp);
//				Timestamp convertedTimestamp = Timestamp.valueOf(formattedDate);
//				CjVO.setJcDeadline(convertedTimestamp);
//				CjVO.setJcNo((Integer) result[7]);
//				CjVO.setJcAvailableNum((Integer) result[8]);
//				CjVO.setJcTop((Integer) result[9]);
//				list.add(CjVO);
//			}
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;

	}
	@Transactional
	@Override
	public JobVO selectByJobStatus(Integer jobStatus) {
		try{
//			Transaction tx = session.beginTransaction();
			JobVO jobstatus = session.get(JobVO.class, jobStatus);
//			tx.commit();
			return jobstatus;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		try (Session session = getSession()) {
//			Transaction tx = session.beginTransaction();
//			JobVO jobstatus = session.get(JobVO.class, jobStatus);
//			tx.commit();
//			return jobstatus;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// 更新上下架
	@Transactional
	@Override
	public void updateJobUploadStatus(JobVO JobVo) {
		
		try {
//			Transaction tx = session.beginTransaction();
			final String hql = "UPDATE JobVO SET  jobUpload = :jobUpload, jobStatus = :jobStatus WHERE jobNo = :jobNo";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("jobUpload", JobVo.getJobUpload()); 
	        query.setParameter("jobStatus", JobVo.getJobStatus()); 
	        query.setParameter("jobNo", JobVo.getJobNo());
	        query.executeUpdate();
//	        tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try(Session session=getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "UPDATE JobVO SET  jobUpload = :jobUpload, jobStatus = :jobStatus WHERE jobNo = :jobNo";
//			Query<?> query = session.createQuery(hql);
//			query.setParameter("jobUpload", JobVo.getJobUpload()); 
//			query.setParameter("jobStatus", JobVo.getJobStatus()); 
//			query.setParameter("jobNo", JobVo.getJobNo());
//			query.executeUpdate();
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	// 更新置頂/未置頂
	@Transactional
	@Override
	public void updateJobTopStatus(JobVO jobvo) {
		try{
//			Transaction tx = session.beginTransaction();
			final String hql = "UPDATE JobVO SET  jobTop = :jobTop WHERE jobNo = :jobNo";
			Query<?> query = session.createQuery(hql);
			query.setParameter("jobTop", jobvo.getJobTop()); 
			query.setParameter("jobNo", jobvo.getJobNo());
			query.executeUpdate();
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try(Session session=getSession()) {
//			Transaction tx = session.beginTransaction();
//			final String hql = "UPDATE JobVO SET  jobTop = :jobTop WHERE jobNo = :jobNo";
//			Query<?> query = session.createQuery(hql);
//			query.setParameter("jobTop", jobvo.getJobTop()); 
//			query.setParameter("jobNo", jobvo.getJobNo());
//			query.executeUpdate();
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	// 更新職缺頁訂企業訂單號
	@Transactional
	@Override
	public boolean updateJoStatus(JobVO job) {
		try{
//			Transaction tx=session.beginTransaction();
			final String hql1 = "SELECT jo.joNo FROM JobOrderVO AS jo WHERE jo.comMemId = :comMemId ";
			Query<?> query1=session.createQuery(hql1);
			query1.setParameter("comMemId", job.getComMemId());
			
			
			final String hql2 = "UPDATE JobVO AS j SET j.joNo = :joNo where j.comMemId = :comMemId "; 
			Query<?> query2=session.createQuery(hql2);
			query2.setParameter("joNo", (int)(query1.getSingleResult()));
			query2.setParameter("comMemId", job.getComMemId());
			query2.executeUpdate();
			
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
//		try(Session session=getSession()) {
//			Transaction tx=session.beginTransaction();
//			final String hql1 = "SELECT jo.joNo FROM JobOrderVO AS jo WHERE jo.comMemId = :comMemId ";
//			Query<?> query1=session.createQuery(hql1);
//			query1.setParameter("comMemId", job.getComMemId());
//			
//			
//			final String hql2 = "UPDATE JobVO AS j SET j.joNo = :joNo where j.comMemId = :comMemId "; 
//			Query<?> query2=session.createQuery(hql2);
//			query2.setParameter("joNo", (int)(query1.getSingleResult()));
//			query2.setParameter("comMemId", job.getComMemId());
//			query2.executeUpdate();
//			
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
	}
}
