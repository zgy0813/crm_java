package com.zgy.wechat.core;


import com.zgy.wechat.common.base.BaseConstant;

public interface Constants extends BaseConstant {

    interface LOGIN {
        int PHONE   = 0;
        int ACCOUNT = 1;
        int MAIL    = 2;
    }

    interface USER {
        interface WATCH_COUNT {
            int HAS_INVITE_CODE = 100;
            int NO_INVITE_CODE  = 50;
        }
        interface SEX {
            int MALE = 0;
            int FEMALE = 1;

        }
    }

    interface ROLE {
        String BASE = "user";
    }

}
