package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class PageBean {
    private Long total; // 總記錄數
    private List rows;  // 當前頁面資料列表

    public PageBean() {
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public PageBean(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
