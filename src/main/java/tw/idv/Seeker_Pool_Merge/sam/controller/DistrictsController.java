package tw.idv.Seeker_Pool_Merge.sam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.service.DistrictsService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/districts")
public class DistrictsController {

    @Autowired
    private DistrictsService districtsService;


    @GetMapping
    public Result list(){
//        log.info("查詢全部鄉鎮市");

        List<Districts> list = districtsService.allList();
        return Result.success(list);
    }

    @GetMapping("/area/{id}")
    public Result findDistrictById(@PathVariable Integer id){
//        log.info("用id查詢相對應的鄉鎮區:{}" ,id);
        // 調用Districts查詢相對應的鄉鎮區資料
        List<Districts> districtsList = districtsService.list(id);

        return Result.success(districtsList);
    }

    @GetMapping("/{cityName}")
    public Result findDistrictByName(@PathVariable String cityName){
//        log.info("查詢相對應的鄉鎮區:{}" ,cityName);

        // 調用Districts查詢相對應的鄉鎮區資料
        List<Districts> districtsList = districtsService.list(cityName);
        return Result.success(districtsList);
    }

}
