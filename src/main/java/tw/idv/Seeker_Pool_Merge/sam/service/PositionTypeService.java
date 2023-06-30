package tw.idv.Seeker_Pool_Merge.sam.service;
import tw.idv.Seeker_Pool_Merge.sam.entity.Job;
import tw.idv.Seeker_Pool_Merge.sam.entity.PositionType;
import java.util.List;

public interface PositionTypeService {

    //    查詢全部職缺
    List<PositionType> list();


    List<PositionType> list(String type);
}
