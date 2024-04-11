package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.OutputVo;
import us.dev.shipandcargo.dao.OutputDao;
import us.dev.shipandcargo.domain.Output;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutputService {

    @Autowired
    private OutputDao outputDao;

    public int insertOutput(Output output) {
        return outputDao.insertOutput(output);
    }

    public int deleteOutputBy3Ids(Long groupId, Long id, Long uploaderId) {
        return outputDao.deleteOutputBy3Ids(groupId, id, uploaderId);
    }

    public int deleteFromHistory(Long groupId, Long uploaderId) {
        return outputDao.deleteFromHistory(groupId, uploaderId);
    }

    public PageData<OutputVo> listOutput(PaginationProps paging, Long groupId,
                                         Long uploaderId) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<Output> outputList = outputDao.queryByCondition(groupId, uploaderId, paging.getSortByToList());
        if (outputList.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<OutputVo>());
        }

        List<OutputVo> outputVoList = new ArrayList<OutputVo>();
        for (Output output: outputList) {
            OutputVo outputVo = new OutputVo();
            ObjectUtil.objectCopy(output, outputVo);
            outputVoList.add(outputVo);
        }

        return PageDataUtil.convertToPageData(outputList, outputVoList);
    }

}
