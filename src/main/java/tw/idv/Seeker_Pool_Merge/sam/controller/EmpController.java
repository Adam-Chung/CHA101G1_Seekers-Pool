package tw.idv.Seeker_Pool_Merge.sam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.Seeker_Pool_Merge.sam.entity.Emp;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;
import tw.idv.Seeker_Pool_Merge.sam.utils.XmlParserUtils;


@RestController
public class EmpController {

    @RequestMapping("/listEmp")
    public Result list(){
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if ("1".equals(gender)){
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }
        });
        return Result.success(empList);
    }
}
