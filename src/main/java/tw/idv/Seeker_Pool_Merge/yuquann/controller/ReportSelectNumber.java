package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.ReportEnterpriseDao;


@WebServlet("/ReportSelectNumber")
public class ReportSelectNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		String reNoStr = req.getParameter("reNoStr");
		
		if(reNoStr != "") {
			
			int reNo = Integer.parseInt(reNoStr);

			ReportEnterpriseDao dao = new ReportEnterpriseDao();

			Gson gson = new Gson();
			String json = gson.toJson(dao.selectNumber(reNo));
//			System.out.println("json : " + json);
			out.print(json);

		}else {
			System.out.println("檢舉單號輸入為空值");
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public ReportSelectNumber() {
		super();
	}
}
