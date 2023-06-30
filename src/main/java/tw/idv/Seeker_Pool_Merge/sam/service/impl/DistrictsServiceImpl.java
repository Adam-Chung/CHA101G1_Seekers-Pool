package tw.idv.Seeker_Pool_Merge.sam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;
import tw.idv.Seeker_Pool_Merge.sam.mapper.DistrictsMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.DistrictsService;

import java.util.List;

@Service
public class DistrictsServiceImpl implements DistrictsService {

    @Autowired
    private DistrictsMapper districtsMapper;

    @Override
    public List<Districts> list(String cityName) {
        return districtsMapper.findDistrictByCityName(cityName);
    }

    @Override
    public List<Districts> list(Integer id) {
        return districtsMapper.findDistrictById(id);
    }


//    查詢全部鄉鎮市
    @Override
    public List<Districts> allList() {
        return districtsMapper.findAllDistricts();
    }

}
