package com.gaoyifeng.apigateway.binding;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname IGenericReference
 * @Description TODO 泛化调用接口
 * @Date 2024/11/3 21:57
 * @Created by gaoyifeng
 */
public interface IGenericReference {

    Object invoke(Map<String, Object> params);

}
