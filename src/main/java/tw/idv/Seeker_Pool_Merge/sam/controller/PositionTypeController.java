package tw.idv.Seeker_Pool_Merge.sam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.Seeker_Pool_Merge.sam.entity.PositionType;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.service.PositionTypeService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/positionType")
public class PositionTypeController {

    @Autowired
    private PositionTypeService positionTypeService;


//    查詢全部產業及職務類別
    @GetMapping
    public Result TypeList(){
        List<PositionType> typeList = positionTypeService.list();
        return Result.success(typeList);
    }

//    根據產業名稱查對應職務
    @GetMapping("/{type}")
    public Result findNameByType(@PathVariable String type){
//        log.info("查詢對應職務類別小項:{}" , type);

        // 調用position_type查詢全部職務類別大項
        List<PositionType> nameList = positionTypeService.list(type);
        System.out.println(nameList);

        return nameList.size()== 0  ? Result.error("nothing") : Result.success(nameList) ;
    }
}

