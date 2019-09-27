package org.thatbug.whale.core.secure.constant;

/**
 * 授权校验常量
 *
 * @author qzl
 * @date 15:32 2019/9/17
 */
public interface SecureConstant {

    /**
     * 认证请求头
     */
    String BASIC_HEADER_KEY = "Authorization";

    /**
     * 认证请求头前缀
     */
    String BASIC_HEADER_PREFIX = "Basic ";

    /**
     * 认证请求头前缀
     */
    String BASIC_HEADER_PREFIX_EXT = "Basic%20";

    /**
     * whale_client表字段
     */
    String CLIENT_FIELDS = "client_id, client_secret, access_token_validity, refresh_token_validity";

    /**
     * whale_client查询语句
     */
    String BASE_STATEMENT = "select " + CLIENT_FIELDS + " from whale_client";

    /**
     * whale_client查询排序
     */
    String DEFAULT_FIND_STATEMENT = BASE_STATEMENT + " order by client_id";

    /**
     * 查询client_id
     */
    String DEFAULT_SELECT_STATEMENT = BASE_STATEMENT + " where client_id = ?";

}
