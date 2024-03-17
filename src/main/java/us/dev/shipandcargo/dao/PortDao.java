package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.Port;

@Repository
public interface PortDao {
    int insertPort(Port port);
    Port selectPortById(@Param("portId") Long portId);
    Port selectPortByCName(@Param("nameCHN") String nameCHN);
    Port selectPortByEName(@Param("nameENG") String nameENG);
    String selectCNameById(@Param("portId") Long portId);
    String selectENameById(@Param("portId") Long portId);
    Port selectPortByLatitude(@Param("latitude") Long latitude);
    Port selectPortByLongitude(@Param("longitude") Long longitude);
    Port selectPortByMinDraft(@Param("minDraft") Long minDraft);
    int updatePort(Port port);
    int deletePortById(@Param("portId") Long portId);
}
