package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.fong.service.ApplyRecordService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.ApplyRecordServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.ApplyRecordShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.PageBean;

@WebServlet("/ApplyRecordServlet")
public class ApplyRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplyRecordService applyRecordService = new ApplyRecordServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		//接收參數
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String keyWordStr = req.getParameter("keyWord");
        String filterNumStr = req.getParameter("filterNum");
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
        String keyWord = ""; //關鍵字
        if (keyWordStr != null && keyWordStr.length() > 0 && !("null".equals(keyWordStr))){
        	keyWord = keyWordStr.trim();
        }
        int filterNum = 100; //狀態篩選器 默認100全部顯示
        if (filterNumStr != null && filterNumStr.length() > 0){
        	filterNum = Integer.parseInt(filterNumStr);
        }

        //調用service查詢PageBean對象
        PageBean<ApplyRecordShowVo> pb = applyRecordService.pageQuery(memId, currentPage, pageSize, keyWord, filterNum);
        
        //轉為json寫回客戶端
    	String jsonStr =  new JSONObject(pb).toString();
    	resp.getWriter().write(jsonStr);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}