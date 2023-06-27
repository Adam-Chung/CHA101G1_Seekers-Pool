package tw.idv.Seeker_Pool_Merge.sam.service;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;

public interface DistrictsService {
    List<Districts> list(String cityName);

    List<Districts> list(Integer id);

    List<Districts> allList();
}
