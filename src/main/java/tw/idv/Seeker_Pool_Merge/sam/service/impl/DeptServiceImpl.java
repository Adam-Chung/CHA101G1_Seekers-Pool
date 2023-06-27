package tw.idv.Seeker_Pool_Merge.sam.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.Seeker_Pool_Merge.sam.entity.Tb_dept;
import tw.idv.Seeker_Pool_Merge.sam.mapper.DeptMapper;
import tw.idv.Seeker_Pool_Merge.sam.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Tb_dept> list(){
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Tb_dept tbDept) {
        tbDept.setCreateTime(LocalDateTime.now());
        tbDept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(tbDept);
    }
}
