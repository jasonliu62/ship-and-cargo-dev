package us.dev.shipandcargo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoInCargoNeedVo;
import us.dev.shipandcargo.Vo.CargoNeedVo;
import us.dev.shipandcargo.dao.CargoNeedDao;
import us.dev.shipandcargo.domain.CargoNeed;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;

@Service
public class CargoNeedService {

    @Autowired
    private CargoNeedDao cargoNeedDao;

    public int insertCargoNeed(CargoNeed cargoNeed, Long uploaderId) {
        Long id = cargoNeed.getCargoId();
        if (selectCargoNeedByIdAndUploader(id, uploaderId) != null) {
            throw new ApiException(ApiMessage.CARGO_NEED_EXISTED);
        }
        return cargoNeedDao.insertCargoNeed(cargoNeed);
    }

    public CargoNeed selectCargoNeedByIdAndUploader(Long id, Long uploaderId) {
        return cargoNeedDao.selectCargoNeedByCargoIdAndUploader(id, uploaderId);
    }

    public int updateCargoNeed(CargoNeed cargoNeed) {
        return cargoNeedDao.updateCargoNeed(cargoNeed);
    }

    public int deleteCargoNeedByIdAndUploader(Long cargoId, Long uploaderId) {
        return cargoNeedDao.deleteCargoNeedByIdAndUploader(cargoId, uploaderId);
    }

    public int deleteCargoNeedByUploaderId(Long uploaderId) {
        return cargoNeedDao.deleteCargoNeedByUploaderId(uploaderId);
    }

    public CargoInCargoNeedVo insertListHandler(Long uploaderId, int status, String unloadPort, String loadPort, Long cargoId, Long contractNumber) {

        CargoNeed cargoNeed = new CargoNeed();
        cargoNeed.setContractNumber(contractNumber);
        cargoNeed.setCargoId(cargoId);
        cargoNeed.setUploaderId(uploaderId);
        cargoNeed.setStatus(status);
        int res = insertCargoNeed(cargoNeed, uploaderId);

        CargoInCargoNeedVo cargoInCargoNeedVo = new CargoInCargoNeedVo();
        cargoInCargoNeedVo.setCargoId(cargoId);
        cargoInCargoNeedVo.setContractNumber(contractNumber);
        cargoInCargoNeedVo.setUnloadPortId(unloadPort);
        cargoInCargoNeedVo.setLoadPortId(loadPort);

        return cargoInCargoNeedVo;
    }

    public CargoNeedVo listCargoNeedHandler(Long cargoId, Long uploaderId) {
        CargoNeed cargoNeed = selectCargoNeedByIdAndUploader(cargoId, uploaderId);
        CargoNeedVo cargoNeedVo = new CargoNeedVo();
        cargoNeedVo.setLoadCost(cargoNeed.getLoadCost());
        cargoNeedVo.setUnloadCost(cargoNeed.getUnloadCost());
        cargoNeedVo.setOther(cargoNeed.getOther());
        cargoNeedVo.setLoadTime(cargoNeed.getLoadTime());
        cargoNeedVo.setAnchorTime(cargoNeed.getAnchorTime());
        cargoNeedVo.setIrregularstoppingTime(cargoNeed.getIrregularstoppingTime());
        cargoNeedVo.setNavigationTime(cargoNeed.getNavigationTime());
        cargoNeedVo.setVoyageTime(cargoNeed.getVoyageTime());
        return cargoNeedVo;
    }

}
