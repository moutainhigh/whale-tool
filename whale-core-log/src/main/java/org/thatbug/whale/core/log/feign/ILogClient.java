package org.thatbug.whale.core.log.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thatbug.whale.core.launch.constant.AppConstant;
import org.thatbug.whale.core.log.model.LogApi;
import org.thatbug.whale.core.log.model.LogError;
import org.thatbug.whale.core.log.model.LogUsual;
import org.thatbug.whale.core.tool.api.R;

/**
 * feign接口类
 *
 * @author qzl
 * @date 16:16 2019/9/19
 */
@FeignClient(
        value = AppConstant.APPLICATION_LOG_NAME,
        fallback = LogClientFallback.class
)
public interface ILogClient {

    String API_PREFIX = "/log";

    /**
     * 保存错误日志
     *
     * @param log 日志实体
     * @return boolean
     */
    @PostMapping(API_PREFIX + "/saveUsualLog")
    R<Boolean> saveUsualLog(@RequestBody LogUsual log);

    /**
     * 保存操作日志
     *
     * @param log 日志实体
     * @return boolean
     */
    @PostMapping(API_PREFIX + "/saveApiLog")
    R<Boolean> saveApiLog(@RequestBody LogApi log);

    /**
     * 保存错误日志
     *
     * @param log 日志实体
     * @return boolean
     */
    @PostMapping(API_PREFIX + "/saveErrorLog")
    R<Boolean> saveErrorLog(@RequestBody LogError log);

}
