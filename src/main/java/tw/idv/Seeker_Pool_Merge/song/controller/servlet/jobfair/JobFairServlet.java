package tw.idv.Seeker_Pool_Merge.song.controller.servlet.jobfair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;
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
import tw.idv.Seeker_Pool_Merge.song.vo.JobFairVo;


@WebServlet("/JobFair")
public class JobFairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		 
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(req.getParameter("action"));
		if ("com".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("com");
		 req.getSession().setAttribute("companyMember", 3);
		

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new Gson();
			String str ="go!";
			String jsonString = gson.toJson(str );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();	
			
		}
		if ("getAlljf".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
		 JobFairDaoImpl dao = new JobFairDaoImpl();
		 dao.delete2();
			List<JobFairVo > list = dao.getAll3();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();	
			
		}
		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
		 JobFairDaoImpl dao = new JobFairDaoImpl();
		 dao.delete2();
			List<JobFairVo > list = dao.getAll();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();	
			
		}
		if ("getAll2".equals(action)) {
			/***************************開始查詢資料 ****************************************/
		 System.out.println("yes");
		 JobFairDaoImpl dao = new JobFairDaoImpl();
			List<JobFairVo > list = dao.getAll2();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonString = gson.toJson(list );
	        res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();	
			
		}


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("season");
				System.out.println(str);
				
				
				
				/***************************2.開始查詢資料*****************************************/
				JobFairDaoImpl dao = new JobFairDaoImpl();
				List<JobFairVo> jobFairVO = dao.findBySeason(str);
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(jobFairVO );
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
		}
		if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("jfNo");
				System.out.println(str);
				
				
				
				/***************************2.開始查詢資料*****************************************/
				JobFairDaoImpl dao = new JobFairDaoImpl();
				JobFairVo jobFairVO = dao.findByPrimaryKey(Integer.valueOf(str));
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(jobFairVO );
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
		}
		if ("getSeason_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("season");
				System.out.println(str);
				
				
				
				/***************************2.開始查詢資料*****************************************/
				JobFairDaoImpl dao = new JobFairDaoImpl();
				List<JobFairVo> jobFairVO = dao.findBySeason2(str);
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonString = gson.toJson(jobFairVO );
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
		}
		if ("get_For_Display2".equals(action)) { // 來自select_page.jsp的請求
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("season");
				System.out.println(str);
				int a=0;
				if("已上架".equals(str)) {
					a=1;
				}else if("未上架".equals(str)) {
					a=0;
				}else {};
				
				
				/***************************2.開始查詢資料*****************************************/
				JobFairDaoImpl dao = new JobFairDaoImpl();
				List<JobFairVo> jobFairVO = dao.findByJrStatus(a);
				

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    	
				String jsonString = gson.toJson(jobFairVO );
		        res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
		}
		if ("insert".equals(action)) {
				String data=req.getParameter("file");
				String base64Data=data.split(",")[1].replace("\"}", "");
			
				System.out.println(base64Data);
				
				byte[] bytes= Base64.getDecoder().decode(base64Data);
				FileOutputStream fos=null;
				String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄; (不要在註解@處指定目的地路徑)
				String realPath = getServletContext().getRealPath(saveDirectory);
		        File dir = new File(realPath);
		        //檔案目錄不存在，就建立一個
		        if (!dir.isDirectory()) {
		            dir.mkdirs();
		        }
		        File f = new File(dir, req.getParameter("jrArImg")); // 建立一個檔案，用真實路徑(阿飄路徑)(連動端，實際運作取用)
				try {
					fos=new FileOutputStream(f.toString());
					fos.write(bytes);
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				String filePath =req.getContextPath() + saveDirectory + "/" + req.getParameter("jrArImg");
			
			String str2 = req.getParameter("admId");
			System.out.println(str2);
			String str3 = req.getParameter("jfName");
			System.out.println(str3);
			String str4 = req.getParameter("jfStartTime");
			System.out.println(str4);
			String str5 = req.getParameter("jfEndTime");
			System.out.println(str5);
			String str6 = req.getParameter("regStartTime");
			System.out.println(str6);
			String str7 = req.getParameter("regEndTime");
			System.out.println(str7);
			String str8 = req.getParameter("jfActivity");
			System.out.println(str8);
			String str9 = req.getParameter("jrArImg");
			System.out.println(str9);
			JobFairVo jobFairVO=new JobFairVo();
			
			jobFairVO.setAdmId(Integer.valueOf(str2));
			jobFairVO.setJfName(str3);
			jobFairVO.setJfStartTime(Date.valueOf(str4));
			jobFairVO.setJfEndTime(Date.valueOf(str5));
			jobFairVO.setRegStartTime(Date.valueOf(str6));
			jobFairVO.setRegEndTime(Date.valueOf(str7));
			jobFairVO.setJfActivity(str8);
			jobFairVO.setJrArImg(filePath);
			JobFairDaoImpl dao = new JobFairDaoImpl();
			
			if(dao.insert(jobFairVO)) {
				String sucess= "輸入成功";
				Gson gson = new Gson();
				String jsonString = gson.toJson(sucess);
				res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
				
			}
		}
		if ("de".equals(action)) {
			String str1 = req.getParameter("jfNo");
			System.out.println(str1);
			String str2 = req.getParameter("jrStatus").trim();
			int status=0;
			if("已上架".equals(str2)) {
				String result ="上架!";
				Gson gson = new Gson();
				String jsonString = gson.toJson(result);
				res.setContentType("application/json;charset=utf-8");
		        PrintWriter out=res.getWriter();
		        System.out.println(jsonString);
		        out.write(jsonString);
		        out.close();
		        return;
			}else if("未上架".equals(str2)) {
				status=1;
			}else {};
			System.out.println(str2);
			System.out.println(status);
			JobFairDaoImpl dao = new JobFairDaoImpl();
			 
			dao.delete(Integer.valueOf(str1),status);
			String sucess= "上架成功";
			Gson gson = new Gson();
			String jsonString = gson.toJson(sucess);
			res.setContentType("application/json;charset=utf-8");
	        PrintWriter out=res.getWriter();
	        System.out.println(jsonString);
	        out.write(jsonString);
	        out.close();
			
		}
		if ("up".equals(action)) {
			String data=req.getParameter("file");
			String base64Data=data.split(",")[1].replace("\"}", "");
		
			System.out.println(base64Data);
			
			byte[] bytes= Base64.getDecoder().decode(base64Data);
			FileOutputStream fos=null;
			String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄; (不要在註解@處指定目的地路徑)
			String realPath = getServletContext().getRealPath(saveDirectory);
	        File dir = new File(realPath);
	        //檔案目錄不存在，就建立一個
	        if (!dir.isDirectory()) {
	            dir.mkdirs();
	        }
	        File f = new File(dir, req.getParameter("jrArImg")); // 建立一個檔案，用真實路徑(阿飄路徑)(連動端，實際運作取用)
			try {
				fos=new FileOutputStream(f.toString());
				fos.write(bytes);
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			String filePath =req.getContextPath() + saveDirectory + "/" + req.getParameter("jrArImg");
			System.out.println(action);
			String str1 = req.getParameter("jfNo");
			System.out.println(str1);
			String str2 = req.getParameter("admId");
			System.out.println(str2);
			String str3 = req.getParameter("jfName");
			System.out.println(str3);
			String str4 = req.getParameter("jfStartTime");
			System.out.println(str4);
			String str5 = req.getParameter("jfEndTime");
			System.out.println(str5);
			String str6 = req.getParameter("regStartTime");
			System.out.println(str6);
			String str7 = req.getParameter("regEndTime");
			System.out.println(str7);
			String str8 = req.getParameter("jfActivity");
			System.out.println(str8);
			String str9 = req.getParameter("jrArImg");
			System.out.println(str9);
			JobFairVo jobFairVO=new JobFairVo();
			jobFairVO.setJfNo(Integer.valueOf(str1));
			jobFairVO.setAdmId(Integer.valueOf(str2));
			jobFairVO.setJfName(str3);
			jobFairVO.setJfStartTime(Date.valueOf(str4));
			jobFairVO.setJfEndTime(Date.valueOf(str5));
			jobFairVO.setRegStartTime(Date.valueOf(str6));
			jobFairVO.setRegEndTime(Date.valueOf(str7));
			jobFairVO.setJfActivity(str8);
			jobFairVO.setJrArImg(filePath);
			JobFairDaoImpl dao = new JobFairDaoImpl();
			dao.update(jobFairVO);
			String sucess= "編輯成功";
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
