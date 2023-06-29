package tw.idv.Seeker_Pool_Merge.Administrator.controller;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobCaseDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

public class test {
	public static void main(String[] args) {
		JobCaseDaoImpl jobCaseDaoImpl = new JobCaseDaoImpl();
		JobCaseVO selectByNo = jobCaseDaoImpl.selectByNo(1);
		System.out.println(selectByNo);
		
		
//		AdministratorDaoImpl administratorDaoImpl = new AdministratorDaoImpl();
//		AdministratorVO findByPrimaryKey = administratorDaoImpl.findByPrimaryKey(1);
//		System.out.println(findByPrimaryKey);
	}
}
