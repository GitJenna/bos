package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.VehicleRepository;
import cn.itcast.bos.domain.base.Vehicle;
import cn.itcast.bos.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Page<Vehicle> findPageData(int page, int rows, Vehicle vehicle) {
        Pageable pageable= PageRequest.of(page-1,rows);
        return vehicleRepository.findAll(pageable);
    }
}
