package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.common.ResponseResult;
import cn.itcast.bos.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/courier")
public class CourierController {

    @Autowired
    private CourierService courierService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows, Courier courier) {
        Page<Courier> pageData = courierService.findPageData(page, rows, courier);
        Map<String, Object> result = new HashMap<>();
        result.put("total", pageData.getTotalElements());
        result.put("rows", pageData.getContent());
        return result;
    }

    @RequestMapping("/save")
    public ResponseResult save(Courier courier) {
        try {
            courierService.save(courier);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }

    @RequestMapping("/updateDeltag")
    public ResponseResult updateDeltag(Character deltag, int[] ids) {
        try {
            courierService.updateDeltag(deltag, ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }

}

