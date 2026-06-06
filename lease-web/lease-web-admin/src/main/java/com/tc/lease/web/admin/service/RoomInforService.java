package com.tc.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tc.lease.model.entity.RoomInfo;
import com.tc.lease.web.admin.vo.room.RoomDetailVo;
import com.tc.lease.web.admin.vo.room.RoomItemVo;
import com.tc.lease.web.admin.vo.room.RoomQueryVo;
import com.tc.lease.web.admin.vo.room.RoomSubmitVo;


public interface RoomInforService extends IService<RoomInfo> {

    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    RoomDetailVo getRoomDetailById(Long id);

    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);
}
