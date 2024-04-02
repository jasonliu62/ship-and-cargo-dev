package us.dev.shipandcargo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.dao.DistanceDao;
import us.dev.shipandcargo.domain.Distance;

import java.util.List;

@Service
public class DistanceService {

    @Autowired
    private DistanceDao distanceDao;

    public int insertDistance(Distance distance) {
        return distanceDao.insertDistance(distance);
    }

    public Distance selectDistanceById(Long id) {
        return distanceDao.selectDistanceById(id);
    }

    public List<Distance> selectAllDistances() {
        return distanceDao.selectAllDistances();
    }

    public Distance selectDistanceByStartAndEndPort(Long startPortId, Long endPortId) {
        return distanceDao.selectDistanceByStartAndEndPort(startPortId, endPortId);
    }

    public int updateDistance(Distance distance) {
        return distanceDao.updateDistance(distance);
    }

    public int deleteDistanceById(Long id) {
        return distanceDao.deleteDistanceById(id);
    }
}
