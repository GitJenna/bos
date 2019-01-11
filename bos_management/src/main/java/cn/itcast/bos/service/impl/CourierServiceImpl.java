package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.CourierService;
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
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierRepository courierRepository;

    @Override
    public Page<Courier> findPageData(int page, int rows, Courier courier) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        Specification<Courier> spec=new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<>();
                //isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
                if(StringUtils.isNotBlank(courier.getCourierNum())){
                    Predicate p1= cb.equal(root.get("courierNum").as(String.class),courier.getCourierNum());
                    list.add(p1);
                }
                if(StringUtils.isNotBlank(courier.getCompany())){
                    Predicate p2= cb.like(root.get("company").as(String.class),"%"+courier.getCompany()+"%");
                    list.add(p2);
                }
                if(StringUtils.isNotBlank(courier.getType())){
                    Predicate p3= cb.equal(root.get("type").as(String.class),courier.getType());
                    list.add(p3);
                }
                if(null!=courier.getStandard()&&courier.getStandard().getId()!=null){
                    Predicate p4=cb.equal(root.get("standard").as(Standard.class),courier.getStandard());
                    list.add(p4);
                }
                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        return courierRepository.findAll(spec,pageable);
    }

    @Override
    public void save(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    public void updateDeltag(Character deltag,int[] ids) {
        for (int id : ids) {
            courierRepository.updateDeltag(deltag, id);
        }
    }



}
