package help.lixin.starlink.plugin.xxl.job.api.dto;

import java.util.ArrayList;
import java.util.List;

import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;

public class JobInfoResponse {
    // 总记录数
    private int recordsTotal;
    // 过滤后的总记录数
    private int recordsFiltered;
    private List<XxlJobInfo> data = new ArrayList<>();

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<XxlJobInfo> getData() {
        return data;
    }

    public void setData(List<XxlJobInfo> data) {
        if (null != data) {
            this.data = data;
        }
    }
}
