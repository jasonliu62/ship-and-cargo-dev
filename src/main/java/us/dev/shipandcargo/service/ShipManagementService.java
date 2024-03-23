package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.ShipManagementVo;
import us.dev.shipandcargo.dao.ShipManagementDao;
import us.dev.shipandcargo.domain.ShipContractManagement;
import us.dev.shipandcargo.domain.ShipManagement;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShipManagementService {

    @Autowired
    private ShipManagementDao shipManagementDao;

    public int insertShipManagement(ShipManagement shipManagement) {
        Long imo = shipManagement.getImo();
        if (selectShipManagementByImo(imo) != null) {
            throw new ApiException(ApiMessage.SHIP_MANAGEMENT_EXISTED);
        }
        return shipManagementDao.insertShipManagement(shipManagement);
    }

    public ShipManagement selectShipManagementByImo(Long imo) {
        return shipManagementDao.selectShipManagementByImo(imo);
    }

    public List<ShipManagement> selectShipManagementByShipOperatingDay(Float shipOperatingDay) {
        return shipManagementDao.selectShipManagementByShipOperatingDay(shipOperatingDay);
    }

    public List<ShipManagement> selectShipManagementByShipTotalTime(Float shipTotalTime) {
        return shipManagementDao.selectShipManagementByShipTotalTime(shipTotalTime);
    }

    public List<ShipManagement> selectShipManagementByDailyCapitalCost(Float dailyCapitalCost) {
        return shipManagementDao.selectShipManagementByDailyCapitalCost(dailyCapitalCost);
    }

    public List<ShipManagement> selectShipManagementByDailyOperatingCost(Float dailyOperatingCost) {
        return shipManagementDao.selectShipManagementByDailyOperatingCost(dailyOperatingCost);
    }

    public List<ShipManagement> selectShipManagementByAcceptableCargoType(String acceptableCargoType) {
        return shipManagementDao.selectShipManagementByAcceptableCargoType(acceptableCargoType);
    }

    public List<ShipManagement> selectShipManagementByOwner(String owner) {
        return shipManagementDao.selectShipManagementByOwner(owner);
    }

    public List<ShipManagement> selectShipManagementByOperator(String operator) {
        return shipManagementDao.selectShipManagementByOperator(operator);
    }

    public List<ShipManagement> selectShipManagementByNavigationArea(String navigationArea) {
        return shipManagementDao.selectShipManagementByNavigationArea(navigationArea);
    }

    public List<ShipManagement> selectShipManagementByShipDepartment(String shipDepartment) {
        return shipManagementDao.selectShipManagementByShipDepartment(shipDepartment);
    }

    public List<ShipManagement> selectShipManagementByShipEmptyPort(String shipEmptyPort) {
        return shipManagementDao.selectShipManagementByShipEmptyPort(shipEmptyPort);
    }

    public List<ShipManagement> selectShipManagementByShipEmptyTime(LocalDateTime shipEmptyTime) {
        return shipManagementDao.selectShipManagementByShipEmptyTime(shipEmptyTime);
    }

    public List<ShipManagement> selectShipManagementByUploaderId(Long uploaderId) {
        return shipManagementDao.selectShipManagementByUploaderId(uploaderId);
    }

    public int deleteShipManagementByImo(Long imo) {
        return shipManagementDao.deleteShipManagementByImo(imo);
    }

    public PageData<ShipManagementVo> listShipManagement(PaginationProps paging,
                                                         Long imo,
                                                         Float shipOperatingDay,
                                                         Float shipTotalTime,
                                                         Float dailyCapitalCost,
                                                         Float dailyOperatingCost,
                                                         String acceptableCargoType,
                                                         String owner,
                                                         String operator,
                                                         String navigationArea,
                                                         String shipDepartment,
                                                         String shipEmptyPort,
                                                         LocalDateTime shipEmptyTime,
                                                         Long uploaderId
    ) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<ShipManagement> shipManagements = shipManagementDao.queryByCondition(imo, shipOperatingDay, shipTotalTime,
                dailyCapitalCost, dailyOperatingCost, acceptableCargoType, owner, operator, navigationArea, shipDepartment,
                shipEmptyPort, shipEmptyTime, uploaderId, paging.getSortByToList());
        if (shipManagements.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<ShipManagementVo>());
        }
        List<ShipManagementVo> shipManagementVoList = new ArrayList<ShipManagementVo>();
        for (ShipManagement shipManagement : shipManagements) {
            ShipManagementVo shipManagementVo = new ShipManagementVo();
            ObjectUtil.objectCopy(shipManagement, shipManagementVo);
            shipManagementVoList.add(shipManagementVo);
        }
        return PageDataUtil.convertToPageData(shipManagements, shipManagementVoList);

    }

}
