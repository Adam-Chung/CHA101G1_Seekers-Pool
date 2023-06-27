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
		

		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
			AdministratorDaoImpl dao = new AdministratorDaoImpl();
			List<AdministratorVo> list = dao.getAll();


			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/administrator/index.html";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("admId");
				System.out.println(str);
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/administrator/select_page.jsp");
					failureView.forward(req, res);
					//程式中斷
				}
				
				Integer admId = null;
				try {
					admId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/administrator/index.html");
					failureView.forward(req, res);
					//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdministratorDaoImpl dao = new AdministratorDaoImpl();
				AdministratorVo administratorVO = dao.findByPrimaryKey(admId);
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
	}

}
