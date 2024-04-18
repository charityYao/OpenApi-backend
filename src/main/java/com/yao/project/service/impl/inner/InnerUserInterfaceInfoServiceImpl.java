package com.yao.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.project.common.ErrorCode;
import com.yao.project.exception.BusinessException;
import com.yao.project.service.UserInterfaceInfoService;
import com.yao.yuapicommon.model.entity.UserInterfaceInfo;
import com.yao.yuapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部用户接口信息服务实现类
 *

 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }



    public UserInterfaceInfo getUserInterfaceInfo(long interfaceInfoId, long userId) {
        if (interfaceInfoId <0 || userId<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId", interfaceInfoId);
        queryWrapper.eq("userId", userId);
        return userInterfaceInfoService.getOne(queryWrapper);
    }
}
