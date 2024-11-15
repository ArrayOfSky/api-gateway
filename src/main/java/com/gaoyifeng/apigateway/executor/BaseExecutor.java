package com.gaoyifeng.apigateway.executor;

import com.alibaba.fastjson.JSON;
import com.gaoyifeng.apigateway.datasource.Connection;
import com.gaoyifeng.apigateway.executor.result.GatewayResult;
import com.gaoyifeng.apigateway.mapping.HttpStatement;
import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.type.SimpleTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname BaseExecutor
 * @Description TODO
 * @Date 2024/11/15 20:28
 * @Created by gaoyifeng
 */
public abstract class BaseExecutor implements Executor {

    private Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception {
        // 参数处理；后续的一些参数校验也可以在这里封装。
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};
        logger.info("执行调用 method：{}#{}.{}({}) args：{}", httpStatement.getApplication(), httpStatement.getInterfaceName(), httpStatement.getMethodName(), JSON.toJSONString(parameterTypes), JSON.toJSONString(args));
        // 抽象方法
        try {
            Object data = doExec(methodName, parameterTypes, args);
            return GatewayResult.buildSuccess(data);
        } catch (Exception e) {
            return GatewayResult.buildError(e.getMessage());
        }
    }


    protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);

}
