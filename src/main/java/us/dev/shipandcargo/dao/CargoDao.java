package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.enums.CargoDisplayEnum;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

@Repository
public interface CargoDao {

    int insertCargo(Cargo record);
    Cargo selectCargoById(@Param("cargoId") Long cargoId);
    List<Cargo> selectCargoByVolume(@Param("cargoVolume") Long cargoVolume);
    List<Cargo> selectCargoByType(@Param("cargoType") String cargoType);
    List<Cargo> selectCargoByLoadPort(@Param("loadPortId") Long loadPortId);
    List<Cargo> selectCargoByUnloadPort(@Param("unloadPortId") Long unloadPortId);
    List<Cargo> selectCargoByLayday(@Param("layDay") Long layDay);
    List<Cargo> selectCargoByCompany(@Param("company") String company);
    int deleteCargoById(@Param("cargoId") Long cargoId);
    int updateCargo(Cargo record);
    List<Cargo> queryByCondition(@Param("cargoId") Long cargoId,
                                 @Param("cargoVolume") Long cargoVolume,
                                 @Param("cargoType") String cargoType,
                                 @Param("loadPortId") Long loadPortId,
                                 @Param("unloadPortId") Long unloadPortId,
                                 @Param("layDay") Long layDay,
                                 @Param("company") String company,
                                 @Param("status") CargoDisplayEnum status,
                                 @Param("sortByList") List<PaginationOrderQuery> sortBy);

}
