package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoVo;
import us.dev.shipandcargo.dao.CargoDao;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoDao cargoDao;

    public int insertCargo(Cargo cargo) {
        Long id = cargo.getCargoId();
        if (selectCargoById(id) != null) {
            throw new ApiException(ApiMessage.CARGO_EXISTED);
        }
        return cargoDao.insertCargo(cargo);
    }

    public Cargo selectCargoById(Long id) {
        return cargoDao.selectCargoById(id);
    }


    public List<Cargo> selectCargoByContractNumber(Long contractNumber) {
        return cargoDao.selectCargoByContractNumber(contractNumber);
    }

    public List<Cargo> selectCargoByContractType(String contractType) {
        return cargoDao.selectCargoByContractType(contractType);
    }

    public List<Cargo> selectCargoByVolume(Float cargoVolume) {
        return cargoDao.selectCargoByCargoVolume(cargoVolume);
    }

    public List<Cargo> selectCargoByType(String cargoType) {
        return cargoDao.selectCargoByCargoType(cargoType);
    }

    public List<Cargo> selectCargoByCargoFlowArea(String cargoflowArea) {
        return cargoDao.selectCargoByCargoFlowArea(cargoflowArea);
    }

    public List<Cargo> selectCargoByLoadPortId(String loadPortId) {
        return cargoDao.selectCargoByLoadPortId(loadPortId);
    }

    public List<Cargo> selectCargoByUnloadPortId(String unloadPortId) {
        return cargoDao.selectCargoByUnloadPortId(unloadPortId);
    }

    public List<Cargo> selectCargoByLayDay(Date layDay) {
        return cargoDao.selectCargoByLayDay(layDay);
    }

    public List<Cargo> selectCargoByStatus(int status) {
        return cargoDao.selectCargoByStatus(status);
    }

    public List<Cargo> selectCargoByDischargeDay(Date dischargeDay) {
        return cargoDao.selectCargoByDischargeDay(dischargeDay);
    }

    public List<Cargo> selectCargoByFreightRate(Float freightRate) {
        return cargoDao.selectCargoByFreightRate(freightRate);
    }

    public List<Cargo> selectCargoByVolumeRate(Float volumeRate) {
        return cargoDao.selectCargoByVolumeRate(volumeRate);
    }

    public List<Cargo> selectCargoByLoadportDepth(Float loadportDepth) {
        return cargoDao.selectCargoByLoadportDepth(loadportDepth);
    }

    public List<Cargo> selectCargoByUnloadportDepth(Float unloadportDepth) {
        return cargoDao.selectCargoByUnloadportDepth(unloadportDepth);
    }

    public List<Cargo> selectCargoByVoyageNumber(Long voyageNumber) {
        return cargoDao.selectCargoByVoyageNumber(voyageNumber);
    }

    public List<Cargo> selectCargoByVoyageVolume(Float voyageVolume) {
        return cargoDao.selectCargoByVoyageVolume(voyageVolume);
    }

    public List<Cargo> selectCargoByVoyagePeriod(Float voyagePeriod) {
        return cargoDao.selectCargoByVoyagePeriod(voyagePeriod);
    }

    public int deleteCargoById(Long id) {
        return cargoDao.deleteCargoById(id);
    }

    public int updateCargo(Cargo cargo) {
        return cargoDao.updateCargo(cargo);
    }


    public PageData<CargoVo> listCargo(PaginationProps paging, Long contractNumber,
                                       String contractType,
                                       Long cargoId,
                                       Float cargoVolume,
                                       String cargoType,
                                       String cargoflowArea,
                                       String loadPortId,
                                       String unloadPortId,
                                       Date layDay,
                                       Date dischargeDay,
                                       Float freightRate,
                                       Float volumeRate,
                                       Float loadportDepth,
                                       Float unloadportDepth,
                                       Long voyageNumber,
                                       Float voyageVolume,
                                       Float voyagePeriod,
                                       int status) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<Cargo> cargos =  cargoDao.queryByCondition(contractNumber,
                contractType, cargoId, cargoVolume, cargoType,
                cargoflowArea, loadPortId,
                unloadPortId, layDay,
                dischargeDay, freightRate,
                volumeRate, loadportDepth,
                unloadportDepth, voyageNumber,
                voyageVolume, voyagePeriod,
                status, paging.getSortByToList());
        if (cargos.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<CargoVo>());
        }

        List<CargoVo> cargoVoList = new ArrayList<CargoVo>();
        for (Cargo cargo: cargos) {
            CargoVo cargoVo = new CargoVo();
            ObjectUtil.objectCopy(cargo, cargoVo);
            cargoVoList.add(cargoVo);
        }

        return PageDataUtil.convertToPageData(cargos, cargoVoList);
    }

}
