package com.hyn.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.hyn.utils.IdGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author wuzg
 * @version 1.0.0
 * @description mybatis-plus配置类
 * @date 2022/2/15
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * @description mybatis-plus分页插件
     * @author wuzg
     * @date 2022/2/15 17:26
     * @return: PaginationInterceptor
     * @throws
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return plusInterceptor;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setMetaObjectHandler(myMetaObjectHandler());
        return config;
    }

    /**
     * @description 自定义元数据处理
     * @author wuzg
     * @date 2022/3/15 16:01
     * @return: com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
     * @throws
     */
    @Bean
    public MetaObjectHandler myMetaObjectHandler()  {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
                this.strictInsertFill(metaObject,"lastModifyTime", Date.class,new Date());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject,"lastModifyTime", Date.class,new Date());
            }
        };
    }

    @Bean
    public IdentifierGenerator identifierGenerator() {
        return idGenerator -> {
            // 填充自己的Id生成器
            return IdGenerator.generateId();
        };
    }
}
