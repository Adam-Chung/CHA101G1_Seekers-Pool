package tw.idv.Seeker_Pool_Merge.sam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.service.VacancyService;

@RestController
@RequestMapping("/vacancy")
@Slf4j
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

//    新增職缺
    @PostMapping
    public Result save(@RequestBody Job job){
        log.info("新增職缺, {}", job);
        vacancyService.save(job);
        return Result.success();
    }

//    查詢全部職缺
    @GetMapping
    public Result vacancyList(){
        List<Job> jobList = vacancyService.list();
        return Result.success(jobList);
    }

//   查詢職缺
    @GetMapping("/{id}")
    public Result findVacancyById(@PathVariable Integer id){
        log.info("查詢相對應的職缺:{}" ,id);

        // 調用job查詢相對應的職缺資料
        List<Job> oneVacancy = vacancyService.list(id);
        return Result.success(oneVacancy);
    }

// 更新職缺
    @PutMapping
    public Result updateVacancy(@RequestBody Job job){
        log.info("修改職缺 : {}" , job);
        vacancyService.update(job);
        return Result.success();

    }
//   刪除職缺
    @DeleteMapping("/{id}")
    public Result deleteVacancyById(@PathVariable Integer id){

        vacancyService.delete(id);
        return Result.success();
    }

//  分頁功能
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分頁查詢, 參數: {}, {}", page, pageSize);
//        //調用service分頁查詢
//        PageBean pageBean = vacancyService.page(page,pageSize);
//        return Result.success(pageBean);
//    }
}
