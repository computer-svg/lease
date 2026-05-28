package com.tc.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tc.lease.model.entity.RoomInfo;
import com.tc.lease.web.admin.mapper.RoomInfoMapper;
import com.tc.lease.web.admin.service.RoomInforService;
import org.springframework.stereotype.Service;

@Service
public class RoomInforServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInforService {
}
