package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoVo;
import us.dev.shipandcargo.Vo.ShipVo;
import us.dev.shipandcargo.dao.ShipDao;
import us.dev.shipandcargo.domain.Ship;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    @Autowired
    private ShipDao shipDao;

    public int insertShip(Ship ship){
        Long imo = ship.getImo();
        if (selectShipByImo(imo) != null) {
            throw new ApiException(ApiMessage.SHIP_EXISTED);
        }
        return shipDao.insertShip(ship);
    }

    public Ship selectShipByImo(Long imo) { return shipDao.selectShipByImo(imo); }

    public List<Ship> selectShipByDeadWeight(Float deadWeight) {
        return shipDao.selectShipByDeadWeight(deadWeight);
    }

    public List<Ship> selectShipByShipType(String shipType) {
        return shipDao.selectShipByShipType(shipType);
    }

    public List<Ship> selectShipByShipName(String shipName) {
        return shipDao.selectShipByShipName(shipName);
    }

    public List<Ship> selectShipByShipConstant(Float shipConstant) {
        return shipDao.selectShipByShipConstant(shipConstant);
    }

    public List<Ship> selectShipByDeadDraft(Float deadDraft) {
        return shipDao.selectShipByDeadDraft(deadDraft);
    }

    public List<Ship> selectShipByEmptyDraft(Float emptyDraft) {
        return shipDao.selectShipByEmptyDraft(emptyDraft);
    }

    public List<Ship> selectShipByBallastDraft(Float ballastDraft) {
        return shipDao.selectShipByBallastDraft(ballastDraft);
    }

    public List<Ship> selectShipByShipCubic(Float shipCubic) {
        return shipDao.selectShipByShipCubic(shipCubic);
    }

    public List<Ship> selectShipByFlagState(String flagState) {
        return shipDao.selectShipByFlagState(flagState);
    }

    public List<Ship> selectShipByShipTag(String shipTag) {
        return shipDao.selectShipByShipTag(shipTag);
    }

    public List<Ship> selectShipByOwner(String owner) {
        return shipDao.selectShipByOwner(owner);
    }

    public List<Ship> selectShipByOperator(String operator) {
        return shipDao.selectShipByOperator(operator);
    }

    public List<Ship> selectShipByUploaderId(Long uploaderId) {
        return shipDao.selectShipByUploaderId(uploaderId);
    }

    public int deleteShipByImo(Long imo) { return shipDao.deleteShipByImo(imo);}

    public int updateShip(Ship ship) { return shipDao.updateShip(ship);}

    public PageData<ShipVo> listShip(PaginationProps paging,
                                     Long imo,
                                     Float deadWeight,
                                     String shipType,
                                     String shipName,
                                     Float shipConstant,
                                     Float deadDraft,
                                     Float emptyDraft,
                                     Float ballastDraft,
                                     Float shipCubic,
                                     String flagState,
                                     String shipTag,
                                     String owner,
                                     String operator,
                                     Long uploaderId
                                     ){
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<Ship> ships = shipDao.queryByCondition(imo, deadWeight, shipType, shipName, shipConstant,
                deadDraft, emptyDraft, ballastDraft, shipCubic, flagState,shipTag, owner, operator, uploaderId,
                paging.getSortByToList());
        if (ships.size() == 0){
            return PageDataUtil.convertToPageData(new ArrayList<ShipVo>());
        }

        List<ShipVo> shipVoList = new ArrayList<ShipVo>();
        for (Ship ship:ships){
            ShipVo shipVo = new ShipVo();
            ObjectUtil.objectCopy(ship, shipVo);
            shipVoList.add(shipVo);
        }

        return PageDataUtil.convertToPageData(ships, shipVoList);

    }



}
