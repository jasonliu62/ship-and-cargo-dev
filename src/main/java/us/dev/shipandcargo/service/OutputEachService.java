package us.dev.shipandcargo.service;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.OutputEachVo;
import us.dev.shipandcargo.Vo.OutputVo;
import us.dev.shipandcargo.dao.OutputEachDao;
import us.dev.shipandcargo.domain.OutputEach;
import us.dev.shipandcargo.request.paging.PageData;
import us.dev.shipandcargo.request.paging.PaginationProps;
import us.dev.shipandcargo.util.ObjectUtil;
import us.dev.shipandcargo.util.PageDataUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutputEachService {

    @Autowired
    private OutputEachDao outputEachDao;

    public int insertOutputEach(OutputEach outputEach) {
        return outputEachDao.insertOutput(outputEach);
    }

    public int deleteOutputEachsBy3Ids(Long groupId, Long outputId, Long uploaderId) {
        return outputEachDao.deleteOutputEachsBy3Ids(groupId, outputId, uploaderId);
    }

    public int deleteFromHistory(Long groupId, Long uploaderId) {
        return outputEachDao.deleteFromHistory(groupId, uploaderId);
    }

    public PageData<OutputEachVo> listOutputEach(PaginationProps paging,
                                                 Long groupId,
                                                 Long outputId,
                                                 Long uploaderId) {
        PageHelper.startPage(paging.getCurrent(), paging.getPageSize());
        List<OutputEach> outputEachList = outputEachDao.queryByCondition(groupId, outputId, uploaderId, paging.getSortByToList());
        if (outputEachList.size() == 0) {
            return PageDataUtil.convertToPageData(new ArrayList<OutputEachVo>());
        }

        List<OutputEachVo> outputEachVoList = new ArrayList<OutputEachVo>();
        for (OutputEach outputEach: outputEachList) {
            OutputEachVo outputEachVo = new OutputEachVo();
            ObjectUtil.objectCopy(outputEach, outputEachVo);
            outputEachVoList.add(outputEachVo);
        }

        return PageDataUtil.convertToPageData(outputEachList, outputEachVoList);

    }

}
