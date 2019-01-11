package cn.itcast.bos.dao;

import cn.itcast.bos.domain.base.TakeTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TakeTimeRepository extends JpaRepository<TakeTime,Integer>,JpaSpecificationExecutor<TakeTime> {
}
