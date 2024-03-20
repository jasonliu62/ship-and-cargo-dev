package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.Date;
import java.util.List;

@Repository
public interface ShipDao {

    int insertShip(Ship ship);

    Ship selectShipByImo(@Param("imo") Long imo);

    List<Ship> selectShipByDeadWeight(@Param("deadWeight") Float deadWeight);

    List<Ship> selectShipByShipType(@Param("shipType") String shipType);

    List<Ship> selectShipByShipName(@Param("shipName") String shipName);

    List<Ship> selectShipByShipConstant(@Param("shipConstant") Float shipConstant);

    List<Ship> selectShipByDeadDraft(@Param("deadDraft") Float deadDraft);

    List<Ship> selectShipByEmptyDraft(@Param("emptyDraft") Float emptyDraft);

    List<Ship> selectShipByBallastDraft(@Param("ballastDraft") Float ballastDraft);

    List<Ship> selectShipByShipCubic(@Param("shipCubic") Float shipCubic);

    List<Ship> selectShipByFlagState(@Param("flagState") String flagState);

    List<Ship> selectShipByShipTag(@Param("shipTag") String shipTag);

    List<Ship> selectShipByOwner(@Param("owner") String owner);

    List<Ship> selectShipByOperator(@Param("operator") String operator);

    List<Ship> selectShipByUploaderId(@Param("uploaderId") Long uploaderId);

    List<Ship> selectCargoByCreatedAt(@Param("createdAt") Date createdAt);

    List<Ship> selectCargoByModifiedAt(@Param("modifiedAt") Date modifiedAt);

    int deleteShipByImo(@Param("imo") Long imo);

    int updateShip(Ship ship);

    List<Ship> queryByCondition(
            @Param("imo") Long imo,
            @Param("deadWeight") Float deadWeight,
            @Param("shipType") String shipType,
            @Param("shipName") String shipName,
            @Param("shipConstant") Float shipConstant,
            @Param("deadDraft") Float deadDraft,
            @Param("emptyDraft") Float emptyDraft,
            @Param("ballastDraft") Float ballastDraft,
            @Param("shipCubic") Float shipCubic,
            @Param("flagState") String flagState,
            @Param("shipTag") String shipTag,
            @Param("owner") String owner,
            @Param("operator") String operator,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);
}
