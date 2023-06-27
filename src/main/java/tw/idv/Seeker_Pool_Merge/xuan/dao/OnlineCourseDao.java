package tw.idv.Seeker_Pool_Merge.xuan.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;


public interface OnlineCourseDao {
	
	public void createOnlineCourse(OnlineCourseVo onlineCourse);

	public List<OnlineCourseVo> getAllOnlineCourses();

	public List<OnlineCourseVo> searchOnlineCoursesByTitle(String onTitle);
	
	
	public void updateOnlineCourse(OnlineCourseVo onlineCourse);

	public void deleteOnlineCourse(int onNo);
}
