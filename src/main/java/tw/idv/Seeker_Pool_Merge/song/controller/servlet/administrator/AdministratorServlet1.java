package tw.idv.Seeker_Pool_Merge.song.controller.servlet.administrator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.song.dao.impl.AdministratorDaoImpl;
import tw.idv.Seeker_Pool_Merge.song.vo.AdministratorVo;


@WebServlet("/administrator.do")
public class AdministratorServlet1 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("logout".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);
                req.getSession().removeAttribute("admId");
                String str2 = "登出成功!";
				Gson gson = new Gson();
				String jsonString = gson.toJson(str2);
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
            

		}
		
		if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("admAccount");
				System.out.println(str);
				String str1 = req.getParameter("admPassword");
				System.out.println(str1);
		
				/***************************2.開始查詢資料*****************************************/
				AdministratorDaoImpl dao = new AdministratorDaoImpl();
				AdministratorVo administratorVO = dao.findByKey(str,str1);
				System.out.println(administratorVO);
				if(administratorVO==null) {
					String str2 = "請確認帳號密碼!";
					Gson gson = new Gson();
					String jsonString = gson.toJson(str2);
			        res.setContentType("application/json;charset=utf-8");
			        PrintWriter out=res.getWriter();
			        System.out.println(jsonString);
			        out.write(jsonString);
			        out.close();
			        return;
				}else{
					req.getSession().setAttribute("admId", administratorVO.getAdmId());
				Gson gson = new Gson();
				String jsonString = gson.toJson(administratorVO);
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();	
				}
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

			
		}
		
		if ("getOne_For_Display3".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Object str = req.getSession().getAttribute("admId");
				System.out.println(str);
				
		
				/***************************2.開始查詢資料*****************************************/
				AdministratorDaoImpl dao = new AdministratorDaoImpl();
				AdministratorVo administratorVO = dao.findByPrimaryKey((int)str);
				System.out.println(administratorVO);
				
				Gson gson = new Gson();
				String jsonString = gson.toJson(administratorVO);
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();	
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

			
		}
		if ("getOne_For_Display4".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Object str = req.getSession().getAttribute("admId");
				System.out.println(str);
				
		
				/***************************2.開始查詢資料*****************************************/
				AdministratorDaoImpl dao = new AdministratorDaoImpl();
				AdministratorVo administratorVO = dao.findByPrimaryKey2((int)str);
				System.out.println(administratorVO);
				if(administratorVO ==null) {
					Gson gson = new Gson();
					String error = gson.toJson(" 您無最高權限!");
					res.setContentType("application/json;charset=utf-8");
			        PrintWriter out=res.getWriter();
			        System.out.println(error );
			        out.write(error );
			        out.close();	
			       return;
				}else {
				
				Gson gson = new Gson();
				String jsonString = gson.toJson(administratorVO);
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();	
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				}
			
		
		}

	}

}
