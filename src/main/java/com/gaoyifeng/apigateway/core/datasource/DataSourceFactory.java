package com.gaoyifeng.apigateway.core.datasource;

import com.gaoyifeng.apigateway.core.session.Configuration;

/**
 * @author gaoyifeng
 * @Classname DataSourceFactory
 * @Description TODO
 * @Date 2024/11/15 19:25
 * @Created by gaoyifeng
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
