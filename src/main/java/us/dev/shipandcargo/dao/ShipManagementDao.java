package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.ShipManagement;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

@Repository
public interface ShipManagementDao {
    int insertShipManagement(ShipManagement shipManagement);

    ShipManagement selectShipManagementByImo(@Param("imo") Long imo);

    List<ShipManagement> selectShipManagementByShipOperatingDay(@Param("shipOperatingDay") Float shipOperatingDay);

    List<ShipManagement> selectShipManagementByShipTotalTime(@Param("shipTotalTime") Float shipTotalTime);

    List<ShipManagement> selectShipManagementByDailyCapitalCost(@Param("dailyCapitalCost") Float dailyCapitalCost);

    List<ShipManagement> selectShipManagementByDailyOperatingCost(@Param("dailyOperatingCost") Float dailyOperatingCost);

    List<ShipManagement> selectShipManagementByAcceptableCargoType(@Param("acceptableCargoType") String acceptableCargoType);

    List<ShipManagement> selectShipManagementByOwner(@Param("owner") String owner);

    List<ShipManagement> selectShipManagementByOperator(@Param("operator") String operator);

    List<ShipManagement> selectShipManagementByNavigationArea(@Param("navigationArea") String navigationArea);

    List<ShipManagement> selectShipManagementByShipDepartment(@Param("shipDepartment") String shipDepartment);

    List<ShipManagement> selectShipManagementByShipEmptyPort(@Param("shipEmptyPort") String shipEmptyPort);

    List<ShipManagement> selectShipManagementByShipEmptyTime(@Param("shipEmptyTime") LocalDateTime shipEmptyTime);

    List<ShipManagement> selectShipManagementByUploaderId(@Param("uploaderId") Long uploaderId);

    List<ShipManagement> selectShipManagementByCreatedAt(@Param("createdAt") Date createdAt);

    List<ShipManagement> selectShipManagementByModifiedAt(@Param("modifiedAt") Date modifiedAt);

    int deleteShipManagementByImo(@Param("imo") Long imo);

    int updateShipManagement(ShipManagement shipManagement);

    List<ShipManagement> queryByCondition(
            @Param("imo") Long imo,
            @Param("shipOperatingDay") Float shipOperatingDay,
            @Param("shipTotalTime") Float shipTotalTime,
            @Param("dailyCapitalCost") Float dailyCapitalCost,
            @Param("dailyOperatingCost") Float dailyOperatingCost,
            @Param("acceptableCargoType") String acceptableCargoType,
            @Param("owner") String owner,
            @Param("operator") String operator,
            @Param("navigationArea") String navigationArea,
            @Param("shipDepartment") String shipDepartment,
            @Param("shipEmptyPort") String shipEmptyPort,
            @Param("shipEmptyTime") LocalDateTime shipEmptyTime,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);

}
