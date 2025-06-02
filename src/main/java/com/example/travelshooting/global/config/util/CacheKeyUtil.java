package com.example.travelshooting.global.config.util;

import com.example.travelshooting.global.common.Const;

public class CacheKeyUtil {

    private CacheKeyUtil() {
    }

    public static String getCompanyPageKey(int page) {
        return Const.COMPANY_CACHE_PREFIX + page;
    }
}
