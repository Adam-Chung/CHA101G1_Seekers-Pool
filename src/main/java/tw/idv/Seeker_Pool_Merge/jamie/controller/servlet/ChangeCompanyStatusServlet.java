package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/BackEnd/ChangeCompanyStatus")
public class ChangeCompanyStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	ResultInfo resultInfo = new ResultInfo();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idStr = req.getParameter("id");
		String statusStr = req.getParameter("comStatus");
		System.out.println(idStr);
		System.out.println(statusStr);

		if (idStr != null && statusStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				int status = Integer.parseInt(statusStr);
//				System.out.println("現在的企業ID是: " + id);
//				System.out.println("現在的企業帳號狀態是: " + status);
				
				CompanyMemberVo companyMember = service.getCompanyMemberById(id);
				companyMember.setComStatus(status);
				service.updateCompanyStatus(companyMember);
				System.out.println(companyMember);
//				System.out.println("更新成功");

				resultInfo.setFlag(true);
				String jsonStr = new JSONObject(resultInfo).toString();
				resp.setContentType("application/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(jsonStr);
				out.flush();

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
