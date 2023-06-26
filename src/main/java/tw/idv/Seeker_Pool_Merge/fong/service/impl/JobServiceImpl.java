package tw.idv.Seeker_Pool_Merge.fong.service.impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.fong.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.fong.dao.impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.fong.service.JobService;
import tw.idv.Seeker_Pool_Merge.fong.vo.JobVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.PageBean;

public class JobServiceImpl implements JobService{
	private JobDao jobDao = new JobDaoImpl();

	@Override
	public PageBean<JobVo> pageQuery(int memId, int currentPage, int pageSize) {
		//封裝pageBean
        PageBean<JobVo> pb = new PageBean<JobVo>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //設置總記錄數
        int totalCount = jobDao.findTotalCount(memId);
        pb.setTotalCount(totalCount);

        //設置當前頁數據集合
        int start = (currentPage - 1) * pageSize;//開始的紀錄數
        List<JobVo> list = jobDao.findByPage(memId, start, pageSize);
        pb.setList(list);

        //設置總頁數
        int totalPage = (totalCount % pageSize)== 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
	}

	@Override
	public void deletColJobByMemIdAndArNo(Integer jobNo, Integer memId) {
		jobDao.deletColJobByMemIdAndArNo(jobNo,  memId);
		
	}

}
