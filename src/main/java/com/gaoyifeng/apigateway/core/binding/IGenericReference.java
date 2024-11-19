package com.gaoyifeng.apigateway.core.binding;

import com.gaoyifeng.apigateway.core.executor.result.SessionResult;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname IGenericReference
 * @Description TODO 泛化调用接口
 * @Date 2024/11/3 21:57
 * @Created by gaoyifeng
 */
public interface IGenericReference {

    SessionResult invoke(Map<String, Object> params);

}
