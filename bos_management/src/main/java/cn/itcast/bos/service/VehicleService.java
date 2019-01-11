package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Vehicle;
import org.springframework.data.domain.Page;

public interface VehicleService {
    Page<Vehicle> findPageData(int page, int rows, Vehicle vehicle);
}
