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

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComJobQueryVO;

@WebServlet("/JobQueryStatusServlet")
public class JobQueryStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobDao JobDao;
//	private JobDao JobDao = new JobDaoImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());

		if ("selectJobStatus".equals(action)) {
			System.out.println(action);
			ComJobQueryVO vo=new ComJobQueryVO();
			// 要改成動態企業ID
			int comMemId = (int) req.getSession().getAttribute("companyMember");
			// Integer comMemId=1;
			vo.setComMemId(comMemId);
			List<ComJobQueryVO> list = JobDao.selectJobStatus(vo);
//			System.out.println("list" + list);
			writePojo2Json(resp, list);
		}

	}

}
