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
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;
@WebServlet("/JobOrderQueryServlet")
public class JobOrderQueryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Autowired
	private JobOrderDao JoDao;
//	private JobOrderDao JoDao = new JobOrderDaoImpl();
//	private JobOrderVO JoVO = new JobOrderVO();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());
		
		if ("selectComOrderAll".equals(action)) {
//			System.out.println("進入 selectComOrderAll");
//			System.out.println(action);
			// 要改動態生成企業編號
			int comMemId = (int) req.getSession().getAttribute("companyMember");
			// Integer comMemId=1;
			ComOrderQueryVO coqo=new ComOrderQueryVO();
			coqo.setComMemId(comMemId);
			List<ComOrderQueryVO> list = JoDao.comMemIdCase(coqo);
			
			writePojo2Json(resp, list);
		}
		
		if ("selectComOrderOne".equals(action)) {
//			System.out.println("進入 selectComOrderOne");
			String joNo = req.getParameter("joNo");
			Integer JoNo=Integer.valueOf(joNo);
			// 要改動態生成企業編號
			int comMemId = (int) req.getSession().getAttribute("companyMember");

			// Integer comMemId=1;
			ComOrderQueryVO coqo=new ComOrderQueryVO();
			coqo.setComMemId(comMemId);
			coqo.setJoNo(JoNo);
			List<ComOrderQueryVO> list = JoDao.selectComOrderOne(coqo);
//			System.out.println(list);
			writePojo2Json(resp, list);
		}
	}
}
