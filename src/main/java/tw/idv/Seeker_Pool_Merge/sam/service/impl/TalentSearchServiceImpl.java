package tw.idv.Seeker_Pool_Merge.sam.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.Seeker_Pool_Merge.sam.entity.Member;
import tw.idv.Seeker_Pool_Merge.sam.entity.PageBean;
import tw.idv.Seeker_Pool_Merge.sam.mapper.TalentSearchMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.TalentSearchService;

import java.util.List;

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
        return talentSearchMapper.findTalentByArea(areas);
    }

    @Override
    public List<Member> list(Integer memId) {
        return talentSearchMapper.findTalentById(memId);
    }

    //    分頁功能
    @Override
    public PageBean page(Integer page, Integer pageSize, String keyword) {
        //1. 設置分頁參數
        PageHelper.startPage(page, pageSize);

        //2. 執行查詢
        List<Member> vacancyList = talentSearchMapper.findTalentByKeyword(keyword);
        Page<Member> p = (Page<Member>) vacancyList;

        //3. 封裝PageBean對象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());  // getTotal() => 資料總數量, getResult() 總資料內容
        return pageBean;
    }

    @Override
    public PageBean page2(Integer page, Integer pageSize, String[] areas) {
        //1. 設置分頁參數
        PageHelper.startPage(page, pageSize);

        //2. 執行查詢
        List<Member> vacancyList = talentSearchMapper.findTalentByArea(areas);
        Page<Member> p = (Page<Member>) vacancyList;

        //3. 封裝PageBean對象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());  // getTotal() => 資料總數量, getResult() 總資料內容
        return pageBean;
    }
}
