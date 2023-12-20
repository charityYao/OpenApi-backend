package com.yao.yuapicommon.service;

import com.yao.yuapicommon.model.entity.User;


/**
 * 内部用户服务
 *
 * @author <a href="https://github.com/liyao">程序员鱼皮</a>
 * @from <a href="https://yao.icu">编程导航知识星球</a>
 */
public interface InnerUserService {
    /**
     * 只负责提供接口，不负责提供具体实现
     */
    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
