# spring-boot-jwt-sample

spring boot整合jwt完成接口授权认证

1、注册用户(POST)

http://localhost:8081/api/v1.0/register

```json
{
  "id": 1,
  "userName": "admin",
  "loginName": "admin",
  "password": "admin",
  "roles": "admin",
  "email": "admin@ameizi.net",
  "location": "xi'an",
  "signature": "admin",
  "createAt": "2018/5/30 10:06",
  "updateAt": "2018/5/30 10:06"
}
```

2、获取token(POST)

http://localhost:8081/api/v1.0/auth/token

```json
{
  "userName": "admin",
  "loginName": "admin",
  "password": "admin"
}
```

3、请求接口(GET)

http://localhost:8081/api/v1.0/users/current

在请求头中添加第二步操作生成的jwt token

在`Header`中添加`Authorization`其值为上一步生成的`token`信息如：`Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6IjEiLCJyb2xlcyI6InVzZXIiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1Mjc2NDYxNjUsImlzcyI6ImFkbWluIiwiZXhwIjoxNTI3NzMyNTY1fQ.5kglv2hGTfmJ9PW61BFiak09NFW2aHRckP3GUdpkPe8W3K7psc0QCVOp7CNbfIB1LEC-JixX1RQCA6ymWyupIw`
