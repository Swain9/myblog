package cn.maolin.myblog.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Zml on 2017/2/14.
 * HASH工具类
 */
public class HashUtil {
    /**
     * emoji表情替换
     *
     * @param source  原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source, String slipStr) {
        if (source != null && !"".equals(source.trim())) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }

    /**
     * 伪随机数字
     *
     * @return String
     */
    public static String getRandomStr(int num) {
        String[] rondomStr = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(rondomStr[(int) (Math.random() * 36)]);
        }
        return sb.toString();
    }

    public static String getPwdHash(String userpwd) {
        if (userpwd == null || "".equals(userpwd.trim())) {
            return "";
        }
        return HashUtil.getStringHash(HashUtil.getStringHash(HashUtil.base64encode(userpwd), "MD5") + userpwd, "SHA-512");
    }

    /**
     * URL地址解码
     *
     * @param url  链接
     * @param type 解码类型
     * @return 解码后的URL地址
     * @throws UnsupportedEncodingException 异常
     */
    public static String urlDecode(String url, String type) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, type);
    }

    /**
     * URL地址编码
     *
     * @param url  URL
     * @param type 编码类型
     * @return 编码后的地址
     * @throws UnsupportedEncodingException 异常
     */
    public static String urlEncode(String url, String type) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, type);
    }

    /**
     * 获取文件的HASH
     *
     * @param path     文件路径
     * @param hashType HASH类型
     * @return hash
     */
    public static String getFileHash(String path, String hashType) {
        try (
                FileInputStream in = new FileInputStream(path)
        ) {
            MessageDigest md = MessageDigest.getInstance(hashType);
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            return toHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的HASH格式");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串MD5加密
     *
     * @param value    字符串
     * @param hashType hash类型
     * @return hash
     */
    public static String getStringHash(String value, String hashType) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashType);
            byte[] bytes = value.getBytes();
            return toHexString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的HASH格式");
        }
    }

    /**
     * 将字符串编码为Base64
     *
     * @param str 字符串
     * @return base64字符串
     */
    public static String base64encode(String str) {
        Base64.Encoder base64 = Base64.getEncoder();
        return base64.encodeToString(str.getBytes());
    }

    /**
     * 将字符串编码为Base64
     *
     * @param str      字符串
     * @param charaset 字符串的编码类型
     * @return base64字符串
     * @throws UnsupportedEncodingException 异常
     */
    public static String base64encode(String str, String charaset) throws UnsupportedEncodingException {
        Base64.Encoder base64 = Base64.getEncoder();
        return base64.encodeToString(str.getBytes(charaset));
    }

    /**
     * 解码base64字符串
     *
     * @param str base64字符串
     * @return 解码后的字符串
     * @throws IOException 异常
     */
    public static String base64decode(String str) throws IOException {
        Base64.Decoder base64 = Base64.getDecoder();
        byte b[] = base64.decode(str);
        return new String(b);//GBK
    }

    /**
     * 解码base64字符串
     *
     * @param str      base64字符串
     * @param charaset 解码后字符串编码
     * @return 解码后的字符串
     * @throws IOException 异常
     */
    public static String base64decode(String str, String charaset) throws IOException {
        Base64.Decoder base64 = Base64.getDecoder();
        byte b[] = base64.decode(str);
        return new String(b, charaset);
    }

    private static String byteArrayToHex(byte[] bytes) {
        String hs = "";
        String stmp = "";
        int len = bytes.length;
        for (int n = 0; n < len; n++) {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < len - 1) {
                hs = hs + "";
            }
        }
        return hs;
    }

    private static char[] hexChar = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(hexChar[(b & 0xf0) >>> 4]);
            sb.append(hexChar[b & 0x0f]);
        }
        return sb.toString();
    }
}
