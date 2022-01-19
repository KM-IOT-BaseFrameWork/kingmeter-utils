package com.kingmeter.utils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

public class TokenUtils {
    private static TokenUtils instance;

    private TokenUtils() {
    }


    public static TokenUtils getInstance() {
        if (instance == null) {
            synchronized (TokenUtils.class) {
                if (instance == null) {
                    instance = new TokenUtils();
                }
            }
        }
        return instance;
    }

    public TokenResult getRandomSiteToken(ConcurrentMap<String, String> existTokenMap) {
        TokenResult tmpToken = createToken(32);
        if (existTokenMap.containsKey(tmpToken)) {
            return getRandomSiteToken(existTokenMap);
        }
        return tmpToken;
    }

    public TokenResult getRandomLockToken(ConcurrentMap<String, String> existTokenMap) {
        TokenResult tmpToken = createToken(4);
        if (existTokenMap.containsKey(tmpToken)) {
            return getRandomLockToken(existTokenMap);
        }
        return tmpToken;
    }

    private TokenResult createToken(int num) {
        byte[] tokenArray = new byte[num];
        StringBuffer token = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int tmp = new Random().nextInt(127);
            tokenArray[i] = (byte) tmp;
            token.append(Integer.toHexString(tmp));
        }
        return new TokenResult(token.toString(),
                tokenArray);
    }


}
