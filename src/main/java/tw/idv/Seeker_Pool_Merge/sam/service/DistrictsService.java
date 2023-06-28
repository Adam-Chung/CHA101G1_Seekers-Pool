package tw.idv.Seeker_Pool_Merge.sam.service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;

import java.util.List;

public interface DistrictsService {
    List<Districts> list(String cityName);

    List<Districts> list(Integer id);

    List<Districts> allList();
}
