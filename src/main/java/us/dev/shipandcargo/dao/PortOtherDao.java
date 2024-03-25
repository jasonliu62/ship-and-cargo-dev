package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.PortOther;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.Date;
import java.util.List;

@Repository
public interface PortOtherDao {

    int insertPortOther(PortOther record);

    PortOther selectPortOtherById(@Param("portId") Long portId);

    List<PortOther> selectPortOtherByDistance(@Param("distance") Float distance);

    List<PortOther> selectPortOtherByOilPrice(@Param("oilPrice") Float oilPrice);

    List<PortOther> selectPortOtherByAvgTimeStay(@Param("avgTimeStay") Float avgTimeStay);

    List<PortOther> selectPortOtherByLoadTime(@Param("loadTime") Float loadTime);

    List<PortOther> selectPortOtherByLoadEfficiency(@Param("loadEfficiency") Float loadEfficiency);

    List<PortOther> selectPortOtherByUnloadTime(@Param("unloadTime") Float unloadTime);

    List<PortOther> selectPortOtherByUnloadEfficiency(@Param("unloadEfficiency") Float unloadEfficiency);

    List<PortOther> selectPortOtherByPortFee(@Param("portFee") Float portFee);

    List<PortOther> selectPortOtherByAvgAnchorTime(@Param("avgAnchorTime") Float avgAnchorTime);

    int deletePortOtherById(@Param("portId") Long portId);

    int updatePortOther(PortOther record);

    List<PortOther> selectPortOtherByCreatedAt(@Param("createdAt") Date createdAt);

    List<PortOther> selectPortOtherByModifiedAt(@Param("modifiedAt") Date modifiedAt);

    List<PortOther> queryByCondition(
            @Param("portId") Long portId,
            @Param("distance") Float distance,
            @Param("oilPrice") Float oilPrice,
            @Param("avgTimeStay") Float avgTimeStay,
            @Param("loadTime") Float loadTime,
            @Param("loadEfficiency") Float loadEfficiency,
            @Param("unloadTime") Float unloadTime,
            @Param("unloadEfficiency") Float unloadEfficiency,
            @Param("portFee") Float portFee,
            @Param("avgAnchorTime") Float avgAnchorTime,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);
}
