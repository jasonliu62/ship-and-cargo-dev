package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.PortVo;
import us.dev.shipandcargo.dao.PortDao;
import us.dev.shipandcargo.domain.Port;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortService {

    @Autowired
    private PortDao portDao;

    public int insertPort(Port port){
        Long portId = port.getPortId();
        if (selectPortByPortId(portId) != null){
            throw new ApiException(ApiMessage.PORT_EXISTED);
        }
        return portDao.insertPort(port);
    }

    public Port selectPortByPortId(Long portId) { return portDao.selectPortByPortId(portId); }

    public List<Port> selectPortByCName(String nameCHN) { return portDao.selectPortByCName(nameCHN); }

    public List<Port> selectPortByEName(String nameENG) { return portDao.selectPortByEName(nameENG); }

    public List<Port> selectPortByLatitude(Float latitude) { return portDao.selectPortByLatitude(latitude); }

    public List<Port> selectPortByLongitude(Float longitude) { return portDao.selectPortByLongitude(longitude); }

    public List<Port> selectPortByMinDraft(Float minDraft) { return portDao.selectPortByMinDraft(minDraft); }

    public int updatePort(Port port) { return portDao.updatePort(port); }

    public int deletePortByPortId(Long portId) { return portDao.deletePortByPortId(portId); }

    public PageData<PortVo> listPort(PaginationProps paging, Long portId,
                                     String nameCHN, String nameENG,
                                     Float latitude, Float longitude,
                                     Float minDraft){
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<Port> ports = portDao.queryByCondition(portId, nameCHN, nameENG, latitude, longitude, minDraft,paging.getSortByToList());
        if (ports.size() == 0){
            return PageDataUtil.convertToPageData(new ArrayList<PortVo>());
        }
        List<PortVo> portVoList = new ArrayList<PortVo>();
        for (Port port:ports){
            PortVo portVo = new PortVo();
            ObjectUtil.objectCopy(port, portVo);
            portVoList.add(portVo);
        }
        return PageDataUtil.convertToPageData(ports, portVoList);
    }


}
