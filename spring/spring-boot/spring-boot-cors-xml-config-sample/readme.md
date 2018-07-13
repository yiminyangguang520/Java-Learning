– **origins**: specifies the URI that can be accessed by resource. “*” means that all origins are allowed. If undefined, all origins are allowed.

– **allowCredentials**: defines the value for **Access-Control-Allow-Credentials** response header. If value is true, response to the request can be exposed to the page. The credentials are cookies, authorization headers or TLS client certificates. The default value is **true**.

– **maxAge**: defines maximum age (in seconds) for cache to be alive for a pre-flight request. By default, its value is 1800 seconds.

We also have some attributes:
– **methods**: specifies methods (GET, POST,…) to allow when accessing the resource. If we don’t use this attribute, it takes the value of `@RequestMapping` method by default. If we specify methods attribute value in `@CrossOrigin` annotation, default method will be overridden.

– **allowedHeaders**: defines the values for **Access-Control-Allow-Headers** response header. We don’t need to list headers if it is one of *Cache-Control*, *Content-Language*, *Expires*, *Last-Modified*, or *Pragma*. By default all requested headers are allowed.

– **exposedHeaders**: values for **Access-Control-Expose-Headers** response header. Server uses it to tell the browser about its whitelist headers. By default, an empty exposed header list is used.



If we use `@CrossOrigin` annotation on the Controller, all CORS Configuration of methods inside will be enabled. 



– Config maven build:
`clean install`
– Run project with mode **Spring Boot App** and port **8081**.
– Create Client Application (stored in folder **webapps/Cors** of **Apache Tomcat**):

– Deploy client project on Tomcat with port **8080**:

Send Request on Browser:
`http://localhost:8080/Cors/index.html`
Result:

```
Id: 1, Name: Jack
Id: 2, Name: Adam
Id: 3, Name: Kim
```

Clear Browser Cache, then modify **data.js** file by changing **url** to:

```
$.ajax({
	url: "http://localhost:8081/data"
})
```

Send Request on Browser:
`http://localhost:8080/Cors/index.html`
Result: **Browser shows nothing**.

– Deploy client project on Tomcat with port **9000**:

Clear Browser Cache, then send Request on Browser:
`http://localhost:9000/Cors/index.html`
Result:

```
Id: 1, Name: JACK
Id: 2, Name: ADAM
Id: 3, Name: KIM
```

