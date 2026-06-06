package com.tc.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tc.lease.common.result.Result;
import com.tc.lease.model.entity.ApartmentInfo;
import com.tc.lease.model.enums.ReleaseStatus;
import com.tc.lease.web.admin.service.ApartmentInfoService;
import com.tc.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.tc.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.tc.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.tc.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@Slf4j
@RequestMapping("/admin/apartment")
@Validated
public class ApartmentController {

    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    //在参数上添加@Valid注解表示需要验证，Spring会自动根据ApartmentSubmitVo中的验证注解进行验证，如果验证失败会抛出MethodArgumentNotValidException异常
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        log.info("apartmentSubmitVo:{}",apartmentSubmitVo);
        apartmentInfoService.saveOrUpdateApartment(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {

        IPage<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> list = apartmentInfoService.pageApartmentItem(page, queryVo);
        return Result.ok(list);
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        log.info("id:{}",id);
        ApartmentDetailVo apartmentDetailVo = apartmentInfoService.getApartmentDetailById(id);
        return Result.ok(apartmentDetailVo);
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@Length(max = 9) @RequestParam Long id) {
        apartmentInfoService.removeApartmentById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        //构建条件构造器
        LambdaUpdateWrapper<ApartmentInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ApartmentInfo::getId, id);
        updateWrapper.set(ApartmentInfo::getIsRelease, status);
        apartmentInfoService.update(updateWrapper);
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        //构建构造器
        LambdaQueryWrapper<ApartmentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApartmentInfo::getDistrictId, id);
        List<ApartmentInfo> apartmentInfoList = apartmentInfoService.list(queryWrapper);
        return Result.ok(apartmentInfoList);
    }
}














