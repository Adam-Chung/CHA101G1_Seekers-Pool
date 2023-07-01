package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobOrderDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;

@WebServlet("/JobUpTopNumStatusServlet")
public class JobUpTopNumStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobOrderDao JoDao;
//	private JobOrderDao JoDao = new JobOrderDaoImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());
		
		// 更新企業已上架數量(companyjobselect.html)
		if ("JobUploadNum".equals(action)) {
//			System.out.println(action);
			ComOrderQueryVO coqv=new ComOrderQueryVO();
			// 要改成動態企業ID
			int comMemId = (int) req.getSession().getAttribute("companyMember");
			 // Integer comMemId=1;
			coqv.setComMemId(comMemId);
			List<ComOrderQueryVO> list = JoDao.updateJobUploadNum(coqv);
//			System.out.println("list" + list);
			writePojo2Json(resp, coqv);
		}
		
		// 更新企業已置頂數量(companyjobselect.html)
		if ("JobTopNum".equals(action)) {
//			System.out.println(action);
			ComOrderQueryVO coqv=new ComOrderQueryVO();
			// 要改成動態企業ID
			int comMemId = (int) req.getSession().getAttribute("companyMember");
			// Integer comMemId=1;
			
			coqv.setComMemId(comMemId);
			List<ComOrderQueryVO> list = JoDao.updateJobTopNum(coqv);
//			System.out.println("list" + list);
			writePojo2Json(resp, list);
		}

	}

}
