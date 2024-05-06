package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Port;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.util.List;

@Repository
public interface PortDao {
    int insertPort(Port port);
    Port selectPortByPortId(@Param("portId") String portId);
    List<Port> selectPortByCName(@Param("nameCHN") String nameCHN);
    List<Port> selectPortByEName(@Param("nameENG") String nameENG);
    List<Port> selectPortByLatitude(@Param("latitude") String latitude);
    List<Port> selectPortByLongitude(@Param("longitude") String longitude);
    List<Port> selectPortByMinDraft(@Param("minDraft") Float minDraft);
    int updatePort(Port port);
    int deletePortByPortId(@Param("portId") Long portId);

    List<Port> queryByCondition(
            @Param("portId") String portId,
            @Param("nameCHN") String nameCHN,
            @Param("nameENG") String nameENG,
            @Param("latitude") String latitude,
            @Param("longitude") String longitude,
            @Param("minDraft") Float minDraft,
            @Param("sortByList") List<PaginationOrderQuery> sortBy);
}
