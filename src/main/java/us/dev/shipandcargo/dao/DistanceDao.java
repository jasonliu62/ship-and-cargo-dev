package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Distance;

import java.util.List;

@Repository
public interface DistanceDao {

    int insertDistance(Distance distance);

    List<Distance> selectDistanceByShipType(@Param("shipType") String shipType);

    List<Distance> selectAllDistances();

    List<Distance> selectDistanceByStartAndEndPort(@Param("startPortId") String startPortId, @Param("endPortId") String endPortId);

    int updateDistance(Distance distance);

    int deleteDistanceById(@Param("id") Long id);
}
