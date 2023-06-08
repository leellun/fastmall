package com.newland.mall.service.impl;

import com.newland.mall.entity.Region;
import com.newland.mall.mapper.RegionMapper;
import com.newland.mall.model.vo.RegionVO;
import com.newland.mall.service.RegionService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 行政区域表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {
    @Override
    public List<Region> listByPid(Long id) {
        return baseMapper.listByPid(id);
    }

    @Override
    public List<RegionVO> list() {
        List<RegionVO> regionVoList = new ArrayList<>();

        List<Region> regions = baseMapper.listAll();
        Map<Integer, List<Region>> collect = regions.stream().collect(Collectors.groupingBy(Region::getType));
        byte provinceType = 1;
        List<Region> provinceList = collect.get(provinceType);
        byte cityType = 2;
        List<Region> city = collect.get(cityType);
        Map<Long, List<Region>> cityListMap = city.stream().collect(Collectors.groupingBy(Region::getPid));
        byte areaType = 3;
        List<Region> areas = collect.get(areaType);
        Map<Long, List<Region>> areaListMap = areas.stream().collect(Collectors.groupingBy(Region::getPid));

        for (Region province : provinceList) {
            RegionVO provinceVO = new RegionVO();
            provinceVO.setId(province.getId());
            provinceVO.setName(province.getName());
            provinceVO.setCode(province.getCode());
            provinceVO.setType(province.getType());

            List<Region> cityList = cityListMap.get(province.getId());
            List<RegionVO> cityVOList = new ArrayList<>();
            for (Region cityVo : cityList) {
                RegionVO cityVO = new RegionVO();
                cityVO.setId(cityVo.getId());
                cityVO.setName(cityVo.getName());
                cityVO.setCode(cityVo.getCode());
                cityVO.setType(cityVo.getType());

                List<Region> areaList = areaListMap.get(cityVo.getId());
                List<RegionVO> areaVOList = new ArrayList<>();
                for (Region area : areaList) {
                    RegionVO areaVO = new RegionVO();
                    areaVO.setId(area.getId());
                    areaVO.setName(area.getName());
                    areaVO.setCode(area.getCode());
                    areaVO.setType(area.getType());
                    areaVOList.add(areaVO);
                }

                cityVO.setChildren(areaVOList);
                cityVOList.add(cityVO);
            }
            provinceVO.setChildren(cityVOList);
            regionVoList.add(provinceVO);
        }
        return regionVoList;
    }

}