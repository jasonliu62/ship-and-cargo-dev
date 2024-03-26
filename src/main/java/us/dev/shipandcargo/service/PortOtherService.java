package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.PortOtherVo;
import us.dev.shipandcargo.domain.PortOther;
import us.dev.shipandcargo.dao.PortOtherDao;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortOtherService {

    @Autowired
    private PortOtherDao portOtherDao;

    public int insertPortOther(PortOther portOther) {
        Long id = portOther.getPortId();
        if (selectPortOtherById(id) != null) {
            // 这边要改成port
            throw new ApiException(ApiMessage.PORT_OTHER_EXISTED);
        }
        return portOtherDao.insertPortOther(portOther);
    }

    public PortOther selectPortOtherById(Long portId) {
        return portOtherDao.selectPortOtherById(portId);
    }

    public List<PortOther> selectPortOtherByDistance(Float distance) {
        return portOtherDao.selectPortOtherByDistance(distance);
    }

    public List<PortOther> selectPortOtherByOilPrice(Float oilPrice) {
        return portOtherDao.selectPortOtherByOilPrice(oilPrice);
    }

    public List<PortOther> selectPortOtherByAvgTimeStay(Float avgTimeStay) {
        return portOtherDao.selectPortOtherByAvgTimeStay(avgTimeStay);
    }

    public List<PortOther> selectPortOtherByLoadTime(Float loadTime) {
        return portOtherDao.selectPortOtherByLoadTime(loadTime);
    }

    public List<PortOther> selectPortOtherByLoadEfficiency(Float loadEfficiency) {
        return portOtherDao.selectPortOtherByLoadEfficiency(loadEfficiency);
    }

    public List<PortOther> selectPortOtherByUnloadTime(Float unloadTime) {
        return portOtherDao.selectPortOtherByUnloadTime(unloadTime);
    }

    public List<PortOther> selectPortOtherByUnloadEfficiency(Float unloadEfficiency) {
        return portOtherDao.selectPortOtherByUnloadEfficiency(unloadEfficiency);
    }

    public List<PortOther> selectPortOtherByPortFee(Float portFee) {
        return portOtherDao.selectPortOtherByPortFee(portFee);
    }

    public List<PortOther> selectPortOtherByAvgAnchorTime(Float avgAnchorTime) {
        return portOtherDao.selectPortOtherByAvgAnchorTime(avgAnchorTime);
    }

    public int deletePortOtherById(Long portId) {
        return portOtherDao.deletePortOtherById(portId);
    }

    public int updatePortOther(PortOther portOther) {
        return portOtherDao.updatePortOther(portOther);
    }

    public PageData<PortOtherVo> listPortOther(PaginationProps paging, Long portId, Float distance, Float oilPrice, Float avgTimeStay, Float loadTime, Float loadEfficiency, Float unloadTime, Float unloadEfficiency, Float portFee, Float avgAnchorTime) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<PortOther> portOthers = portOtherDao.queryByCondition(portId,
                distance,
                oilPrice,
                avgTimeStay,
                loadTime,
                loadEfficiency,
                unloadTime,
                unloadEfficiency,
                portFee,
                avgAnchorTime,
                paging.getSortByToList());
        if (portOthers.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<PortOtherVo>());
        }
        List<PortOtherVo> portOtherVoList = new ArrayList<PortOtherVo>();
        for (PortOther portOther: portOthers) {
            PortOtherVo portOtherVo = new PortOtherVo();
            ObjectUtil.objectCopy(portOther, portOtherVo);
            portOtherVoList.add(portOtherVo);
        }

        return PageDataUtil.convertToPageData(portOthers, portOtherVoList);
    }

}

