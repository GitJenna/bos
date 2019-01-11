package cn.itcast.bos.dao;

import cn.itcast.bos.domain.base.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer>,JpaSpecificationExecutor<Vehicle> {
}
