package us.dev.shipandcargo.request.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiModel;
import us.dev.shipandcargo.domain.Paging;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "PageData", description = "page data")
public class PageData<T> {
    @JsonIgnore
    private long total;
    private List<T> list = new ArrayList<T>();
    @JsonIgnore
    private long size;
    @JsonIgnore
    private long startRow;
    @JsonIgnore
    private long endRow;

    private Paging paging;

    private ObjectNode ext = null;

    public Paging getPaging() {
        Paging newPaging = new Paging();
        newPaging.setSize(size);
        // client端希望获取0 base的数据结构
        newPaging.setStartRow(size > 0 ? startRow - 1 : 0);
        newPaging.setEndRow(size > 0 ? endRow - 1 : 0);
        newPaging.setTotal(total);
        if (total != 0 && endRow != 0) {
            newPaging.setHasMore(total > endRow);
        } else {
            newPaging.setHasMore(false);
        }
        return newPaging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    public ObjectNode getExt() {
        return ext;
    }

    public void setExt(ObjectNode ext) {
        this.ext = ext;
    }
}
