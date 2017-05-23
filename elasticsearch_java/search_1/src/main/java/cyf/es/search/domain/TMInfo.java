package cyf.es.search.domain;

/**
 * Created by yufei on 2017/5/21.
 */
public class TMInfo {
    private Integer tmClass;

    private String tmCn;

    private String tmEn;

    private String tmApplicant;

    private Integer id;

    private String tmdetail;

    private String tmRegNbr;

    private Integer pageCnt;

    private Integer rowStart; // 起始行

    private Integer totalCnt; // 总记录数


    public String getTmEn() {
        return tmEn;
    }

    public void setTmEn(String tmEn) {
        this.tmEn = tmEn;
    }

    public Integer getTmClass() {
        return tmClass;
    }

    public void setTmClass(Integer tmClass) {
        this.tmClass = tmClass;
    }

    public String getTmCn() {
        return tmCn;
    }

    public void setTmCn(String tmCn) {
        this.tmCn = tmCn;
    }

    public String getTmApplicant() {
        return tmApplicant;
    }

    public void setTmApplicant(String tmApplicant) {
        this.tmApplicant = tmApplicant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTmdetail() {
        return tmdetail;
    }

    public void setTmdetail(String tmdetail) {
        this.tmdetail = tmdetail;
    }

    public String getTmRegNbr() {
        return tmRegNbr;
    }

    public void setTmRegNbr(String tmRegNbr) {
        this.tmRegNbr = tmRegNbr;
    }

    public Integer getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(Integer pageCnt) {
        this.pageCnt = pageCnt;
    }

    public Integer getRowStart() {
        return rowStart;
    }

    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }
}
