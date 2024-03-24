package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.ShipActiveMissionLocationVo;
import us.dev.shipandcargo.dao.ShipActiveMissionLocationDao;
import us.dev.shipandcargo.domain.ShipActiveMissionLocation;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipActiveMissionLocationService {

    @Autowired
    private ShipActiveMissionLocationDao shipActiveMissionLocationDao;

    public int insertShipActiveMissionLocation(ShipActiveMissionLocation location) {
        Long imo = location.getImo();
        if (selectShipActiveMissionLocationByImo(imo) != null) {
            throw new ApiException(ApiMessage.SHIP_MISSION_LOCATION_EXISTED);
        }
        return shipActiveMissionLocationDao.insertShipActiveMissionLocation(location);
    }

    public ShipActiveMissionLocation selectShipActiveMissionLocationByImo(Long imo) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByImo(imo);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByLatitude(Float latitude) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByLatitude(latitude);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByLongitude(Float longitude) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByLongitude(longitude);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByPostPortId(String postPortId) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByPostPortId(postPortId);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByNextPortId(String nextPortId) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByNextPortId(nextPortId);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationBySpeed(Float speed) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationBySpeed(speed);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByDepartureDistance(Float departureDistance) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByDepartureDistance(departureDistance);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByArrivalDistance(Float arrivalDistance) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByArrivalDistance(arrivalDistance);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByCurrentTime(LocalDateTime currentTime) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByCurrentTime(currentTime);
    }

    public List<ShipActiveMissionLocation> selectShipActiveMissionLocationByUploaderId(Long uploaderId) {
        return shipActiveMissionLocationDao.selectShipActiveMissionLocationByUploaderId(uploaderId);
    }

    public int deleteShipActiveMissionLocationByImo(Long imo) {
        return shipActiveMissionLocationDao.deleteShipActiveMissionLocationByImo(imo);
    }

    public int updateShipActiveMissionLocation(ShipActiveMissionLocation location) {
        return shipActiveMissionLocationDao.updateShipActiveMissionLocation(location);
    }

    public PageData<ShipActiveMissionLocationVo> listShipActiveMissionLocations(PaginationProps paging, Long imo,
                                                                                Float latitude, Float longitude,
                                                                                String postPortId, String nextPortId,
                                                                                Float speed, Float departureDistance,
                                                                                Float arrivalDistance, LocalDateTime currentTime,
                                                                                Long uploaderId) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<ShipActiveMissionLocation> locations = shipActiveMissionLocationDao.queryByCondition(imo, latitude, longitude,
                postPortId, nextPortId,
                speed, departureDistance,
                arrivalDistance, currentTime,
                uploaderId, paging.getSortByToList());
        if (locations.isEmpty()) {
            return PageDataUtil.convertToPageData(new ArrayList<ShipActiveMissionLocationVo>());
        }

        List<ShipActiveMissionLocationVo> locationVoList = new ArrayList<>();
        for (ShipActiveMissionLocation location : locations) {
            ShipActiveMissionLocationVo locationVo = new ShipActiveMissionLocationVo();
            ObjectUtil.objectCopy(location, locationVo);
            locationVoList.add(locationVo);
        }

        return PageDataUtil.convertToPageData(locations, locationVoList);
    }
}
