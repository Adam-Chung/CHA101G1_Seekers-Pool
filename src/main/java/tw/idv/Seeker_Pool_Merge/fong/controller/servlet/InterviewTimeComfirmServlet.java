package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import redis.clients.jedis.Jedis;
import tw.idv.Seeker_Pool_Merge.common.util.JedisUtil;
import tw.idv.Seeker_Pool_Merge.fong.service.ApplyRecordService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.ApplyRecordServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.InterviewTimeShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/InterviewTimeComfirmServlet")
public class InterviewTimeComfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplyRecordService applyRecordService = new ApplyRecordServiceImpl();
	Jedis jedis = JedisUtil.getJedis();// 獲取jedis
	Gson gson = new Gson();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String checkbtn = req.getParameter("checkbtn");
		String checktime = req.getParameter("checktime");
//		InterviewTimeShowVo interviewTime = (InterviewTimeShowVo) req.getServletContext().getAttribute(checktime);
		
		String hget = jedis.get(checktime);
		InterviewTimeShowVo interviewTime;
		
//		System.out.println(interviewTime.getDateTime());
		
		ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
		
		if(checkbtn == null) { //沒按確認，只有轉跳到此頁
			if(hget == null) {
				//回傳錯誤訊息
				info.setFlag(false);
				info.setMsg("勿亂改網址，請重新點選時段，或面試時間已確認完畢");
			}else {
				interviewTime = gson.fromJson(hget, new TypeToken<InterviewTimeShowVo>() {}.getType());
				info.setFlag(true);
				info.setData(interviewTime.getDateTime());
			}
			
		}else { //按下確認
			
			if(hget == null) {
				//回傳錯誤訊息
				info.setFlag(false);
				info.setMsg("勿亂改網址，請重新點選時段，或面試時間已確認完畢");
			}else {
				interviewTime = gson.fromJson(hget, new TypeToken<InterviewTimeShowVo>() {}.getType());
				applyRecordService.updateInterviewTime(interviewTime.getJobId(), interviewTime.getMember().getMemId(),  interviewTime.getDateTime());
				info.setFlag(true);
				info.setMsg("確認成功，請登入後至應徵紀錄查看");
				
				
				//刪除ServletContext 裡面用過的東西
//				String CheckTimeKeysName  = interviewTime.getMember().getMemId()+"XX"+interviewTime.getJobId();
//				@SuppressWarnings("unchecked") //不用確認此強轉
//				List<String> CheckTimeKeys = (ArrayList<String>) req.getServletContext().getAttribute(CheckTimeKeysName);
//				req.getServletContext().removeAttribute(CheckTimeKeysName);
				
				//刪除Redis裡面東西
				List<String> CheckTimeKeys = interviewTime.getCheckTimeKeys();
				
				for (int i = 0; i < CheckTimeKeys.size(); i++) {
//					req.getServletContext().removeAttribute(CheckTimeKeys.get(i));
					jedis.del(CheckTimeKeys.get(i));
				}
			}
		}
		//轉為json寫回客戶端
		String jsonStr =  new JSONObject(info).toString();  
		resp.getWriter().write(jsonStr);
		
		jedis.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}