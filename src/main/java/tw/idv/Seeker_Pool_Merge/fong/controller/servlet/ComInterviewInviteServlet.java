package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import tw.idv.Seeker_Pool_Merge.common.util.JedisUtil;
import tw.idv.Seeker_Pool_Merge.common.util.RandCodeUtil;
import tw.idv.Seeker_Pool_Merge.fong.service.ComApplyRecordService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.ComApplyRecordServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.InterviewTimeShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/ComInterviewInviteServlet")
public class ComInterviewInviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComApplyRecordService comApplyRecordService = new ComApplyRecordServiceImpl();
	Jedis jedis = JedisUtil.getJedis();// 獲取jedis
	Gson gson = new Gson();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");
		String date3 = req.getParameter("date3");
		Integer memId = Integer.valueOf(req.getParameter("memId"));
		Integer jobNo = Integer.valueOf(req.getParameter("jobNo"));
//		System.out.println(date1 + "\n" + date2 + "\n" + date3 + "\n memId=>"+ memId + "\n jobId=>"+ jobId);

		MemberVo member = comApplyRecordService.getMemberById(memId);

		// ======之後公司會登入，所以可以獲取 但目前還沒先用假登入資料==========
		Integer comId = (Integer) req.getSession().getAttribute("companyMember");
		System.out.println("comId = " + comId);
		String comName = comApplyRecordService.getComNameBycomId(comId);
		
		// 然後再用comId獲取所有com訊息
		CompanyMemberShowVo company = new CompanyMemberShowVo();
		company.setComMemId(comId);
		company.setComName(comName);

		// 獲取專案名稱
		String contextPath = req.getContextPath(); // /SeekerPool

		// 獲得InterviewTimeVo
		InterviewTimeShowVo interviewTime1 = new InterviewTimeShowVo();
		InterviewTimeShowVo interviewTime2 = new InterviewTimeShowVo();
		InterviewTimeShowVo interviewTime3 = new InterviewTimeShowVo();

		List<String> CheckTimeKeys = new ArrayList<String>(); // 此list建立為了將存入ServletContext的資料刪除，裡面儲存所有應徵日期要傳出去ServletContext的key

		for (int i = 0; i < 3; i++) {
			String randomCode = RandCodeUtil.getRandomCode(4);
			CheckTimeKeys.add(memId + randomCode);
		}
		// 透過 memId+"XX"+jobId 的key有唯一性 也有指定性 也相對有安全性
//		req.getServletContext().setAttribute(memId+"XX"+jobNo, CheckTimeKeys);

		// 填入第1個預約時間資訊
		interviewTime1.setCheckTimeKey(CheckTimeKeys.get(0));
		interviewTime1.setDateTime(date1);
		interviewTime1.setCompany(company);
		interviewTime1.setJobId(jobNo); 
		interviewTime1.setMember(member);
		interviewTime1.setCheckTimeKeys(CheckTimeKeys);
//		req.getServletContext().setAttribute(interviewTime1.getCheckTimeKey(), interviewTime1); //之後換成 Redis
		String json1 = gson.toJson(interviewTime1);
		jedis.set(interviewTime1.getCheckTimeKey(), json1);
//		jedis.expire(interviewTime1.getCheckTimeKey(), 20L);  //時效性

		// 填入第2個預約時間資訊
		if (date2 != null) {
			interviewTime2.setCheckTimeKey(CheckTimeKeys.get(1));
			interviewTime2.setDateTime(date2);
			interviewTime2.setCompany(company);
			interviewTime2.setJobId(jobNo);
			interviewTime2.setMember(member);
			interviewTime2.setCheckTimeKeys(CheckTimeKeys);
//			req.getServletContext().setAttribute(interviewTime2.getCheckTimeKey(), interviewTime2);
			String json2 = gson.toJson(interviewTime2);
			jedis.set(interviewTime2.getCheckTimeKey(), json2);

		}
		// 填入第3個預約時間資訊
		if (date3 != null) {
			interviewTime3.setCheckTimeKey(CheckTimeKeys.get(2));
			interviewTime3.setDateTime(date3);
			interviewTime3.setCompany(company);
			interviewTime3.setJobId(jobNo);
			interviewTime3.setMember(member);
			interviewTime3.setCheckTimeKeys(CheckTimeKeys);
//			req.getServletContext().setAttribute(interviewTime3.getCheckTimeKey(), interviewTime3);
			String json3 = gson.toJson(interviewTime3);
			jedis.set(interviewTime3.getCheckTimeKey(), json3);
		}

		boolean flag = comApplyRecordService.InterviewInvite(memId, company, jobNo, contextPath, interviewTime1, interviewTime2, interviewTime3);
		
		ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
		// 響應結果
		if (flag) {
			// 寄信成功
			info.setFlag(true);

		} else {
			// 寄信失敗
			info.setFlag(false);
		}
		//轉為json寫回客戶端
    	String jsonStr =  new JSONObject(info).toString();  //Json.jar
    	resp.getWriter().write(jsonStr);
		System.out.println(jsonStr);
		jedis.close();  

	}
 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}