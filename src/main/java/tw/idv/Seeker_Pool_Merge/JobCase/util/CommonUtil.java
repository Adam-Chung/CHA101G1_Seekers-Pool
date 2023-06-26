package tw.idv.Seeker_Pool_Merge.JobCase.util;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonUtil {
	public static final Gson GSON = new GsonBuilder().create();
	public static final String JSON_MIME_TYPE = "application/json";
	public static final String PREFIX_WEB_INF = "/WEB-INF";
	
	public static <P> P json2Pojo(HttpServletRequest req, Class<P> classOfPojo) {
		try (BufferedReader br = req.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse resp, P pojo) {
		resp.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = resp.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
