package com.tc.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tc.lease.model.entity.*;
import com.tc.lease.model.enums.ItemType;
import com.tc.lease.web.admin.mapper.*;
import com.tc.lease.web.admin.service.*;
import com.tc.lease.web.admin.vo.attr.AttrValueVo;
import com.tc.lease.web.admin.vo.graph.GraphVo;
import com.tc.lease.web.admin.vo.room.RoomDetailVo;
import com.tc.lease.web.admin.vo.room.RoomItemVo;
import com.tc.lease.web.admin.vo.room.RoomQueryVo;
import com.tc.lease.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomInforServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInforService {


    @Autowired
    private RoomFacilityService roomFacilityService;
    @Autowired
    private RoomLabelService roomLabelService;
    @Autowired
    private RoomLeaseTermService roomLeaseTermService;
    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;

    @Autowired
    private RoomAttrValueService roomAttrValueService;

    @Autowired
    private GraphInfoService graphInfoService;


    @Autowired
    private FacilityInfoMapper facilityInfoMapper;

    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;
    @Autowired
    private AttrValueMapper attrValueMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Override
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
        Boolean isUpdate = roomSubmitVo.getId() != null;
        super.saveOrUpdate(roomSubmitVo);
        if (isUpdate) {

            //删除原来的图片列表
            LambdaQueryWrapper<GraphInfo> queryWrapper6 = new LambdaQueryWrapper<>();
            queryWrapper6.eq(GraphInfo::getItemId, roomSubmitVo.getId());
            queryWrapper6.eq(GraphInfo::getItemType, ItemType.ROOM);
            graphInfoService.remove(queryWrapper6);
            //删除房间配套
            LambdaQueryWrapper<RoomFacility> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(RoomFacility::getRoomId, roomSubmitVo.getId());
            roomFacilityService.remove(queryWrapper);
            //删除房间标签
            LambdaQueryWrapper<RoomLabel> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(RoomLabel::getRoomId, roomSubmitVo.getId());
            roomLabelService.remove(queryWrapper2);
            //删除房间租期
            LambdaQueryWrapper<RoomLeaseTerm> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(RoomLeaseTerm::getRoomId, roomSubmitVo.getId());
            roomLeaseTermService.remove(queryWrapper3);
            //删除房间支付方式
            LambdaQueryWrapper<RoomPaymentType> queryWrapper4 = new LambdaQueryWrapper<>();
            queryWrapper4.eq(RoomPaymentType::getRoomId, roomSubmitVo.getId());
            roomPaymentTypeService.remove(queryWrapper4);
            //删除房间属性
            LambdaQueryWrapper<RoomAttrValue> queryWrapper5 = new LambdaQueryWrapper<>();
            queryWrapper5.eq(RoomAttrValue::getRoomId, roomSubmitVo.getId());
            roomAttrValueService.remove(queryWrapper5);
        }

        //增加房间配套
        List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            ArrayList<RoomFacility> roomFacilities = new ArrayList<>();
            for (Long facilityInfoId : facilityInfoIds) {
                RoomFacility roomFacility = new RoomFacility();
                roomFacility.setRoomId(roomSubmitVo.getId());
                roomFacility.setFacilityId(facilityInfoId);
                roomFacilities.add(roomFacility);
            }
            roomFacilityService.saveBatch(roomFacilities);
        }

        //增加房间标签
        List<Long> labelIds = roomSubmitVo.getLabelInfoIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            ArrayList<RoomLabel> roomLabels = new ArrayList<>();
            for (Long labelId : labelIds) {
                RoomLabel roomLabel = new RoomLabel();
                roomLabel.setRoomId(roomSubmitVo.getId());
                roomLabel.setLabelId(labelId);
                roomLabels.add(roomLabel);
            }
            roomLabelService.saveBatch(roomLabels);
        }

        //增加房间租期
        List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
        if (!CollectionUtils.isEmpty(leaseTermIds)) {
            ArrayList<RoomLeaseTerm> roomLeaseTerms = new ArrayList<>();
            for (Long leaseTermId : leaseTermIds) {
                RoomLeaseTerm roomLeaseTerm = new RoomLeaseTerm();
                roomLeaseTerm.setRoomId(roomSubmitVo.getId());
                roomLeaseTerm.setLeaseTermId(leaseTermId);
                roomLeaseTerms.add(roomLeaseTerm);
            }
            roomLeaseTermService.saveBatch(roomLeaseTerms);
            //增加房间支付方式
            List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
            if (!CollectionUtils.isEmpty(paymentTypeIds)) {
                ArrayList<RoomPaymentType> roomPaymentTypes = new ArrayList<>();
                for (Long paymentTypeId : paymentTypeIds) {
                    RoomPaymentType roomPaymentType = new RoomPaymentType();
                    roomPaymentType.setRoomId(roomSubmitVo.getId());
                    roomPaymentType.setPaymentTypeId(paymentTypeId);
                    roomPaymentTypes.add(roomPaymentType);
                }
                roomPaymentTypeService.saveBatch(roomPaymentTypes);
                //增加房间属性
                List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
                if (!CollectionUtils.isEmpty(attrValueIds)) {
                    ArrayList<RoomAttrValue> roomAttrValues = new ArrayList<>();
                    for (Long attrValueId : attrValueIds) {
                        RoomAttrValue roomAttrValue = new RoomAttrValue();
                        roomAttrValue.setRoomId(roomSubmitVo.getId());
                        roomAttrValue.setAttrValueId(attrValueId);
                        roomAttrValues.add(roomAttrValue);
                    }
                    roomAttrValueService.saveBatch(roomAttrValues);
                    //增加图片列表
                    List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
                    if (!CollectionUtils.isEmpty(graphVoList)) {
                        ArrayList<GraphInfo> graphInfos = new ArrayList<>();
                        for (GraphVo graphVo : graphVoList) {
                            GraphInfo graphInfo = new GraphInfo();
                            graphInfo.setItemId(roomSubmitVo.getId());
                            graphInfo.setItemType(ItemType.ROOM);
                            graphInfo.setUrl(graphVo.getUrl());
                            graphInfo.setName(graphVo.getName());
                            graphInfos.add(graphInfo);

                        }
                        graphInfoService.saveBatch(graphInfos);


                    }
                }
            }

        }
    }

    @Override
    public RoomDetailVo getRoomDetailById(Long id) {
        RoomInfo roomInfo = this.getById(id);
        if (roomInfo == null) {
            return null;
        }
        //获取房间配套信息列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.listFacilityInfoByRoomId(id);
        //获取房间标签信息列表
        List<LabelInfo> labelInfoList = labelInfoMapper.listLabelInfoByRoomId(id);
        //获取房间支付方式列表
        List<PaymentType> paymentTypeList = paymentTypeMapper.listPaymentTypeByRoomId(id);
        //获取房间租期列表
        List<LeaseTerm> leaseTermList = leaseTermMapper.listLeaseTermByRoomId(id);
        //获得房间属性信息列表
        List<AttrValueVo> attrValueVoList = attrValueMapper.listAttrValueByRoomId(id);

        //获得图片列表
        List<GraphVo> graphVoList = graphInfoMapper.listByItemTypeAndId(ItemType.ROOM, id);
        //获取公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(roomInfo.getApartmentId());


        RoomDetailVo roomDetailVo = new RoomDetailVo();
        roomDetailVo.setFacilityInfoList(facilityInfoList);
        roomDetailVo.setLabelInfoList(labelInfoList);
        roomDetailVo.setPaymentTypeList(paymentTypeList);
        roomDetailVo.setLeaseTermList(leaseTermList);
        roomDetailVo.setAttrValueVoList(attrValueVoList);
        roomDetailVo.setGraphVoList(graphVoList);
        roomDetailVo.setApartmentInfo(apartmentInfo);
        BeanUtils.copyProperties(roomInfo, roomDetailVo);
        return roomDetailVo;
    }

    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }

}





