package cn.keepting.family.server.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liujh
 */
public class CypherHelper {
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static final String PASSWORD_HASH = "password_hash";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密
    public static final String SALT = "salt";

   /* public static Map<String, String> encryptSHA256(final String password) {
        Map<String, String> resultMap = new HashMap<String, String>();
        final String salt = getSalt();

        String newPassword = password + salt;
        String hashStr = getSHA256Code(newPassword);

        resultMap.put(PASSWORD_HASH, hashStr);
        resultMap.put(SALT, salt);

        return resultMap;
    }*/

    public static boolean checkPassword(final String password, final String passwordHash, final String salt) {
        String newPassword = password + salt;
        String hashStr = getSHA256Code(newPassword);

        return StringUtils.equals(hashStr, passwordHash);
    }

    private static String getSHA256Code(String plainText) {
        String hashStr = null;
        try {
            hashStr = new String(plainText);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            hashStr = byteToString(md.digest(plainText.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return hashStr;
    }

    /**
     *     * 利用java原生的类实现SHA256加密
     *     * @param str 加密后的报文
     *     * @return
     *     
     */
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    //sha1 加密
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    /**
     *  * 将byte转为16进制
     *  * @param bytes
     *  * @return
     *  
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    public static String genFileMD5(File file) throws FileNotFoundException {
        if (file == null) {
            return null;
        }

        return genFileMD5(new FileInputStream(file), file.length());
    }

    public static String genFileMD5(FileInputStream in, long fileLen) {
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
                    fileLen);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            return byteToString(md5.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }


    public static String getAESKey() throws NoSuchAlgorithmException {

        KeyGenerator kg = KeyGenerator.getInstance("AES");

        kg.init(128);

        //要生成多少位，只需要修改这里即可128, 192或256

        SecretKey sk = kg.generateKey();

        byte[] b = sk.getEncoded();

        return byteToString(b);

    }


    public static String encryptAES(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(), KEY_ALGORITHM));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return byte2Hex(result);//通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    public static String decrypt(String encryptContent, String password) {
        if (StringUtils.isAnyEmpty(encryptContent, password)) {
            return null;
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(), KEY_ALGORITHM));
            //执行解密操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(result, "UTF-8");
        } catch (Exception e) {
        }
        return null;
    }



    public static byte[] parseHexStr2Byte(String hexStr){
        if(hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


  /*  private static String getSalt() {
        String salt = UUIDGenarator.getUUID() + "******";

        if (salt != null && salt.length() > 32) {
            salt = salt.substring(0, 32);
        }
        return salt;
    }*/

    public static void main(String[] args) throws Exception {
        String s="13899999999";
        String key="MSVOxKtONO4030WN";
        System.out.println(key.getBytes().length);

        String res=encryptAES(s,key);
        System.out.println(res);



        System.out.println(decrypt(res,key));
    }

}
