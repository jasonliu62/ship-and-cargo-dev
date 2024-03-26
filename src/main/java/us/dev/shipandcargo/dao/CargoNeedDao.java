package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.CargoNeed;

import java.util.List;

@Repository
public interface CargoNeedDao {

    int insertCargoNeed(CargoNeed record);

    CargoNeed selectCargoNeedByCargoIdAndUploader(@Param("cargoId") Long cargoId, @Param("uploaderId") Long uploaderId);

    CargoNeed selectCargoNeedByContractNumber(@Param("contractNumber") Long contractNumber);

    CargoNeed selectCargoNeedByCargoId(@Param("cargoId") Long cargoId);

    List<CargoNeed> selectAllCargoNeeds();

    int updateCargoNeed(CargoNeed record);

    int deleteCargoNeedByIdAndUploader(@Param("id") Long id, @Param("uploaderId") Long uploaderId);

    int deleteCargoNeedByContractNumber(@Param("contractNumber") Long contractNumber);

    List<CargoNeed> selectCargoNeedsByStatus(@Param("status") int status);

    List<CargoNeed> selectAllCargoNeedsByUploaderSortedByLayDay(@Param("uploaderId") Long uploaderId);

    int deleteCargoNeedByUploaderId(@Param("uploaderId") Long uploaderId);

    int deleteCargoNeedByContractNumberAndCargoId(@Param("contractNumber") Long contractNumber, @Param("cargoId") Long cargoId);

}

