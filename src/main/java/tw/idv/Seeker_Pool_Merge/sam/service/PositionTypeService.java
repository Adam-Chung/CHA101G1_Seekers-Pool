package tw.idv.Seeker_Pool_Merge.sam.service;
import java.util.List;

import tw.idv.Seeker_Pool_Merge.sam.entity.PositionType;

public interface PositionTypeService {

    //    查詢全部職缺
    List<PositionType> list();


    List<PositionType> list(String type);
}
