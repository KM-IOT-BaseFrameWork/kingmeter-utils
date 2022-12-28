package com.kingmeter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResult {
    String token;
    byte[] tokenArray;
    boolean reLogin;//the station will re login if the network is not stable at that moment
}
