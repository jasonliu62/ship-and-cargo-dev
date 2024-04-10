package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import us.dev.shipandcargo.domain.Output;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

public interface OutputDao {

    int insertOutput(Output output);

    int deleteOutputBy3Ids(@Param("groupId") Long groupId, @Param("id") Long id, @Param("uploaderId") Long uploaderId);

    List<Output> queryByCondition(
            @Param("groupId") Long groupId,
            @Param("id") Long id,
            @Param("actual_own") Float actual_own,
            @Param("actual_rent") Float actual_rent,
            @Param("virtual_own") Float virtual_own,
            @Param("virtual_rent") Float virtual_rent,
            @Param("spot_own") Float spot_own,
            @Param("spot_rent") Float spot_rent,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortByList
    );

}
