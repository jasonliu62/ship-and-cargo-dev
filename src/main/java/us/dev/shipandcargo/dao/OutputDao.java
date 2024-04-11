package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import us.dev.shipandcargo.domain.Output;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

public interface OutputDao {

    int insertOutput(Output output);

    int deleteOutputBy3Ids(@Param("groupId") Long groupId, @Param("id") Long id, @Param("uploaderId") Long uploaderId);

    int deleteFromHistory(@Param("groupId") Long groupId, @Param("uploaderId") Long uploaderId);

    List<Output> queryByCondition(
            @Param("groupId") Long groupId,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortByList
    );

}
