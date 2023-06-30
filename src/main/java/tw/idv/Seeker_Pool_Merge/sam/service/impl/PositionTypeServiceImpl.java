package tw.idv.Seeker_Pool_Merge.sam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.Seeker_Pool_Merge.sam.entity.Cities;
import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.PositionType;
import tw.idv.Seeker_Pool_Merge.sam.mapper.PositionTypeMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.PositionTypeService;

import java.util.List;

@Service
public class PositionTypeServiceImpl implements PositionTypeService {

    @Autowired
    private PositionTypeMapper positionTypeMapper;

    @Override
    public List<PositionType> list() {
        return positionTypeMapper.list();
    }

    @Override
    public List<PositionType> list(String type) {
            return positionTypeMapper.findNameByType(type);
    }
}
