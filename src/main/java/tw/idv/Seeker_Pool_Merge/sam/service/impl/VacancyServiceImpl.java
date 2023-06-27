package tw.idv.Seeker_Pool_Merge.sam.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;
import tw.idv.Seeker_Pool_Merge.sam.mapper.VacancyMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.VacancyService;

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
     * @param id
     */

    @Override
    public void delete(Integer id) {
        vacancyMapper.delete(id);
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
