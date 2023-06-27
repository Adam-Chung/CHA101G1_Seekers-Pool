package tw.idv.Seeker_Pool_Merge.sam.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.Seeker_Pool_Merge.sam.entity.Address;
import tw.idv.Seeker_Pool_Merge.sam.entity.Result;

@RestController
public class ResponseExampleController {

//    比較推薦的版本
//    在entity裡創建 Result, 在那打包回傳的方法(JSON格式)
    @RequestMapping("/noHello")
    public Result noHello(){
        System.out.println("no hello u know");
//        return new Result(1, "success", "no hello");
        return Result.success("no hello"); // 調用Result.success的方法就可以傳data就好
    }

    @RequestMapping("/noGetAddr")
    public Result noGetAddr(){
        Address addr = new Address();
        addr.setProvince("宜蘭");
        addr.setCity("sfs");
//        addr.setCity("頭城");
        System.out.println(addr);
        return Result.success(addr);
    }

    @RequestMapping("/noListAddr")
    public Result noListAddr(){
        List<Address> ListAddr = new ArrayList<>();

        Address addr = new Address();
        addr.setProvince("桃園");
        addr.setCity("中壢");

        Address addr2 = new Address();
        addr2.setProvince("台中");
        addr2.setCity("大里");

        Address addr3 = new Address();
        addr3.setProvince("台東");
        addr3.setCity("哪裡");

        ListAddr.add(addr);
        ListAddr.add(addr2);
        ListAddr.add(addr3);

        System.out.println(ListAddr.get(1));
        return Result.success(ListAddr);
    }

}
