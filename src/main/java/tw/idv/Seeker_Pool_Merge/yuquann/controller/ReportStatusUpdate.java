package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.ReportEnterpriseDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ReportEnterpriseVo;

@WebServlet("/ReportStatusUpdate")
public class ReportStatusUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 設置請求編碼
		req.setCharacterEncoding("utf-8");
		// 取得網頁提供的參數
		String reNoStr = req.getParameter("reNo");
		int reNo = Integer.parseInt(reNoStr);
//		System.out.println("reNo : " + reNo);

		String reStatusStr = req.getParameter("reStatus");
		int reStatus = Integer.parseInt(reStatusStr);
//		System.out.println("reStatus : " + reStatus);

		String reResultStr = req.getParameter("reResult");
		int reResult = Integer.parseInt(reResultStr);
//		System.out.println("reResult : " + reResult);

		// 建立VO實體裝入參數
		ReportEnterpriseVo vo = new ReportEnterpriseVo();
		vo.setReNo(reNo);
		vo.setReStatus(reStatus);
		vo.setReResult(reResult);
		// 建立DAO呼叫update方法
		ReportEnterpriseDao dao = new ReportEnterpriseDao();

		try {
			// 把respones的編碼設定好
			res.setContentType("text/html;charset=utf-8");
			// 呼叫update方法並回傳修改行數
			dao.update(vo);
//			System.out.println("成功修改行數 : " + result);
			// 重新導向到指定的URL
			res.sendRedirect("/SeekerPool/back-end/report/JobReportShow.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public ReportStatusUpdate() {
		super();
	}
}
