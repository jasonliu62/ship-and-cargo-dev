package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.ShipIdleLocation;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.Date;
import java.util.List;

@Repository

public interface ShipIdleLocationDao {

    int insertShipIdleLocation(ShipIdleLocation record);

    ShipIdleLocation selectShipIdleLocationByImo(@Param("imo") Long imo);

    List<ShipIdleLocation> selectShipIdleLocationByPortId(@Param("portId") String portId);

    List<ShipIdleLocation> selectShipIdleLocationByUploaderId(@Param("uploaderId") Long uploaderId);

    List<ShipIdleLocation> selectShipIdleLocationByCreatedAt(@Param("createdAt") Date createdAt);

    List<ShipIdleLocation> selectShipIdleLocationByModifiedAt(@Param("modifiedAt") Date modifiedAt);

    int deleteShipIdleLocationByImo(@Param("imo") Long imo);

    int updateShipIdleLocation(ShipIdleLocation record);

    List<ShipIdleLocation> queryByCondition(
            @Param("imo") Long imo,
            @Param("portId") String portId,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);

}
