package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tw.idv.Seeker_Pool_Merge.jamie.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.ApplyRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/FrontEnd/InterviewComplete")
public class InterviewCompleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ApplyRecordDao dao = new ApplyRecordDaoImpl();
	ResultInfo resultInfo = new ResultInfo();
	CompanyMemberVo testCompanyMember = new CompanyMemberVo();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int comMemId = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();

		int comMemId = (int) req.getSession().getAttribute("companyMember");
		
		/*
		 * axios預設會把POST請求的參數轉換為 JSON 格式放在請求的 body 中， 所以需要使用 getReader() 方法來讀取請求的
		 * body，並解析 JSON 格式的參數
		 */
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		// 將請求體的 JSON 數據解析為一個 Map<String, Object> 對象
		Map<String, Object> map = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
		}.getType());

		// Gson 會將數字轉換成 Double 類型，先轉換成 Double，再轉換為 Integer
		Integer memId = ((Double) map.get("memId")).intValue();
		Integer jobNo = ((Double) map.get("jobNo")).intValue();

//		System.out.println("要修改的會員ID是: " + memId);
//		System.out.println("該會員應徵的工作是: " + jobNo);
		int updateRows = dao.updateHireStatus(memId, comMemId, jobNo);

		String jsonStr;

		if (updateRows == 1) {
			resultInfo.setFlag(true);
//			System.out.println("更新面試狀態 = 完成 : 成功");
		} else {
			resultInfo.setFlag(false);
//			System.out.println("更新面試狀態 = 完成 : 失敗");
		}

		jsonStr = gson.toJson(resultInfo);

		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
		out.flush();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
