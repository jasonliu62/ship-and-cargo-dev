package us.dev.shipandcargo.request.paging;

import lombok.Data;

@Data
public class PaginationOrderQuery {
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    // (allan) added to be compatible with Ant Design table list sort component
    public static final String ASCEND = "ascend";
    public static final String DESCEND = "descend";

    private String sortName;
    private String sortOrder;

    public PaginationOrderQuery() {}

    public PaginationOrderQuery(String sortName, String sortOrder) {
        this.sortName = sortName;
        this.sortOrder = sortOrder;
    }

    public static PaginationOrderQuery byAsc(String sortName) {
        return new PaginationOrderQuery(sortName, ASC);
    }

    public static PaginationOrderQuery byDesc(String sortName) {
        return new PaginationOrderQuery(sortName, DESC);
    }
}

