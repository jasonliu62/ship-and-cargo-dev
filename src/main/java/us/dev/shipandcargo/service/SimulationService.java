package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.ShipVo;
import us.dev.shipandcargo.Vo.SimulationHistoryVo;
import us.dev.shipandcargo.dao.SimulationHistoryDao;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.SimulationHistory;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {

    @Autowired
    private SimulationHistoryDao simulationHistoryDao;

    public int insertSimulationHistory(List<Ship> shipList, List<Cargo> cargoList,
                                       LocalDateTime startDay, LocalDateTime endDay, Long uploaderId) {
        Long groupId = getGroupId() + 1;
        SimulationHistory simulationHistory = new SimulationHistory();
        simulationHistory.setGroupId(groupId);
        simulationHistory.setUploaderId(uploaderId);
        simulationHistory.setImoCombo(buildShipCombo(shipList));
        simulationHistory.setCargoIdCombo(buildCargoCombo(cargoList));
        simulationHistory.setStartDay(startDay);
        simulationHistory.setEndDay(endDay);
        return simulationHistoryDao.insertSimulationHistory(simulationHistory);
    }

    public PageData<SimulationHistoryVo> listSimulationHistory(PaginationProps paging,
                                                               Long uploaderId) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        Long groupId = null;
        List<SimulationHistory> simulationHistories = simulationHistoryDao.queryByCondition(groupId, uploaderId, paging.getSortByToList());
        if (simulationHistories.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<SimulationHistoryVo>());
        }

        List<SimulationHistoryVo> simulationHistoryVoList = new ArrayList<>();
        for (SimulationHistory simulationHistory: simulationHistories) {
            SimulationHistoryVo simulationHistoryVo = new SimulationHistoryVo();
            ObjectUtil.objectCopy(simulationHistory, simulationHistoryVo);
            simulationHistoryVoList.add(simulationHistoryVo);
        }

        return PageDataUtil.convertToPageData(simulationHistories, simulationHistoryVoList);
    }


    public Long getGroupId() {
        return simulationHistoryDao.countSimulationHistory();
    }

    private String buildShipCombo(List<Ship> shipList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shipList.size(); i++) {
            Ship ship = shipList.get(i);
            Long imo = ship.getImo(); // 假设 Ship 类中有 getImo 方法获取 IMO 号
            sb.append(imo.toString());
            if (i < shipList.size() - 1) { // 在最后一个元素后不添加逗号
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private String buildCargoCombo(List<Cargo> cargoList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cargoList.size(); i++) {
            Cargo cargo = cargoList.get(i);
            Long id = cargo.getCargoId(); // 假设 Ship 类中有 getImo 方法获取 IMO 号
            sb.append(id.toString());
            if (i < cargoList.size() - 1) { // 在最后一个元素后不添加逗号
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public int deleteSimulationHistory(Long groupId, Long uploaderId) {
        return simulationHistoryDao.deleteSimulationHistoryByGroupIdAndUploaderId(groupId, uploaderId);
    }
}
