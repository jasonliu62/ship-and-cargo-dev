package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Distance;

import java.util.List;

@Repository
public interface DistanceDao {

    int insertDistance(Distance distance);

    Distance selectDistanceById(@Param("id") Long id);

    List<Distance> selectAllDistances();

    Distance selectDistanceByStartAndEndPort(@Param("startPortId") Long startPortId, @Param("endPortId") Long endPortId);

    int updateDistance(Distance distance);

    int deleteDistanceById(@Param("id") Long id);
}
