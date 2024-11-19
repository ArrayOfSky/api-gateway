package com.gaoyifeng.apigateway.core.datasource.unpooled;

import com.gaoyifeng.apigateway.core.datasource.DataSource;
import com.gaoyifeng.apigateway.core.datasource.DataSourceFactory;
import com.gaoyifeng.apigateway.core.datasource.DataSourceType;
import com.gaoyifeng.apigateway.core.session.Configuration;

/**
 * @author gaoyifeng
 * @Classname UnpooledDataSourceFactory
 * @Description TODO
 * @Date 2024/11/15 19:38
 * @Created by gaoyifeng
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        // 默认dubbo
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));

    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
