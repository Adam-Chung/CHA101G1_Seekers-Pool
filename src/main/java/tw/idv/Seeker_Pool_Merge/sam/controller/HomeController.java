package tw.idv.Seeker_Pool_Merge.sam.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/welcome")
    public String welcome() {
        System.out.println("welcome to");
        return "Welcome to my website!";
    }

    // 其他方法...
}
