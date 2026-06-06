package com.tc.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.lease.model.entity.LabelInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【label_info(标签信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.tc.lease.model.LabelInfo
 */
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {


     List<LabelInfo> listLabelInfoByRoomId(Long id);

    List<LabelInfo> selectListByApartmentId(Long apartmentId);

}




