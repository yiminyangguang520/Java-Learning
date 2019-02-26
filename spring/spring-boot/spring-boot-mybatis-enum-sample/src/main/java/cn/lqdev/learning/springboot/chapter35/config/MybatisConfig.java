package cn.lqdev.learning.springboot.chapter35.config;

import cn.lqdev.learning.springboot.chapter35.biz.entity.StatusEnum;
import javax.annotation.PostConstruct;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybaits配置
 *
 * @author litz-a
 */
@Configuration
@MapperScan("cn.lqdev.learning.springboot.chapter35.biz.mapper")
public class MybatisConfig {

  /**
   * 使用 SqlSessionFactory 类获取 TypeHandlerRegistry 进行注册
   */
  /*
  @Autowired
  SqlSessionFactory sqlSessionFactory;

  @PostConstruct
  public void registerTypeHandler() {
    TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
    registry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
  }
  */


  /**
   * <p>函数名称：  ConfigurationCustomizer      </p>
   * <p>功能说明： 自定义相关注册器
   *
   * </p>
   * <p>参数说明：</p>
   *
   * @date 创建时间：2018年12月2日
   * @author 作者：oKong
   */
  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    ConfigurationCustomizer config = configuration -> {
      //TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
      // mapper接口注册器
      //MapperRegistry mapperRegistry = configuration.getMapperRegistry();
      // 类型处理器
      TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
      typeHandlerRegistry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
    };

    return config;
  }
}
