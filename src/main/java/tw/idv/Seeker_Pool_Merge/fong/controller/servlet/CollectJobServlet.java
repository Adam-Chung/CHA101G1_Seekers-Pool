package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.fong.service.JobService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.JobServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.JobVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.PageBean;

@WebServlet("/CollectJobServlet")
public class CollectJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobService jobService = new JobServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		//接收參數
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        Integer memId = (Integer) req.getSession().getAttribute("memberLogin");

        //處理參數
        int currentPage = 1;//當前頁碼 默認
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 5; //每頁顯示條數 默認
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //用service查詢PageBean對象
        PageBean<JobVo> pb = jobService.pageQuery(memId, currentPage, pageSize);

      //轉為json寫回客戶端
    	String jsonStr =  new JSONObject(pb).toString();  
//    	System.out.println(jsonStr);
    	resp.getWriter().write(jsonStr);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}