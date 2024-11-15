package com.gaoyifeng.apigateway.executor;

import com.gaoyifeng.apigateway.executor.result.GatewayResult;
import com.gaoyifeng.apigateway.mapping.HttpStatement;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname Executor
 * @Description TODO
 * @Date 2024/11/15 20:25
 * @Created by gaoyifeng
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
