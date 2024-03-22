package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.ShipActiveMissionLocation;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.Date;
import java.util.List;

@Repository
public interface ShipActiveMissionLocationDao {

    int insertShipActiveMissionLocation(ShipActiveMissionLocation record);

    ShipActiveMissionLocation selectShipActiveMissionLocationByImo(@Param("imo") Long imo);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByLatitude(@Param("latitude") Float latitude);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByLongitude(@Param("longitude") Float longitude);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByPostPortId(@Param("postPortId") String postPortId);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByNextPortId(@Param("nextPortId") String nextPortId);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationBySpeed(@Param("speed") Float speed);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByDepartureDistance(@Param("departureDistance") Float departureDistance);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByArrivalDistance(@Param("arrivalDistance") Float arrivalDistance);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByCurrentTime(@Param("currentTime") Date currentTime);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByUploaderId(@Param("uploaderId") Long uploaderId);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByCreatedAt(@Param("createdAt") Date createdAt);

    List<ShipActiveMissionLocation> selectShipActiveMissionLocationByModifiedAt(@Param("modifiedAt") Date modifiedAt);

    int deleteShipActiveMissionLocationByImo(@Param("imo") Long imo);

    int updateShipActiveMissionLocation(ShipActiveMissionLocation record);

    List<ShipActiveMissionLocation> queryByCondition(
            @Param("imo") Long imo,
            @Param("latitude") Float latitude,
            @Param("longitude") Float longitude,
            @Param("postPortId") String postPortId,
            @Param("nextPortId") String nextPortId,
            @Param("speed") Float speed,
            @Param("departureDistance") Float departureDistance,
            @Param("arrivalDistance") Float arrivalDistance,
            @Param("currentTime") Date currentTime,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);
}
