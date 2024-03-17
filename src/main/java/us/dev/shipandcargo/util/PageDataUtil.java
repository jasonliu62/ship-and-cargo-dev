package us.dev.shipandcargo.util;

import com.github.pagehelper.Page;
import us.dev.shipandcargo.request.paging.PageData;

import java.util.Collection;
import java.util.List;

public class PageDataUtil {

    public static <T> PageData<T> convertToPageData(List list, List<T> voList) {
        PageData<T> pageData = new PageData<>();
        if (list instanceof Page) {
            Page page = (Page)list;
            pageData.setSize(page.size());
            pageData.setStartRow(pageData.getSize() > 0 ? page.getStartRow() + 1 : 0);
            // 计算实际的endRow（最后一页的时候特殊）
            pageData.setEndRow(pageData.getSize() > 0 ? pageData.getStartRow() - 1 + pageData.getSize() : 0);
            pageData.setTotal(page.getTotal());
        } else if (list instanceof Collection) {
            pageData.setStartRow(0);
            pageData.setEndRow(list.size() > 0 ? list.size() - 1 : 0);
            pageData.setSize(list.size());
        }
        pageData.setList(voList);
        return pageData;
    }

    /**
     * admin apis would like to return db entities directly
     *
     * @param <T>
     * @param list
     * @return paging list
     */
    public static <T> PageData<T> convertToPageData(List<T> list) {
        PageData<T> pageData = new PageData<>();
        if (list instanceof Page) {
            Page page = (Page)list;
            pageData.setSize(page.size());
            pageData.setStartRow(pageData.getSize() > 0 ? page.getStartRow() + 1 : 0);
            // 计算实际的endRow（最后一页的时候特殊）
            pageData.setEndRow(pageData.getSize() > 0 ? pageData.getStartRow() - 1 + pageData.getSize() : 0);
            pageData.setTotal(page.getTotal());
        } else if (list instanceof Collection) {
            pageData.setStartRow(0);
            pageData.setEndRow(list.size() > 0 ? list.size() - 1 : 0);
            pageData.setSize(list.size());
        }
        pageData.setList(list);
        return pageData;
    }
}
