package com.gaoyifeng.apigateway.core.executor;

import com.gaoyifeng.apigateway.core.executor.result.SessionResult;
import com.gaoyifeng.apigateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname Executor
 * @Description TODO
 * @Date 2024/11/15 20:25
 * @Created by gaoyifeng
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
