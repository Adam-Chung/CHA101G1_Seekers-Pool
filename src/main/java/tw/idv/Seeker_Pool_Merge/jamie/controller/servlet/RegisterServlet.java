package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/CompanyRegister")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();

//	@Autowired
//	private CompanyMemberService service;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 設置編碼
		req.setCharacterEncoding("utf-8");

		// 1. 取得客戶端傳來的所有「請求參數名稱」和「字串陣列的值」
		Map<String, String[]> map = req.getParameterMap();

		// 2. 封裝物件
		CompanyMemberVo companyMember = new CompanyMemberVo();
		try {
			BeanUtils.populate(companyMember, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 讀取圖片
		Part part = req.getPart("comPicture");  // 從HTTP請求中獲得上傳物件的參數
		String fieldName = part.getName();  // 獲取上傳檔案的原始檔名 + 時間戳
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		String formatDateTime = now.format(formatter);
		String fileName = formatDateTime + "-" + part.getSubmittedFileName();
//		System.out.println("File name: " + fileName);

		if ("comPicture".equals(fieldName)) {
			String saveDirectory = "/uploads/comPicture";
			String realPath = getServletContext().getRealPath(saveDirectory);
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();  // 檢查檔案目錄是否存在，不存在則用 mkdirs() 建多個資料夾
			}
			File f = new File(fsaveDirectory, fileName);
			part.write(f.toString());  // part類別的方法write() 利用File物件, 寫入指定的目錄
			companyMember.setComPicture(req.getContextPath() + saveDirectory + "/" + fileName);
		}

		// 3. 調用Service完成註冊
		boolean flag = service.registerCompanyMember(companyMember);
		ResultInfo resultInfo = new ResultInfo();  // 用於封裝資訊回傳給前端

		// 4. 回應結果
		if (flag) {
			// 註冊成功
			resultInfo.setFlag(true);
			// 讓使用者直接登入
			CompanyMemberVo loginComMem = service.login(companyMember);
			int comMemId = loginComMem.getComMemId();
			req.getSession().setAttribute("companyMember", comMemId);
//			System.out.println("========================");
//			System.out.println("註冊成功，會員為：" + comMemId);
		} else {
			// 註冊失敗
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("您的帳號名稱已被使用");
//			System.out.println("註冊失敗");
		}

		// 5. 將info物件序列化為JSON傳到前端
		String jsonStr = new JSONObject(resultInfo).toString();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}