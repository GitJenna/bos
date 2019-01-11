package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.StandardRepository;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.StandardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardRepository standardRepository;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Page<Standard> findPageData(int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return standardRepository.findAll(pageable);
    }

    @Override
    public void save(Standard standard) {
        standardRepository.save(standard);
    }

    @Override
    public void delete(int id) {
        standardRepository.deleteById(id);
    }

    @Override
    public List<Standard> findAll() throws JsonProcessingException {
        //从redis缓存中获得指定的数据
        List<Standard> standardListData = (List<Standard>) redisTemplate.boundHashOps("standard.findAll").get("standardListData");
        if (null == standardListData) {
            standardListData = standardRepository.findAll();
            //将数据存储到redis中，下次在查询直接从redis中获得数据，不用在查询数据库
            redisTemplate.boundHashOps("standard.findAll").put("standardListData", standardListData);
        }
        return standardListData;
    }
}
