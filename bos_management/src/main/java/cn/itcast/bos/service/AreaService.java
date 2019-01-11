package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Area;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AreaService {

    Page<Area> findPageData(int page, int rows, Area area);

    void save(Area area);

    void delete(String[] ids);

}
