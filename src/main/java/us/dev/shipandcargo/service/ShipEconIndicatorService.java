package us.dev.shipandcargo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoInCargoNeedVo;
import us.dev.shipandcargo.Vo.ShipEconIndicatorVo;
import us.dev.shipandcargo.Vo.ShipInShipEconVo;
import us.dev.shipandcargo.dao.ShipEconIndicatorDao;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.domain.ShipEconIndicator;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipEconIndicatorService {

    @Autowired
    private ShipEconIndicatorDao shipEconIndicatorDao;

    public int insertShipEconIndicator(ShipEconIndicator shipEconIndicator) {
        return shipEconIndicatorDao.insertShipEconIndicator(shipEconIndicator);
    }

    public ShipEconIndicator selectShipEconIndicatorByImoAndSelector(Long imo, Long selectorId) {
        return shipEconIndicatorDao.selectShipEconIndicatorByImoAndSelector(imo, selectorId);
    }

    public List<ShipEconIndicator> selectAllShipEconIndicatorsBySelector(Long selectorId) {
        return shipEconIndicatorDao.selectAllShipEconIndicatorsBySelector(selectorId);
    }

    public int updateShipEconIndicator(ShipEconIndicator shipEconIndicator) {
        return shipEconIndicatorDao.updateShipEconIndicator(shipEconIndicator);
    }

    public int deleteShipEconIndicatorByImoAndSelector(Long imo, Long selectorId) {
        return shipEconIndicatorDao.deleteShipEconIndicatorByImoAndSelector(imo, selectorId);
    }

    public int deleteAllBySelector(Long selectorId) {
        return shipEconIndicatorDao.deleteAllBySelector(selectorId);
    }

    public List<Ship> selectShipsBySelectorId(Long selectorId) {
        return shipEconIndicatorDao.selectShipsBySelectorId(selectorId);
    }


    public ShipInShipEconVo listInsertHandler(Ship ship, Long imo, Long uploaderId) {
        ShipEconIndicator shipEconIndicator = new ShipEconIndicator();
        shipEconIndicator.setImo(imo);
        shipEconIndicator.setSelectorId(uploaderId);
        int res = insertShipEconIndicator(shipEconIndicator);

        ShipInShipEconVo shipInShipEconVo = new ShipInShipEconVo();
        shipInShipEconVo.setImo(imo);
        shipInShipEconVo.setShipType(ship.getShipType());
        shipInShipEconVo.setShipName(ship.getShipName());
        return shipInShipEconVo;

    }

    public PageData<ShipInShipEconVo> listShipHandler(List<Ship> shipList) {
        if (shipList.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<ShipInShipEconVo>());
        }
        List<ShipInShipEconVo> shipInShipEconVos = new ArrayList<ShipInShipEconVo>();
        for (Ship ship: shipList) {
            ShipInShipEconVo econVo = new ShipInShipEconVo();
            ObjectUtil.objectCopy(ship, econVo);
            shipInShipEconVos.add(econVo);
        }
        return PageDataUtil.convertToPageData(shipList, shipInShipEconVos);
    }

    public ShipEconIndicatorVo listHandler(Long imo, Long uploaderId) {
        ShipEconIndicator shipEconIndicator = selectShipEconIndicatorByImoAndSelector(imo, uploaderId);
        ShipEconIndicatorVo shipEconIndicatorVo = new ShipEconIndicatorVo();
        shipEconIndicatorVo.setCharterTime(shipEconIndicator.getCharterTime());
        shipEconIndicatorVo.setDailyRent(shipEconIndicatorVo.getDailyRent());
        shipEconIndicatorVo.setChartType(shipEconIndicatorVo.getChartType());
        shipEconIndicatorVo.setOilPrice(shipEconIndicatorVo.getOilPrice());
        shipEconIndicatorVo.setOilConsumption(shipEconIndicatorVo.getOilConsumption());
        return shipEconIndicatorVo;
    }
}
