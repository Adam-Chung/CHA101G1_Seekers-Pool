package tw.idv.Seeker_Pool_Merge.yuquann.service.impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobCollectDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.CollectJobVo;

public class CollectJobServiceImpl {

	public boolean check(int memId, int jobNo) {

		JobCollectDao dao = new JobCollectDao();
		List<CollectJobVo> list = dao.selectAll();

		for (CollectJobVo vo : list) {

			int dbMemId = vo.getMemId();
			int dbJobNo = vo.getJobNo();

			if (memId == dbMemId && jobNo == dbJobNo) {
//				System.out.println("該會員已收藏該職缺至清單中");
				return false;
			}
		}
		return true;
	}

}
