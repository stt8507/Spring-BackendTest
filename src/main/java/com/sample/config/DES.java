package com.sample.config;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DES {

	private static final String KEY = "FCP5KG8R";
	
	/**
     *    數據加密，算法（DES）
     *
     * @param data 要進行加密的數據
     * @param desKey DES密鑰
     * @return 加密後的數據
     */
    public static String encrypt(String data) {
    	
        String encryptedData = null;
        byte[] desKey = KEY.getBytes();
        
        try {
            // DES算法要求有一個可信任的隨機數源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskeySpec = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("加密錯誤，錯誤信息：", e);
        }
        return encryptedData;
    }
    
    /**
     *    數據解密，算法（DES）
     *
     * @param cryptData 加密數據
     * @param desKey DES密鑰
     * @return 解密後的數據
     */
    public static String decrypt(String cryptData) {
    	
        String decryptedData = null;
        byte[] desKey = KEY.getBytes();
        
        try {
            // DES算法要求有一個可信任的隨機數源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskeySpec = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(cryptData)));
        } catch (Exception e) {
            throw new RuntimeException("解密錯誤，錯誤信息：", e);
        }
        return decryptedData;
    }
}
