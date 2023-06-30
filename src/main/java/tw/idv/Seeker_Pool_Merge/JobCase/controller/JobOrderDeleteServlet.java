package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobOrderDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;

@WebServlet("/JobOrderDeleteServlet")
public class JobOrderDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobOrderDao JoDao = new JobOrderDaoImpl();
//	private JobOrderDao JoDao = new JobOrderDaoImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
//		System.out.println("request.getContentType(): " + req.getContentType());
//		System.out.println("進入delete");

		// Step1: 接收請求參數
		Integer joNo = Integer.valueOf(req.getParameter("joNo"));

		// Step2: 開始刪除資料
		if (joNo != null) {
			JobOrderVO jo = JoDao.selectByNo(joNo);
			if (jo != null) {
				JoDao.delete(jo);
			}
		}

	}
}
