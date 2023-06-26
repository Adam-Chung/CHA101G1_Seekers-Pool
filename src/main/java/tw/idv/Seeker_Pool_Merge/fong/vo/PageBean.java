package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.util.List;

public class PageBean<T> implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
    private int totalCount; //總紀錄數量
    private int totalPage; //總頁數
    private int currentPage; //當前頁碼
    private int pageSize; //每頁顯示條數
    private List<T> list; //每一頁顯示的資料(數據)集合

    @Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", list=" + list + "]";
	}

	public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
