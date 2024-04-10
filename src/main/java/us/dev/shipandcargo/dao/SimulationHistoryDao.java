package us.dev.shipandcargo.dao;


import org.apache.ibatis.annotations.Param;
import us.dev.shipandcargo.domain.SimulationHistory;
import us.dev.shipandcargo.request.paging.PaginationOrderQuery;

import java.time.LocalDateTime;
import java.util.List;

public interface SimulationHistoryDao {

    int insertSimulationHistory(SimulationHistory simulationHistory);

    int deleteSimulationHistoryByGroupIdAndUploaderId(@Param("groupId") Long groupId, @Param("uploaderId") Long uploaderId);

    Long countSimulationHistory();

    List<SimulationHistory> queryByCondition(
            @Param("groupId") Long groupId,
            @Param("uploaderId") Long uploaderId,
            @Param("sortByList") List<PaginationOrderQuery> sortByList
    );

}
