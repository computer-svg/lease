package com.tc.lease.web.admin.service;

import com.tc.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tc.lease.model.enums.ReleaseStatus;
import com.tc.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.tc.lease.web.admin.vo.apartment.ApartmentSubmitVo;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);

    ApartmentDetailVo getApartmentDetailById(Long id);

    void removeApartmentById(Long id);


}
