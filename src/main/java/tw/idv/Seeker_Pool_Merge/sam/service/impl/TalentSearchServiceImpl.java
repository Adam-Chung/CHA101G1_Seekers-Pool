package tw.idv.Seeker_Pool_Merge.sam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Member;
import tw.idv.Seeker_Pool_Merge.sam.mapper.TalentSearchMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.TalentSearchService;

@Service
public class TalentSearchServiceImpl implements TalentSearchService {

    @Autowired
    private TalentSearchMapper talentSearchMapper;

    @Override
    public List<Member> list(String keyword) {
        return talentSearchMapper.findTalentByKeyword(keyword);
    }

    @Override
    public List<Member> list(String[] areas) {
        return talentSearchMapper.findByTalentByArea(areas);
    }

    @Override
    public List<Member> list(Integer memId) {
        return talentSearchMapper.findTalentById(memId);
    }
}
