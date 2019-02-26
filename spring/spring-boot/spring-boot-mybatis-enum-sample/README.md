## ǰ��
>����յ����ں�����˵��������`Mybatis`�ļ��ɺ�ʹ�á�ǰ���ڵھ��£�[Mybatis-plus�ļ��ɺ�ʹ��](http://blog.lqdev.cn/2018/07/21/springboot/chapter-nine/ "Mybatis-plus�ļ��ɺ�ʹ��")�����˻���`mybatis-plus`�ļ��ɺ�ʹ�á�����Ҳֻ�Ƕ�`mybatis`�����˹�����ǿ��ԭ�����÷�����û�б仯�ġ��ǽ�������򵥽��������`springboot`����μ��ɺ�ʹ��`Mybatis`�ɡ�

*   [SpringBoot�ļ��ɺ�ʹ��](#SpringBoot�ļ��ɺ�ʹ��)
    *   [ͨ������](#ͨ������)
    *   [ע�ⷽʽ](#ע�ⷽʽ)
    *   [xml��ʽ](#xml��ʽ)
    *   [ö�����ʹ���������](#ö�����ʹ���������)
*   [�ο�����](#�ο�����)
*   [�ܽ�](#�ܽ�)
*   [���](#���)
*   [������̸](#������̸)

## SpringBoot�ļ��ɺ�ʹ��
>`MyBatis`��һ������ĳ־ò��ܣ���֧�ֶ��ƻ�SQL���洢�����Լ��߼�ӳ�䡣`MyBatis` �����˼������е� JDBC ������ֶ����ò����Լ���ȡ�������**`MyBatis`����ʹ�ü򵥵�`XML��`ע�������ú�ӳ��ԭ����Ϣ�����ӿں�Java��POJOs(Plain Old Java Objects,��ͨ�� Java����)ӳ������ݿ��еļ�**¼��

������ܻ���`xml`��`ע��`���ַ�ʽ�������á�ͬʱʹ��`mybatis-spring-boot-starter`���м��ɡ�


>����ѡ�õ�**mybatis-spring-boot-starter**�汾Ϊ��`1.3.2`��
��Ӧ**Mybatis**�汾Ϊ��`3.4.6`

### ͨ������
**���ַ�ʽ������һ�µ�`pom`���ã�**
```xml
<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
```

**������`user`��Ϊ���ӣ����ݿ�Ϊmysql**
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) DEFAULT NULL COMMENT 'Ψһ��ʾ',
  `code` varchar(20) DEFAULT NULL COMMENT '����',
  `name` varchar(64) DEFAULT NULL COMMENT '����',
  `status` char(1) DEFAULT '1' COMMENT '״̬ 1���� 0 ͣ��',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````

**ʵ����`User`Ϊ��**

```java
/**
 * <p>
 * 
 * </p>
 *
 * @author oKong
 * @since 2018-12-02
 */
@Data
@Accessors(chain = true)
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1779270373648636358L;
	/**
     * Ψһ��ʾ
     */
    private Long id;
    /**
     * ����
     */
    private String code;
    /**
     * ����
     */
    private String name;
    
    /**
     * ״̬1 ���� 0 ͣ��
     */
    private StatusEnum status;
    /**
     * ����ʱ��
     */
    private Date gmtCreate;
    /**
     * �޸�ʱ��
     */
    private Date gmtModified;
}
```

**״̬ö����`StatusEnum`��**

```java
public enum StatusEnum {
	
	DISABLE,
	ENABLE;

}
```

**�����ļ���application.properties**
```

# ʵ�����
mybatis.type-aliases-package=cn.lqdev.learning.springboot.chapter35.biz.entity

# ���ݿ�����
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/learning?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=

spring.profiles.active=anno
```

**������**

```java
/**
 * mybaits����
 * @author oKong
 *
 */
@SpringBootApplication
@Slf4j
public class MybatisApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MybatisApplication.class, args);
		log.info("spring-boot-mybatis-chapter35����!");
	}
}
```

### ע�ⷽʽ
0.����ע����mapper��

`UserMapper.java`

```java
/**
 * ע������
 * @author okong
 *
 */
public interface UserMapper {
    //���÷��ص��ֶ����ͣ����������˴������ں��޸������Զ�
	@Select("select * from user where id = #{id}")
	@Results({
		@Result(column = "gmt_create",property = "gmtCreate",jdbcType=JdbcType.DATE),
		@Result(column = "gmt_modified",property = "gmtModified",jdbcType=JdbcType.DATE)
	})
	User queryOne(Long id);
	
	// ö���� Ĭ����ʹ�� EnumTypeHandler �����࣬��ʹ��ö��name��Ϊֵ
	//status Ϊö���� Ҳ����ֱ��ָ���� typeHandler�� ��Ϊ������ ���磺#{status,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}
	//��������sqlFactory ֱ��ʹ��  TypeHandlerRegistry  ����ע�� �꿴��MybatisConfig ��
	//��򵥣��Զ��� ConfigurationCustomizer �˽������� �꿴��MybatisConfig ��
	@Insert("insert into user(code,name,status) values(#{code},#{name}, #{status})")
	//�������û��user�������id��ֵ
    @Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)
	int insert(User user);
	
	@Update("update user set code=#{code}, name = #{name}, status = #{status} where id=#{id}")
	void update(User user);
	
	@Delete("delete from user where id=#{id}")
	void delete(Long id);
	
	@Select("select * from user where code = #{code}")
	@Results({
		@Result(column = "gmt_create",property = "gmtCreate",jdbcType=JdbcType.DATE),
		@Result(column = "gmt_modified",property = "gmtModified",jdbcType=JdbcType.DATE)
	})	
	List<User> queryByParams(@Param("code")String code);
}
```
�򵥶��������ע�����˵���£�

*   @Select �ǲ�ѯ���ע�⣬���еĲ�ѯ��ʹ�����
*   @Result ���η��صĽ����������ʵ�������Ժ����ݿ��ֶ�һһ��Ӧ�����ʵ�������Ժ����ݿ�����������һ�£��Ͳ���Ҫ������������Ρ�
*   @Insert �������ݿ�ʹ�ã�ֱ�Ӵ���ʵ������Զ��������Ե���Ӧ��ֵ
*   @Update �����޸ģ�Ҳ����ֱ�Ӵ������
*   @delete ����ɾ��
*   @Options ӳ���������ԣ�������ʱ��Ҫ����������IDʱ:`@Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)`

����Ŀ���ȥ�������ģ�[http://www.mybatis.org/mybatis-3/zh/java-api.html](http://www.mybatis.org/mybatis-3/zh/java-api.html "http://www.mybatis.org/mybatis-3/zh/java-api.html")

![ӳ����ע��](http://qiniu.xds123.cn/18-12-2/10195302.jpg)

2.ָ��mapperɨ���·����ʹ��ע��`@MapperScan`
```java
/**
 * mybaits����
 * @author oKong
 *
 */
@Configuration
@MapperScan("cn.lqdev.learning.springboot.chapter35.biz.mapper")//mapper��ַ
public class MybatisConfig {
     
}
```
ע�⣺��ʹ��`Druid`�����������ӳع���Ҳ�����ڴ����н���`DataSource`��������á�

3.��д��������в��ԡ�
```java
/**
 * ������
 * @author oKong
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("anno")
@Slf4j
public class UserMapperTest {
	
	@Autowired
	UserMapper userMpper;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setCode("002");
		user.setName("name002");
		user.setStatus(StatusEnum.ENABLE);
		
		//����
		userMpper.insert(user);
	}
	
	@Test
	public void testQueryOne() {
		User user = userMpper.queryOne(1L);
		log.info("idΪ1�Ĳ�ѯ���Ϊ��{}", user);
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setCode("002");
		user.setName("testUpdate");
		user.setStatus(StatusEnum.ENABLE);
		userMpper.insert(user);
		
		User userUpd = userMpper.queryOne(user.getId());
		userUpd.setName("����name");
		userMpper.update(userUpd);
		
		Assert.assertEquals("����ʧ��",userUpd.getName(), userMpper.queryOne(user.getId()).getName());
	}
	
	@Test
	public void testParamSelect() {
		String code = "002";
		List<User> list = userMpper.queryByParams(code);
		
		log.info("��ѯ����Ϊ002,��ѯ���Ϊ��{}��,�����Ϊ��{}",list.size(), Arrays.toString(list.toArray()));
	}

}
```

���в��������󣬾Ϳ��Կ���Ч���ˡ�

![](http://qiniu.xds123.cn/18-12-2/55568488.jpg)

�������̨���������ˣ�������Դ�����в����¡�

### xml��ʽ

0.����xml���mapper��
```java
/**
 * xmlӳ��
 * @author oKong
 *
 */
public interface UserXmlMapper {

	User queryOne(Long id);
	
	int insert(User user);
	
	void update(User user);
	
	void delete(Long id);
		
	List<User> queryByParams(@Param("code")String code);
}
```

ûɶ���𣬾��ǽ�sql�����뵽��xml�н��б�д���ѡ�

1.��дmapper.xml����ӳ���ļ���

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.lqdev.learning.springboot.chapter35.biz.mapper.UserXmlMapper">
   
	<!-- ���÷������� -->
   <resultMap type="User" id="userResultMap">
      <result column="id" property="id"/>
      <result column="code" property="code"/>
      <result column="name" property="name"/>
      <result column="status" property="status"/>
      <result column="gmt_create" property="gmtCreate" jdbcType="DATE"/>
      <result column="gmt_modified" property="gmtModified" jdbcType="DATE"/>
   
   </resultMap>

    <!-- ͨ�ò�ѯ����� -->
    <sql id="Base_Column_List">
        id, code, name, status, gmt_create, gmt_modified
    </sql>
    
    <select id="queryOne" resultMap="userResultMap">
      select 
      <include refid="Base_Column_List"></include>
      from user
      where id = #{id}
    </select>

    <!-- ��������id -->
    <insert id="insert" parameterType="User" keyProperty="id" useGeneratedKeys="true">
        insert into user(code,name,status) values(#{code},#{name}, #{status})
        <!--  insert into user(code,name,status) values(#{code},#{name}, #{status, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}) -->
    </insert>
    
    <update id="update"  parameterType="User">
       update user set code=#{code}, name = #{name}, status = #{status} where id=#{id}
    </update>
    
    <delete id="delete">
       delete from user where id=#{id}
    </delete>
    
    <select id="queryByParams" resultMap="userResultMap">
      select 
      <include refid="Base_Column_List"></include>
      from user
       where code = #{code}
    </select>
</mapper>
```
2.mybatis�����ļ���

`mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--ȫ������-->
    <settings>
        <!-- �������ʹȫ�ֵ�ӳ�������û���û��� -->
        <setting name="cacheEnabled" value="true"/>
        <!-- ȫ�����û�����ӳټ��ء�������ʱ�����й������󶼻ἴʱ���� -->
        <setting name="lazyLoadingEnabled" value="false"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="defaultExecutorType" value="REUSE"/>
        <setting name="defaultStatementTimeout" value="25000"/>
        <setting name="aggressiveLazyLoading" value="true"/>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    <typeHandlers>
       <!-- ö���� -->
       <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
         javaType="cn.lqdev.learning.springboot.chapter35.biz.entity.StatusEnum"/>
    </typeHandlers>
    
</configuration>
```

������������ͣ�����ͨ��`typeHandlers`�������á��Ժ��½�Ҳ�ὲ����ͨ�������ķ�ʽ�������á�

3.����xml��ʽ�����ļ���`application-xml.properties`������xml��config��·����ַ

```
# ����mapper.xml��mybatis-config.xml·��
mybatis.config-location=classpath:mybatis/mybatis-config.xml
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

4.��д�����ࣺ`UserXmlMapperTest.java`
�����`UserMapperTest`���Ƶģ�Ψһ��������ƶ������л�������Ϊ��xml
```java
@ActiveProfiles("xml")
```


### ö�����ʹ���������
>��ʵ�����������������ö������:`StatusEnum`����`mybatis`�ж���ö�ٵ�Ĭ����������`EnumTypeHandler`��������д���ģ����Ĭ��ʹ��`name`���и�ֵ��ͬʱ`mybatis`���ṩ��һ��`EnumOrdinalTypeHandler`�����࣬���Ǹ���ö�ٵ�����ֵ���и�ֵ�ġ�

ע�����ʹ������кܶ��з�ʽ����ÿһ�ַ�ʽ�����ͨ��`TypeHandlerRegistry`����д���ģ����ｲ����ͨ�����ַ�ʽ�������á�

- �����ļ����������ԣ�`mybatis.type-handlers-package`�����ô������·����
```
# ���ʹ�����
mybatis.type-handlers-package=cn.lqdev.learning.springboot.chapter35.config
```

������ʾ����`CustomEnumOrdinalTypeHandler.java`������ֱ�Ӽ̳�`EnumOrdinalTypeHandler`�����Զ��塣

```java
/**
 * 
 * @author oKong
 *
 */
//ö������������
@MappedTypes(value = { StatusEnum.class })
public class CustomEnumOrdinalTypeHandler<E extends Enum<E>> extends EnumOrdinalTypeHandler<E>{

	public CustomEnumOrdinalTypeHandler(Class<E> type) {
		super(type);
	}
}
```
���У�`@MapperType`ָ������Щ����ָ���˴�����ġ�

- �Զ���`ConfigurationCustomizer`��������ã��Ƽ�����
```java
	/**
	 * 
	 * <p>�������ƣ�  ConfigurationCustomizer      </p>
	 * <p>����˵���� �Զ������ע����
	 *
	 * </p>
	 *<p>����˵����</p>
	 * @return
	 *
	 * @date   ����ʱ�䣺2018��12��2��
	 * @author ���ߣ�oKong
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		ConfigurationCustomizer config = new ConfigurationCustomizer() {
			
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
//				TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
				// mapper�ӿ�ע����
//				MapperRegistry mapperRegistry = configuration.getMapperRegistry();
				// ���ʹ�����
				TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
				typeHandlerRegistry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
			}
		};
		
		return config;
	}
```

- ͨ��`SqlSessionFactory`����ȡ`TypeHandlerRegistry`�������á�
```java
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@PostConstruct
	public void registerTypeHandler() {
		TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
		registry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
	}
```

**�������ֶ����Խ���������͵Ĵ��������ã�����ֱ��ʹ�õڶ��֡�**

������`MybatisConfig`�ࣺ

```java
/**
 * mybaits����
 * @author oKong
 *
 */
@Configuration
@MapperScan("cn.lqdev.learning.springboot.chapter35.biz.mapper")//mapper��ַ
public class MybatisConfig {
	
	//ʹ�� SqlSessionFactory ���ȡ TypeHandlerRegistry ����ע��
//	@Autowired
//	SqlSessionFactory sqlSessionFactory;
//	
//	@PostConstruct
//	public void registerTypeHandler() {
//		TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
//		registry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
//	}
	
	/**
	 * 
	 * <p>�������ƣ�  ConfigurationCustomizer      </p>
	 * <p>����˵���� �Զ������ע����
	 *
	 * </p>
	 *<p>����˵����</p>
	 * @return
	 *
	 * @date   ����ʱ�䣺2018��12��2��
	 * @author ���ߣ�oKong
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		ConfigurationCustomizer config = new ConfigurationCustomizer() {
			
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
//				TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
				// mapper�ӿ�ע����
//				MapperRegistry mapperRegistry = configuration.getMapperRegistry();
				// ���ʹ�����
				TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
				typeHandlerRegistry.register(StatusEnum.class, EnumOrdinalTypeHandler.class);
			}
		};
		
		return config;
	}
}
```


## �ο�����
1. [http://www.mybatis.org/mybatis-3/zh/](http://www.mybatis.org/mybatis-3/zh/ "http://www.mybatis.org/mybatis-3/zh/")

2. [http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html "http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html")

## �ܽ�
>���½���Ҫ�򵥽�����`mybatis`���ɵ�����ģʽ������ģʽ�����ص㣬ע����ʺϼ򵥿��ٵ�ģʽ����xml��ʽ�ʺ���Ը��ӵ�sql��䣬д��xml�У����Խ���ͳһ�޸ģ�������Ҫȥ�޸�java���롣����ԭ��ʹ��`mybatis`���ԣ��о�Ҳ�ǱȽϼ򵥵ģ�����д�����Ƚ��鷳������Ҫ�ֶ�ȥ��д�����Ի���ѡ��һ�����ּܰɣ�`mybatis-plus`��һ���ܺõ�ѡ�񡣵�Ȼ�������Ľ��ּܿ���ˣ���ҿ����и���ʵ��������о���ǰ����ȥ����������ʱ�����˾���**���������ͺ����˻�Ҫѧϰ����С���**���Ҿ��ðɣ���ЩС��ܿ��Խ�ʡ���ٷ����Ĺ���ѽ���ÿ�����Ա����רע��ҵ����룬�����õ�һ������ѽ������������û��д������д����Щ���ˣ���ҿ���ֱ������Դ��ʾ�����в鿴�£�ԭ����ֿ��������̽��н��⣬�о�Ҳû�б�Ҫ���ͺϲ���һ���ˣ�ͨ����ͬ�Ļ������ý����л��������½�ѽ��

## ���
>Ŀǰ�������Ϻܶ���ж���`SpringBoot`ϵ�н̳̣�������ͬ����������ˡ�**ԭ�����ף����ֲ���**����ϣ����Ҷ��֧�֡���������������֮�������������лл��

## ������̸
- ����QQ��`499452441`
- ΢�Ź��ںţ�`lqdevOps`

![���ں�](http://qiniu.xds123.cn/default/wxgzh8cm.jpg)

���˲��ͣ�[http://blog.lqdev.cn](http://blog.lqdev.cn "http://blog.lqdev.cn")

����ʾ����[https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35](https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35 "https://github.com/xie19900123/spring-boot-learning/tree/master/chapter-35")

