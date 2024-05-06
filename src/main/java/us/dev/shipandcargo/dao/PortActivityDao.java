package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.PortActivity;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

@Repository
public interface PortActivityDao {

    int insertPortActivity(PortActivity portActivity);

    PortActivity selectPortActivityByPortId(@Param("portId") String portId);

    List<PortActivity> selectPortActivityByShipAmount(@Param("shipAmount") Long shipAmount);

    int updatePortActivity(PortActivity portActivity);

    int deletePortActivityByPortId(@Param("portId") Long portId);

    List<PortActivity> queryByCondition(
            @Param("portId") Long portId,
            @Param("shipAmount") Long shipAmount,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);
}
