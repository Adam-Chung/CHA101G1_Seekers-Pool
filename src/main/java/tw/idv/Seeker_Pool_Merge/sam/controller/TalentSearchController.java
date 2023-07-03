package tw.idv.Seeker_Pool_Merge.sam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.Seeker_Pool_Merge.sam.entity.Member;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.service.TalentSearchService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/talent")
public class TalentSearchController {

    @Autowired
    private TalentSearchService talentSearchService;

//    透過關鍵字查詢人才
//    @GetMapping("/{keyword}")
//    public Result findTalentByKeyword(@PathVariable String keyword){
//        log.info("用關鍵字查詢人才:{}" , keyword);
//
//        List<Member> talentList = talentSearchService.list(keyword);
//        return Result.success(talentList);
//    }

//    //    透過地區查詢人才
//    @GetMapping("/area/{areas}")
//    public Result findTalentByAreas(@PathVariable String[] areas){
//        log.info("用地區查詢人才");
//
//        List<Member> talentList = talentSearchService.list(areas);
//        return Result.success(talentList);
//    }
//
//    //    透過memId查詢人才
//    @GetMapping("/id/{memId}")
//    public Result findTalentById(@PathVariable Integer memId){
//        log.info("用id查詢人才:{}", memId);
//
//        List<Member> talentList = talentSearchService.list(memId);
//        return Result.success(talentList);
//    }


    //    透過關鍵字查詢人才
    @GetMapping("/{keyword}")
    public Result findTalentByKeyword(
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      @PathVariable String keyword){
//        log.info("分頁關鍵字查詢, 參數:{}, {}, {}", page, pageSize, keyword);
        //調用service分頁查詢
        PageBean pageBean = talentSearchService.page( page,pageSize, keyword);
        return Result.success(pageBean);
    }

    //    透過地區查詢人才
    @GetMapping("/area/{areas}")
    public Result findTalentByAreas(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @PathVariable String[] areas){
//        log.info("用地區查詢人才");

        PageBean pageBean = talentSearchService.page2( page,pageSize, areas);
        return Result.success(pageBean);
    }

    //    透過memId查詢人才
    @GetMapping("/id/{memId}")
    public Result findTalentById(@PathVariable Integer memId){
//        log.info("用id查詢人才:{}", memId);

        List<Member> talentList = talentSearchService.list(memId);
        return Result.success(talentList);
    }
}
