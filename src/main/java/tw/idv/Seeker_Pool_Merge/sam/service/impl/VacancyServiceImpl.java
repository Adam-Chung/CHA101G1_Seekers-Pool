package tw.idv.Seeker_Pool_Merge.sam.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;
import tw.idv.Seeker_Pool_Merge.sam.mapper.VacancyMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.VacancyService;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyMapper vacancyMapper;

    /**
     * 新增職缺
     *
     * @param job
     */
    @Override
    public void save(Job job) {
        if(job.getComMemId()== null){
            job.setComMemId(3); // 要討論 暫定的
        }
        vacancyMapper.insert(job);
    }

//    查詢職缺
    @Override
    public List<Job> list() {
        return vacancyMapper.list();
    }

    @Override
    public List<Job> list(Integer id) {
        return vacancyMapper.findVacancyById(id);
    }

    /**
     * 新增職缺
     *
     * @param jobId
     */


//    當table.job的jobId被table.skill_request, report_enterprise, collect_job給參照到時
//    需要一並把table.skill_request, report_enterprise, collect_job的jobId給刪除才能正常刪除職缺
    @Override
    public void deleteJob(Integer jobId) {
        vacancyMapper.deleteSkillRequest(jobId);  // 首先删除关联的子行
        vacancyMapper.deleteReportEnterprise(jobId);  // 首先删除关联的子行
        vacancyMapper.deleteCollectJob(jobId);  // 首先删除关联的子行
        vacancyMapper.deleteJob(jobId);         // 然后删除职缺行
    }

//    保存修改後職缺
    @Override
    public void update(Job job) {
        vacancyMapper.update(job);
    }

//    分頁功能
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 設置分頁參數
        PageHelper.startPage(page, pageSize);

        //2. 執行查詢
        List<Job> vacancyList = vacancyMapper.list();
        Page<Job> p = (Page<Job>) vacancyList;

        //3. 封裝PageBean對象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());  // getTotal() => 資料總數量, getResult() 總資料內容
        return pageBean;
    }

}
