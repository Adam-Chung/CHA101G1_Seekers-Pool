package tw.idv.Seeker_Pool_Merge.song.controller.servlet.jobrecruitmentarticle;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tw.idv.Seeker_Pool_Merge.song.dao.impl.JobRecruitmentArticleDaoImpl;
import tw.idv.Seeker_Pool_Merge.song.vo.JobRecruitmentArticleVo;


@WebServlet("/JobRecruitment")
public class JobRecruitmentServlet extends HttpServlet {
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
		 
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
		 JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
		 dao.delete2();
			List<JobRecruitmentArticleVo> list = dao.getAll();


			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
			
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
	        return;
		}
		if ("getAll2".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println(action);
		 String str=req.getParameter("jrArStatus").trim();
		 int status=0;
		  if("上架".equals(str)) {
				status=1;
			}else {};
		 JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
			List<JobRecruitmentArticleVo> list = dao.getAll2(status);


			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
			
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
	        return;
		}
		if ("getOneSeason".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println(action);
		
		 String str1=req.getParameter("season").trim();	 
		 JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
			List<JobRecruitmentArticleVo> list = dao.getAll3(str1);


			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
			
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
	        return;
		}
		if ("insert".equals(action)) {
			
		
		Object str2 = req.getSession().getAttribute("admId");
		System.out.println(str2);
		String str3 = req.getParameter("jfArTitle").trim();
		System.out.println(str3);
		String str4 = req.getParameter("jfArContent").trim();
		System.out.println(str4);
		String str5 = req.getParameter("jrArImg");
		System.out.println(str5);
		java.util.Date now = new java.util.Date();
		System.out.println("java.util.Date() = "+ now);
		long long1 = now.getTime(); //也可以用long long1 = System.currentTimeMillis();
		java.sql.Date date1 = new java.sql.Date(long1);
		System.out.println("java.sql.Date()  = "+ date1);
		
		String str7 = req.getParameter("jrArStartTime").trim();
		System.out.println(str7);
		String str8 = req.getParameter("jrArEndTime").trim();
		System.out.println(str8);
		String str9 = req.getParameter("jrArCom").trim();
		System.out.println(str9);
		JobRecruitmentArticleVo jobRecruitmentArticleVO=new JobRecruitmentArticleVo();
		
		jobRecruitmentArticleVO.setAdmId((int)str2);
		jobRecruitmentArticleVO.setJrArTitle(str3);
		jobRecruitmentArticleVO.setJrArContent(str4);
		jobRecruitmentArticleVO.setJrArImg(str5);
		jobRecruitmentArticleVO.setJrPubTime(date1);
		jobRecruitmentArticleVO.setJrArStartTime(Date.valueOf(str7));
		jobRecruitmentArticleVO.setJrArEndTime(Date.valueOf(str8));
		jobRecruitmentArticleVO.setJrArCom(str9);
		JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
		if(dao.findByTitle(str3)!= null) {
			String sucess= "已經儲存過了喔!";
			Gson gson = new Gson();
			String jsonString = gson.toJson(sucess);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
		}else {
		
		if(dao.insert(jobRecruitmentArticleVO)) {
			String sucess= "儲存成功!";
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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("jrArNo");
				System.out.println(str);
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/jfback/job_fair.html");
					failureView.forward(req, res);
					//程式中斷
				}
				
				Integer jrArNo = null;
				try {
					jrArNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/jfback/job_fair.html");
					failureView.forward(req, res);
					//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
				JobRecruitmentArticleVo jobRecruitmentArticleVO = dao.findByPrimaryKey(jrArNo);
				System.out.println(jobRecruitmentArticleVO);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(jobRecruitmentArticleVO);
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();		    
				
//			
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				
				
		
	}
	if ("up".equals(action)) { // 來自select_page.jsp的請求
		System.out.println(action);

		

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("jrArStatus");
			System.out.println(str);
			String str1 = req.getParameter("jfArNo").trim();
			System.out.println(str1);
			int status=0;
			if("已上架".equals(str)) {
				String result ="上架!";
				Gson gson = new Gson();
				String jsonString = gson.toJson(result);
				res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
		        return;
			}else if("未上架".equals(str)) {
				status=1;
			}else {};
			
			System.out.println(status);
			JobRecruitmentArticleDaoImpl dao = new JobRecruitmentArticleDaoImpl();
			 
			dao.delete(Integer.valueOf(str1),status);
			String sucess= "上架成功";
			Gson gson = new Gson();
			String jsonString = gson.toJson(sucess);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		
		     
			
			/***************************2.開始查詢資料*****************************************/
		
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		
			
	}
}

}
