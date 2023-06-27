package tw.idv.Seeker_Pool_Merge.song.controller.servlet.signuprecord;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tw.idv.Seeker_Pool_Merge.song.dao.impl.JobFairDaoImpl;
import tw.idv.Seeker_Pool_Merge.song.dao.impl.SignupRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.song.vo.JobFairVo;
import tw.idv.Seeker_Pool_Merge.song.vo.SignupRecordVo;


@WebServlet("/SignupRecordServlet")
public class SignupRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		 
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
		 SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list = dao.getAll();
					


			/***************************查詢完成,準備轉交(Send the Success view)*************/
	        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		}
		if ("All".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes2");
		 SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list = dao.lookUp();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();		


			/***************************查詢完成,準備轉交(Send the Success view)*************/

		}
		
		if ("getOne_For_Display".equals(action)) { 
			System.out.println(action);



				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str1 = req.getParameter("jfNo");
			Integer jfNo= null;
			jfNo= Integer.valueOf(str1);
				/***************************2.開始查詢資料*****************************************/
				
				
				JobFairDaoImpl dao1 = new JobFairDaoImpl();
				JobFairVo jobFairVO = dao1.findByPrimaryKey(jfNo);
				System.out.println(str1);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString =gson.toJson(jobFairVO );
				
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
		}

		if ("getOne_For_Display2".equals(action)) { 
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			int str =(int)(req.getSession().getAttribute("companyMember"));
				System.out.println(str);
				

				/***************************2.開始查詢資料*****************************************/
				SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
				SignupRecordVo signupRecordVO = dao.findByPrimaryKey(str);
				System.out.println(signupRecordVO );
					    
				

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(signupRecordVO );
				
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();	
		}
		
		if ("getOne_For_Display3".equals(action)) { 
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("season");
				
				System.out.println(str);
				

				/***************************2.開始查詢資料*****************************************/
				SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
				List<SignupRecordVo> signupRecordVO = dao.findBySeason(str);
				System.out.println(signupRecordVO );
					    
				

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(signupRecordVO );
				
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();	
		}
		if ("insert".equals(action)) {
			int str1 =(int)(req.getSession().getAttribute("companyMember"));
			System.out.println(str1);
			String str2 = req.getParameter("jfNo");
			System.out.println(str2);
			
			SignupRecordVo jobFairVO=new SignupRecordVo();
			jobFairVO.setComMemId(str1);
			jobFairVO.setJfNo(Integer.valueOf(str2));
			
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			if(dao.findByPrimaryKey2(Integer.valueOf(str2), Integer.valueOf(str1))!=null) {
				String sucess= "您已經報名過了!";
				Gson gson = new Gson();
				String jsonString = gson.toJson(sucess);
				res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
			}else {
			
			if(dao.insert(jobFairVO)) {
				String sucess= "報名成功!";
				Gson gson = new Gson();
				String jsonString = gson.toJson(sucess);
				res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
			}
			}
		}
		if ("de".equals(action)) {
			String str1 = req.getParameter("srNo");
			System.out.println(str1);
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			dao.delete(Integer.valueOf(str1),0);
			System.out.println(str1);
			String sucess= "已取消報名";
			Gson gson = new Gson();
			String jsonString = gson.toJson(sucess);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		}
		if ("look".equals(action)) {
			System.out.println(action);
			int str =(int)(req.getSession().getAttribute(" companyMember"));
			System.out.println(str);
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list=dao.lookUp1(str);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		}
		if ("look2".equals(action)) {
			System.out.println(action);
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list=dao.lookUp2();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		}
		if("look3".equals(action)) {
			System.out.println(action);
			String str =req.getParameter("jfNo");
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list=dao.lookUp3(Integer.valueOf(str));
			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
		}
		if("look4".equals(action)) {
			System.out.println(action);
			System.out.println(req.getSession().getAttribute("companyMember"));
			int str =(int)(req.getSession().getAttribute("companyMember"));
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list=dao.lookUp4(str);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
		}
		if("lookCom".equals(action)) {
			System.out.println(action);
			String str =req.getParameter("comName").trim();
			System.out.println(str);
			SignupRecordDaoImpl dao = new SignupRecordDaoImpl();
			List<SignupRecordVo > list=dao.lookCom(str);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
		}
	}

}
