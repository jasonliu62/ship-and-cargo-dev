package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.CargoVo;
import us.dev.shipandcargo.dao.CargoDao;
import us.dev.shipandcargo.domain.Cargo;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoDao cargoDao;

    public int insertCargo(Cargo cargo) {
        return cargoDao.insertCargo(cargo);
    }

    public Cargo selectCargoById(Long id) {
        return cargoDao.selectCargoById(id);
    }

    public List<Cargo> selectCargoByVolume(Long cargoVolume) {
        return cargoDao.selectCargoByVolume(cargoVolume);
    }

    public List<Cargo> selectCargoByType(String cargoType) {
        return cargoDao.selectCargoByType(cargoType);
    }

    public List<Cargo> selectCargoByLoadPort(Long loadPort) {
        return cargoDao.selectCargoByLoadPort(loadPort);
    }

    public List<Cargo> selectCargoByUnloadPort(Long unloadPort) {
        return cargoDao.selectCargoByUnloadPort(unloadPort);
    }

    public List<Cargo> selectCargoByLayday(Long cargoLayday) {
        return cargoDao.selectCargoByLayday(cargoLayday);
    }

    public List<Cargo> selectCargoByCompany(String company) {
        return cargoDao.selectCargoByCompany(company);
    }

    public PageData<CargoVo> listCargo(PaginationProps paging, Long cargoId, Long cargoVolume, String cargoType, Long loadPortId, Long unloadPortId,
                                     Long layDay, String company) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<Cargo> cargos =  cargoDao.queryByCondition(cargoId, cargoVolume, cargoType, loadPortId, unloadPortId,
                layDay, company,paging.getSortByToList());
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
