package com.chia.multienty.wechat.official.service;

public interface IMService {
    void handleMessageReceived(String decryptedMsg, String openId);
}
