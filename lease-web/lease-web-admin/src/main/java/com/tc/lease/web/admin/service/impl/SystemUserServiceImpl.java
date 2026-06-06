package com.tc.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tc.lease.model.entity.SystemPost;
import com.tc.lease.model.entity.SystemUser;
import com.tc.lease.web.admin.mapper.SystemPostMapper;
import com.tc.lease.web.admin.mapper.SystemUserMapper;
import com.tc.lease.web.admin.service.SystemUserService;
import com.tc.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.tc.lease.web.admin.vo.system.user.SystemUserQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemPostMapper systemPostMapper;

    @Override
    public IPage<SystemUserItemVo> pageSystemUserItemByQuery(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.pageSystemUserItemByQuery(page, queryVo);
    }

    @Override
    public SystemUserItemVo getSystemUserItemById(Long id) {

        SystemUser systemUser = systemUserMapper.selectById(id);
        SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        //复制属性
        BeanUtils.copyProperties(systemUser, systemUserItemVo);
        systemUserItemVo.setPostName(systemPost.getName());
        return systemUserItemVo;
    }
}




