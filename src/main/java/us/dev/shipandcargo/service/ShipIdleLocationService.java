package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.ShipIdleLocationVo;
import us.dev.shipandcargo.dao.ShipIdleLocationDao;
import us.dev.shipandcargo.domain.ShipIdleLocation;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipIdleLocationService {

    @Autowired
    private ShipIdleLocationDao shipIdleLocationDao;

    public int insertShipIdleLocation(ShipIdleLocation location) {
        Long imo = location.getImo();
        if (selectShipIdleLocationByImo(imo) != null) {
            throw new ApiException(ApiMessage.SHIP_IDLE_LOCATION_EXISTED);
        }
        return shipIdleLocationDao.insertShipIdleLocation(location);
    }

    public ShipIdleLocation selectShipIdleLocationByImo(Long imo) {
        return shipIdleLocationDao.selectShipIdleLocationByImo(imo);
    }

    public List<ShipIdleLocation> selectShipIdleLocationByPortId(String portId) {
        return shipIdleLocationDao.selectShipIdleLocationByPortId(portId);
    }

    public List<ShipIdleLocation> selectShipIdleLocationByUploaderId(Long uploaderId) {
        return shipIdleLocationDao.selectShipIdleLocationByUploaderId(uploaderId);
    }

    public int deleteShipIdleLocationByImo(Long imo) {
        return shipIdleLocationDao.deleteShipIdleLocationByImo(imo);
    }

    public PageData<ShipIdleLocationVo> listShipIdleLocations(PaginationProps paging, Long imo, String portId,
                                                              Long uploaderId) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<ShipIdleLocation> locations = shipIdleLocationDao.queryByCondition(imo, portId,
                uploaderId, paging.getSortByToList());
        if (locations.isEmpty()) {
            return PageDataUtil.convertToPageData(new ArrayList<ShipIdleLocationVo>());
        }

        List<ShipIdleLocationVo> locationVoList = new ArrayList<>();
        for (ShipIdleLocation location : locations) {
            ShipIdleLocationVo locationVo = new ShipIdleLocationVo();
            ObjectUtil.objectCopy(location, locationVo);
            locationVoList.add(locationVo);
        }

        return PageDataUtil.convertToPageData(locations, locationVoList);
    }

}
