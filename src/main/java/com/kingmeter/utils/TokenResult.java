package com.kingmeter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResult {
    String token;
    byte[] tokenArray;
}
