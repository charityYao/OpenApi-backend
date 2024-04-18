package com.yao.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 *

 */
public class SignUtils {
    /**
     * 生成签名
     * @param nonce
     * @param secretKey
     *
     * @return
     */
    public static String genSign(String nonce,String timestamp ,String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);

        String content = nonce + timestamp + secretKey;
        return md5.digestHex(content);
    }
}
