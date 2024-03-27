package us.dev.shipandcargo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoInCargoNeedVo;
import us.dev.shipandcargo.Vo.CargoNeedVo;
import us.dev.shipandcargo.Vo.CargoVo;
import us.dev.shipandcargo.dao.CargoNeedDao;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.domain.CargoNeed;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

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
        cargoNeedVo.setUnloadTime(cargoNeed.getUnloadTime());
        cargoNeedVo.setAnchorTime(cargoNeed.getAnchorTime());
        cargoNeedVo.setIrregularstoppingTime(cargoNeed.getIrregularstoppingTime());
        cargoNeedVo.setNavigationTime(cargoNeed.getNavigationTime());
        cargoNeedVo.setVoyageTime(cargoNeed.getVoyageTime());
        return cargoNeedVo;
    }

    public List<CargoNeed> selectAllCargoNeedsByUploaderSortedByLayDay(Long uploaderId) {
        return cargoNeedDao.selectAllCargoNeedsByUploaderSortedByLayDay(uploaderId);
    }

    public List<Cargo> selectCargosByUploaderId(Long uploaderId) {
        return cargoNeedDao.selectCargosByUploaderId(uploaderId);
    }

    public PageData<CargoInCargoNeedVo> listCargoNeedsHandler(List<Cargo> cargos) {
        if (cargos.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<CargoInCargoNeedVo>());
        }
        List<CargoInCargoNeedVo> needVoList = new ArrayList<CargoInCargoNeedVo>();
        for (Cargo cargo: cargos) {
            CargoInCargoNeedVo needVo = new CargoInCargoNeedVo();
            ObjectUtil.objectCopy(cargo, needVo);
            needVoList.add(needVo);
        }
        return PageDataUtil.convertToPageData(cargos, needVoList);
    }

}
