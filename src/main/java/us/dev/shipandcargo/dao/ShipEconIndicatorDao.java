package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.ShipEconIndicator;

import java.util.List;

@Mapper
public interface ShipEconIndicatorDao {

    int insertShipEconIndicator(ShipEconIndicator shipEconIndicator);

    ShipEconIndicator selectShipEconIndicatorByImoAndSelector(@Param("imo") Long imo, @Param("selectorId") Long selectorId);

    List<ShipEconIndicator> selectAllShipEconIndicatorsBySelector(@Param("selectorId") Long selectorId);

    int updateShipEconIndicator(ShipEconIndicator shipEconIndicator);

    int deleteShipEconIndicatorByImoAndSelector(@Param("imo") Long imo, @Param("selectorId") Long selectorId);

    int deleteAllBySelector(@Param("selectorId") Long selectorId);

    List<Ship> selectShipsBySelectorId(@Param("selectorId") Long selectorId);
}
