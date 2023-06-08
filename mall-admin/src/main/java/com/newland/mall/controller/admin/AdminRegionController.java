package com.newland.mall.controller.admin;

import com.newland.mall.entity.Region;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.RegionVO;
import com.newland.mall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping("/clist/{id}")
    public RestResponse<List<Region>> clist(@PathVariable Long id) {
        List<Region> regionList = regionService.listByPid(id);
        return RestResponse.ok(regionList);
    }

    @GetMapping
    public RestResponse<List<RegionVO>> list() {
        List<RegionVO> list=regionService.list();
        return RestResponse.ok(list);
    }
}
