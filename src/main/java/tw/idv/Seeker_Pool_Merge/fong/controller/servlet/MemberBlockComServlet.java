package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.fong.service.CompanyService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.CompanyServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/MemberBlockComServlet")
public class MemberBlockComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService companyService = new CompanyServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String addCompanyName = req.getParameter("addCompanyName");
		String deleteBlockCom = req.getParameter("deleteBlockCom");
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");

		if (deleteBlockCom != null) { // 刪除屏蔽公司
			deleteBlockCom = deleteBlockCom.trim();
			companyService.deletBlockComByName(memId, deleteBlockCom);
			
			
		} else if (addCompanyName != null) { // 新增屏蔽公司
			addCompanyName = addCompanyName.trim();
			ResultInfo info = companyService.addBlockComByName(memId, addCompanyName);
			String jsonStr = new JSONObject(info).toString();
			resp.getWriter().write(jsonStr);
			
		} else { // 把屏蔽公司明細顯示出來
			List<CompanyMemberShowVo> blockComs = companyService.findBlockComsByMemId(memId);

			// list要用JSONArray
			String jsonStr = new JSONArray(blockComs).toString();
			resp.getWriter().write(jsonStr);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}