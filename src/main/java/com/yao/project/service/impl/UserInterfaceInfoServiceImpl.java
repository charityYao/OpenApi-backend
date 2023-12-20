package com.yao.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.project.common.ErrorCode;
import com.yao.project.exception.BusinessException;
import com.yao.project.mapper.UserInterfaceInfoMapper;
import com.yao.project.service.UserInterfaceInfoService;
import com.yao.yuapicommon.model.entity.UserInterfaceInfo;
import org.springframework.stereotype.Service;

/**
 * 用户接口信息服务实现类
 *
 * @author <a href="https://github.com/liyao">程序员鱼皮</a>
 * @from <a href="https://yao.icu">编程导航知识星球</a>
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    // [编程学习交流圈](https://www.code-nav.cn/) 快速入门编程不走弯路！30+ 原创学习路线和专栏、500+ 编程学习指南、1000+ 编程精华文章、20T+ 编程资源汇总

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> userInterfaceInfoQueryWrapper = new QueryWrapper<>();
        userInterfaceInfoQueryWrapper.eq("interfaceInfoId", interfaceInfoId)
                .eq("userId",userId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(userInterfaceInfoQueryWrapper);
        if(userInterfaceInfo == null){
            UserInterfaceInfo userInterfaceInfo1 = new UserInterfaceInfo();
            userInterfaceInfo1.setUserId(userId);
            userInterfaceInfo1.setInterfaceInfoId(interfaceInfoId);
            userInterfaceInfo1.setTotalNum(0);
            userInterfaceInfo1.setLeftNum(10);
            userInterfaceInfo1.setStatus(1);
            this.save(userInterfaceInfo1);
        }
        //设置查询条件 满足interfaceInfoId和userId
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);

//        updateWrapper.gt("leftNum", 0);
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }

}




