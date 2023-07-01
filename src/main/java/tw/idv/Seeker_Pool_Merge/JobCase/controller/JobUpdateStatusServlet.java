package tw.idv.Seeker_Pool_Merge.JobCase.controller;
import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobVO;

@WebServlet("/JobUpdateStatusServlet")
public class JobUpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private JobDao JobDao;
//	private JobDao JobDao = new JobDaoImpl();
	private JobVO JobVo = new JobVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());
		
		// 更新上架&狀態
		if("updateJobUpload".equals(action)) {
			String jobNo = req.getParameter("jobNo");
//			System.out.println(jobNo);
			String jobUpload=req.getParameter("jobUpload");
//			System.out.println(jobUpload);
			String jobStatus=jobUpload;
			JobVo.setJobNo(Integer.valueOf(jobNo));
			JobVo.setJobUpload(Integer.valueOf(jobUpload));
			JobVo.setJobStatus(Integer.valueOf(jobStatus));
			JobDao.updateJobUploadStatus(JobVo);
			writePojo2Json(resp, "上架!");
		}
		// 更新職缺置頂
		if("updateJobTopload".equals(action)) {
			String jobNo = req.getParameter("jobNo");
			String jobTop=req.getParameter("jobTop");
			JobVo.setJobNo(Integer.valueOf(jobNo));
			JobVo.setJobTop(Integer.valueOf(jobTop));
			JobDao.updateJobTopStatus(JobVo);
			writePojo2Json(resp, "置頂!");
		}
		
	}
}
