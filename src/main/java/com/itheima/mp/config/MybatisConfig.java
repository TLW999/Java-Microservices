package com.itheima.mp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    //添加mybatis plus的分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor myBatisPlusInterceptor = new MybatisPlusInterceptor();

        //添加分页拦截器
        myBatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return myBatisPlusInterceptor;
    }
}
