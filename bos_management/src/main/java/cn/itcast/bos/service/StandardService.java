package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Standard;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StandardService {
    Page<Standard> findPageData(int page, int rows);

    void save(Standard standard);

    void delete(int id);

    List<Standard> findAll() throws JsonProcessingException;


}
