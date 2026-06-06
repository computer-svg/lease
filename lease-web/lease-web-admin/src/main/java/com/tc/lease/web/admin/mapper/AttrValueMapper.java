package com.tc.lease.web.admin.mapper;

import com.tc.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.lease.web.admin.vo.attr.AttrValueVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.tc.lease.model.AttrValue
*/
public interface AttrValueMapper extends BaseMapper<AttrValue> {

    void deleteByAttrKeyId(Long attrKeyId);

    List<AttrValueVo> listAttrValueByRoomId(Long id);
}




