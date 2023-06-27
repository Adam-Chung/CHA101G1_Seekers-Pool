package tw.idv.Seeker_Pool_Merge.sam.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Cities;
import tw.idv.Seeker_Pool_Merge.sam.mapper.CitiesMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.CitiesService;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;

    @Override
    public List<Cities> list() {
        return citiesMapper.list();
    }
}
