# elasticsearch-simple

elasticsearch-data-jpa-api：
基于data jpa方式使用elasticsearch（springboot目前只能支持到elasticsearch 2.x版本，不支持5.x版本）

elasticsearch-java-api：
基于java client方式使用elasticsearch

elasticsearch-restful-api：
基于restful client方式使用elasticsearch

分两种情况：

1、基于低等级的客户端LowLevelRestClient（RestClient）
    对elasticsearch相关的依赖很少，但对请求响应参数的封装不灵活
    
2、基于高等级的客户端RestHighLevelClient
    对elasticsearch相关的依赖很多，但对请求响应参数的封装灵活
