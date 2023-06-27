package tw.idv.Seeker_Pool_Merge.sam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.entity.Tb_dept;
import tw.idv.Seeker_Pool_Merge.sam.service.DeptService;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)  //指定請求方式為GET, 可用下面的方式比較簡單
    @GetMapping
    public Result list(){
        log.info("查詢全部系所");

        // 調用service查詢部門數據
        List<Tb_dept> deptList = deptService.list();
        return Result.success(deptList);
    }

//    刪除系所
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根據id刪除部門:{}",id);
        //調用service刪除系所
        deptService.delete(id);
        return Result.success();
    }

//    新增系所
    @PostMapping
    public Result add(@RequestBody Tb_dept tbDept){
        log.info("新增系所: {}", tbDept);
        //調用service新增系所
        deptService.add(tbDept);
        return Result.success();
    }
}
