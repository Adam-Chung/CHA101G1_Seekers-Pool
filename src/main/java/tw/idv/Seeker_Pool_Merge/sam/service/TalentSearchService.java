package tw.idv.Seeker_Pool_Merge.sam.service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Member;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;

import java.util.List;

public interface TalentSearchService {

//    用關鍵字、地區、會員ID查詢職缺
    List<Member> list(String keyword);

    List<Member> list(String[] areas);
    List<Member> list(Integer memId);

    //    分頁功能
    PageBean page(Integer page, Integer pageSize, String keyword);

    PageBean page2(Integer page, Integer pageSize, String[] areas);
}

