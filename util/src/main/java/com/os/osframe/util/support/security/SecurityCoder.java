/**
 * 2011-01-11
 */
package com.os.osframe.util.support.security;

import java.security.Security;

/**
 * 加密基类
 * 
 * @author ShenHuaJie
 * @version 1.0
 * @since 1.0
 */
public abstract class SecurityCoder {
    private static Byte ADDFLAG = 0;
    static {
        if (ADDFLAG == 0) {
            // 加入BouncyCastleProvider支持
            Security.addProvider(new BouncyCastleProvider());
            ADDFLAG = 1;
        }
    }
}
