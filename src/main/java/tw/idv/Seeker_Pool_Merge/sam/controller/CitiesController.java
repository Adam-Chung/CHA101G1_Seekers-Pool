package tw.idv.Seeker_Pool_Merge.sam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.Seeker_Pool_Merge.sam.entity.Cities;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.service.CitiesService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @GetMapping
    public Result list(){
//        log.info("查詢全部縣市");

        // 調用cities查詢縣市資料
        List<Cities> citiesList = citiesService.list();
        return Result.success(citiesList);
    }
}
