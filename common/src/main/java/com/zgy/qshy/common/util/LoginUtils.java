package com.zgy.qshy.common.util;


import com.zgy.qshy.common.base.BaseConstant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class LoginUtils {

    public static String encryptPwd(String account, String password) {
        // 给密码加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);
        Object obj = new SimpleHash(BaseConstant.HASH_ALGORITH_NAME, password,
                credentialsSalt, BaseConstant.HASH_ITERRATIONS);
        return obj.toString();
    }


    public static void main(String[] args) {
        // 给密码加密
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        Object obj = new SimpleHash(BaseConstant.HASH_ALGORITH_NAME, "123321",
                credentialsSalt, BaseConstant.HASH_ITERRATIONS);
        System.out.println(obj.toString());
    }
}
