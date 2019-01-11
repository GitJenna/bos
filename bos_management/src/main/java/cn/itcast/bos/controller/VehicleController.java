package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Vehicle;
import cn.itcast.bos.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows, Vehicle vehicle) {
        Page<Vehicle> pageData=vehicleService.findPageData(page,rows,vehicle);
        Map<String,Object> result=new HashMap<String,Object>();
        //获取总记录数
        result.put("total",pageData.getTotalElements());
        //获取列表数据
        result.put("rows",pageData.getContent());
        return result;
    }
}
