package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.AreaRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Page<Area> findPageData(int page, int rows, Area area) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        Specification<Area> spec=new Specification<Area>() {
            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<>();
                //isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
                if(StringUtils.isNotBlank(area.getProvince())){
                    Predicate p1= cb.like(root.get("province").as(String.class),"%"+area.getProvince()+"%");
                    list.add(p1);
                }
                if(StringUtils.isNotBlank(area.getCity())){
                    Predicate p2= cb.like(root.get("city").as(String.class),"%"+area.getCity()+"%");
                    list.add(p2);
                }
                if(StringUtils.isNotBlank(area.getDistrict())){
                    Predicate p3= cb.like(root.get("district").as(String.class),"%"+area.getDistrict()+"%");
                    list.add(p3);
                }
                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        return areaRepository.findAll(spec,pageable);
    }

    @Override
    public void save(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            areaRepository.deleteById(id);
        }
    }


}
