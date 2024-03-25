package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.PortActivityVo;
import us.dev.shipandcargo.Vo.PortVo;
import us.dev.shipandcargo.dao.PortActivityDao;
import us.dev.shipandcargo.domain.PortActivity;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortActivityService {

    @Autowired
    private PortActivityDao portActivityDao;

    public int insertPortActivity(PortActivity portActivity) {
        Long portId = portActivity.getPortId();
        if (selectPortActivityByPortId(portId) != null) {
            throw new ApiException(ApiMessage.PORT_ACTIVITY_EXISTED);
        }
        return portActivityDao.insertPortActivity(portActivity);
    }

    public PortActivity selectPortActivityByPortId(Long portId) {
        return portActivityDao.selectPortActivityByPortId(portId);
    }

    public List<PortActivity> selectPortActivityByShipAmount(Long shipAmount) {
        return portActivityDao.selectPortActivityByShipAmount(shipAmount);
    }

    public int updatePortActivity(PortActivity portActivity) {
        return portActivityDao.updatePortActivity(portActivity);
    }

    public int deletePortActivityByPortId(Long portId) {
        return portActivityDao.deletePortActivityByPortId(portId);
    }

    public PageData<PortActivityVo> listPortActivity(PaginationProps paging, Long portId, Long shipAmount) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<PortActivity> portActivities = portActivityDao.queryByCondition(portId, shipAmount, paging.getSortByToList());
        if (portActivities.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<PortActivityVo>());
        }
        List<PortActivityVo> portActivityVoList = new ArrayList<PortActivityVo>();
        for (PortActivity portActivity : portActivities) {
            PortActivityVo portActivityVo = new PortActivityVo();
            ObjectUtil.objectCopy(portActivity, portActivityVo);
            portActivityVoList.add(portActivityVo);
        }
        return PageDataUtil.convertToPageData(portActivities, portActivityVoList);

    }
}
