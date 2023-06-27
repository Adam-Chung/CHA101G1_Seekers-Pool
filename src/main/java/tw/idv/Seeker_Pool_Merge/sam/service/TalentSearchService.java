package tw.idv.Seeker_Pool_Merge.sam.service;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.sam.entity.Member;

public interface TalentSearchService {

    List<Member> list(String keyword);

    List<Member> list(String[] areas);
    List<Member> list(Integer memId);
}
