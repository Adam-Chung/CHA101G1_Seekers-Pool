package tw.idv.Seeker_Pool_Merge.yuquann.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.Report_EnterpriseDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.Report_EnterpriseVo;

@WebServlet("/Report_EnterpriseService")
@MultipartConfig
public class Report_EnterpriseService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String rjt_nostr = req.getParameter("rjtNo");
		int rjtNo = Integer.parseInt(rjt_nostr);
		String reContent = req.getParameter("reContent");
		Date reEndTime = null;
		int reStatus = 0;
		int reResult = 0;
		Part reUpload = req.getPart("reUpload"); // "reUpload" 是 HTML 表單中文件上傳欄位的名稱

		Report_EnterpriseVo vo = new Report_EnterpriseVo(rjtNo, reContent, reEndTime, reStatus, reResult, reUpload);
		Report_EnterpriseDao dao = new Report_EnterpriseDao();
		PrintWriter out = res.getWriter();

		try {
			res.setContentType("text/html;charset=utf-8");
			res.setCharacterEncoding("UTF-8");

			int result = dao.insert(vo);
			System.out.println("成功新增行數 : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

	public Report_EnterpriseService() {
		super();

	}

}
