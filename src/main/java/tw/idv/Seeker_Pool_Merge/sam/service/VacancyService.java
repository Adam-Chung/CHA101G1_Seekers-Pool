package tw.idv.Seeker_Pool_Merge.sam.service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;

import java.util.List;

public interface VacancyService {

    /**
     * 新增職缺
     * @param job
     */
    void save(Job job);

//    查詢全部職缺
    List<Job> list();

//    查詢職缺
    List<Job> list(Integer id);

    /**
     * 刪除職缺
     * @param id
     */
    //    刪除職缺
    void deleteJob(Integer id);

//    更新職缺
    void update(Job job);

//    分頁功能
    PageBean page(Integer page, Integer pageSize);
}
