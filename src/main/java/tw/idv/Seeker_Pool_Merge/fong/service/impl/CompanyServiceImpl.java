package tw.idv.Seeker_Pool_Merge.fong.service.impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.fong.dao.CompanyDao;
import tw.idv.Seeker_Pool_Merge.fong.dao.impl.CompanyDaoImpl;
import tw.idv.Seeker_Pool_Merge.fong.service.CompanyService;
import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao = new CompanyDaoImpl();

	@Override
	public List<CompanyMemberShowVo> findBlockComsByMemId(Integer memId) {
		return companyDao.findBlockComsByMemId(memId);
	}

	@Override
	public ResultInfo addBlockComByName(Integer memId, String addCompanyName) {
		
		ResultInfo info = new ResultInfo(); 
		//先確認是否在資料庫有其公司
		CompanyMemberShowVo blockCom =  companyDao.findComByName(addCompanyName);
		
		if(blockCom == null) {
			//新增失敗，找不到該企業
			info.setFlag(false);
			info.setMsg("找不到該企業");
		}else {
			//確認是否已存在屏蔽名單中
			Boolean flag =  companyDao.findBlockComByMemIdAndComMemId(memId, blockCom.getComMemId());
			if(flag == false){
				//新增失敗，已存在於屏蔽名單
				info.setFlag(false);
				info.setMsg("該企業已存在於您的屏蔽名單中");
			}else {
				//新增成功
				companyDao.addBlockComBy(memId, blockCom.getComMemId());
				info.setFlag(true);
				info.setData(blockCom);
			}
		}
		return info;
	}

	@Override
	public void deletBlockComByName(Integer memId, String deleteBlockCom) {
		companyDao.deletBlockComByName(memId, deleteBlockCom);
	}
}
