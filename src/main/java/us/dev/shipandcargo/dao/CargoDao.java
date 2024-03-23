package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CargoDao {

    int insertCargo(Cargo record);
    Cargo selectCargoById(@Param("cargoId") Long cargoId);
    List<Cargo> selectCargoByContractNumber(@Param("contractNumber") Long contractNumber);

    List<Cargo> selectCargoByContractType(@Param("contractType") String contractType);

    List<Cargo> selectCargoByCargoVolume(@Param("cargoVolume") Float cargoVolume);

    List<Cargo> selectCargoByCargoType(@Param("cargoType") String cargoType);

    List<Cargo> selectCargoByCargoFlowArea(@Param("cargoflowArea") String cargoflowArea);

    List<Cargo> selectCargoByLoadPortId(@Param("loadPortId") String loadPortId);

    List<Cargo> selectCargoByUnloadPortId(@Param("unloadPortId") String unloadPortId);

    List<Cargo> selectCargoByLayDay(@Param("layDay") LocalDateTime layDay);

    List<Cargo> selectCargoByDischargeDay(@Param("dischargeDay") LocalDateTime dischargeDay);

    List<Cargo> selectCargoByFreightRate(@Param("freightRate") Float freightRate);

    List<Cargo> selectCargoByVolumeRate(@Param("volumeRate") Float volumeRate);

    List<Cargo> selectCargoByLoadportDepth(@Param("loadportDepth") Float loadportDepth);

    List<Cargo> selectCargoByUnloadportDepth(@Param("unloadportDepth") Float unloadportDepth);

    List<Cargo> selectCargoByVoyageNumber(@Param("voyageNumber") Long voyageNumber);

    List<Cargo> selectCargoByVoyageVolume(@Param("voyageVolume") Float voyageVolume);

    List<Cargo> selectCargoByVoyagePeriod(@Param("voyagePeriod") Float voyagePeriod);

    List<Cargo> selectCargoByStatus(@Param("status") int status);

    List<Cargo> selectCargoByCreatedAt(@Param("createdAt") Date createdAt);

    List<Cargo> selectCargoByModifiedAt(@Param("modifiedAt") Date modifiedAt);
    int deleteCargoById(@Param("cargoId") Long cargoId);
    int updateCargo(Cargo record);
    List<Cargo> queryByCondition(
            @Param("contractNumber") Long contractNumber,
            @Param("contractType") String contractType,
            @Param("cargoId") Long cargoId,
            @Param("cargoVolume") Float cargoVolume,
            @Param("cargoType") String cargoType,
            @Param("cargoflowArea") String cargoflowArea,
            @Param("loadPortId") String loadPortId,
            @Param("unloadPortId") String unloadPortId,
            @Param("layDay") LocalDateTime layDay,
            @Param("dischargeDay") LocalDateTime dischargeDay,
            @Param("freightRate") Float freightRate,
            @Param("volumeRate") Float volumeRate,
            @Param("loadportDepth") Float loadportDepth,
            @Param("unloadportDepth") Float unloadportDepth,
            @Param("voyageNumber") Long voyageNumber,
            @Param("voyageVolume") Float voyageVolume,
            @Param("voyagePeriod") Float voyagePeriod,
            @Param("status") int status,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);

}
