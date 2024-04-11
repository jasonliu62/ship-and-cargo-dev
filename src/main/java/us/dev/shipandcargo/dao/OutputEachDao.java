package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import us.dev.shipandcargo.domain.OutputEach;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

public interface OutputEachDao {

    int insertOutput(OutputEach outputEach);

    // 删除绑定在Output上的每一条OutputEach
    int deleteOutputEachsBy3Ids(@Param("groupId") Long groupId, @Param("outputId") Long outputId, @Param("uploaderId") Long uploaderId);

    int deleteFromHistory(@Param("groupId") Long groupId, @Param("uploaderId") Long uploaderId);

    List<OutputEach> queryByCondition(
            @Param("groupId") Long groupId,
            @Param("outputId") Long outputId,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortByList
    );
}
