package com.tc.lease.web.admin.mapper;

import com.tc.lease.model.entity.GraphInfo;
import com.tc.lease.model.enums.ItemType;
import com.tc.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.tc.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    @Select("SELECT name, url FROM graph_info WHERE item_type = #{itemType} AND item_id = #{itemId}")
    List<GraphVo> selectListByItemTypeAndId(@Param("itemType") ItemType itemType, @Param("itemId") Long itemId);

    List<GraphVo> listByItemTypeAndId(ItemType itemType, Long id);
}




