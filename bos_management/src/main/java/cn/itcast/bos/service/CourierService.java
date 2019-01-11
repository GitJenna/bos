package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Courier;
import org.springframework.data.domain.Page;

public interface CourierService {

    Page<Courier> findPageData(int page, int rows, Courier courier);

    void save(Courier courier);

    void updateDeltag(Character deltag,int[] ids);
}
