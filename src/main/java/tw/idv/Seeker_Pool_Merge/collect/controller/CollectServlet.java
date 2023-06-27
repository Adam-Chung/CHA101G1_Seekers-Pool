package tw.idv.Seeker_Pool_Merge.collect.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.collect.model.CollectService;
import tw.idv.Seeker_Pool_Merge.collect.model.CollectVo;

@WebServlet("/CollectServlet")
public class CollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollectService collectService = new CollectService();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");

		// 接收參數
		Integer arNo = Integer.valueOf(req.getParameter("arNo"));
		
		// 儲存登錄狀態

		Integer memId = (Integer) (req.getSession().getAttribute("memberLogin"));
//		System.out.println("memId = " + memId);
		
		if (memId == null) {
			//在前端跳轉頁面
			resp.getWriter().write("aaa");
			
		}else {
			 Integer memId1 = (Integer) req.getSession().getAttribute("memberLogin");
				// 建立 CollectVo 物件
		        CollectVo collectVo = new CollectVo();
		        collectVo.setArNo(arNo);
		        collectVo.setMemId(memId1);  // 先寫死，合併後更改      

		        // 呼叫 service 的 addCollect 方法，將 collectVo 傳遞給該方法
		        CollectVo collect = collectService.addCollect(collectVo);
//		        System.out.println(collect);


		        // 轉回 json 物件傳回前端
		        resp.getWriter().write(new Gson().toJson(collect));
		}
		
		 
		
    }

}
