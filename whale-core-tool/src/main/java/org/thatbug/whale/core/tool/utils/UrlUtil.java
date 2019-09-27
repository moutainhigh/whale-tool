package org.thatbug.whale.core.tool.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * UrlUtil
 *
 * @author qzl
 * @date 13:53 2019/9/17
 */
public class UrlUtil extends org.springframework.web.util.UriUtils {

    /**
     * url 编码，同js decodeURIComponent
     *
     * @param source  url
     * @param charset 字符集
     * @return 编码后的url
     */
    public static String encodeURL(String source, Charset charset) {
        return UrlUtil.encode(source, charset.name());
    }

    /**
     * url 解码
     *
     * @param source  url
     * @param charset 字符集
     * @return 解码url
     */
    public static String decodeURL(String source, Charset charset) {
        return UrlUtil.decode(source, charset.name());
    }

    /**
     * 获取url路径
     *
     * @param uriStr 路径
     * @return url路径
     */
    public static String getPath(String uriStr) {
        URI uri;

        try {
            uri = new URI(uriStr);
        } catch (URISyntaxException var3) {
            throw new RuntimeException(var3);
        }

        return uri.getPath();
    }

}
