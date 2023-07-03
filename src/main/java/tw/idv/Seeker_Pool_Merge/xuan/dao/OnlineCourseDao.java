package tw.idv.Seeker_Pool_Merge.xuan.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;


public interface OnlineCourseDao {
	//新增課程
	public void createOnlineCourse(OnlineCourseVo onlineCourse);
	//管理列表
	public List<OnlineCourseVo> getAllOnlineCourses();
	//上架列表
	public List<OnlineCourseVo> getOnlineCourses();
	//課程查詢
	public List<OnlineCourseVo> searchOnlineCoursesByTitle(String onTitle);
	
	public void updateOnlineCourse(OnlineCourseVo onlineCourse);
	
	public void deleteOnlineCourse(int onNo);
}
