package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.common.ResponseResult;
import cn.itcast.bos.service.AreaService;
import cn.itcast.bos.util.IdWorker;
import cn.itcast.bos.util.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows, Area area) {
        Page<Area> pageData = areaService.findPageData(page, rows, area);
        Map<String, Object> result = new HashMap<>();
        result.put("total", pageData.getTotalElements());
        result.put("rows", pageData.getContent());
        return result;
    }

    @RequestMapping("/save")
    public ResponseResult save(Area area) {
        if(area.getId()==null||"".equals(area.getId())){
            area.setId((new IdWorker()).nextId()+"");
            //System.out.println(area.getId());
        }
        try {
            areaService.save(area);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }

    @RequestMapping("/delete")
    public ResponseResult delete(String[] ids) {
        try {
            areaService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
        return ResponseResult.SUCCESS();
    }

    @RequestMapping("/batchImport")
    public void batchImport(MultipartFile file) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            if (row.getCell(0) == null || StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
                continue;
            }
            Area area = new Area();
            area.setId(row.getCell(0).getStringCellValue());
            area.setProvince(row.getCell(1).getStringCellValue());
            area.setCity(row.getCell(2).getStringCellValue());
            area.setDistrict(row.getCell(3).getStringCellValue());
            area.setPostcode(row.getCell(4).getStringCellValue());

            //基于pinyin4j生成城市编码和简码
            String province=area.getProvince();
            String city=area.getCity();
            String district=area.getDistrict();
            province=province.substring(0,province.length()-1);
            city=city.substring(0,city.length()-1);
            String[] headArry= PinYin4jUtils.getHeadByString(province+city+district);
            StringBuffer buffer=new StringBuffer();
            for (String headStr : headArry) {
                buffer.append(headStr);
            }
            String shortycode=buffer.toString();
            area.setShortcode(shortycode);
            String citycode=PinYin4jUtils.hanziToPinyin(city,"");
            area.setCitycode(citycode);
            areaService.save(area);
        }
    }
}
