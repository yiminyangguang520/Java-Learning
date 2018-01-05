Annotate with **@Component** to indicate this is class is an auto scan component.

DAO layer, add **@Component** to indicate this is an auto scan component also.

## Custom auto scan component name

By default, Spring will lower case the first character of the component – from ‘CustomerService’ to ‘customerService’. And you can retrieve this component with name ‘customerService’

## Auto Components Scan Annotation Types

In Spring 2.5, there are 4 types of auto components scan annotation types

- @Component – Indicates a auto scan component.
- @Repository – Indicates DAO component in the persistence layer.
- @Service – Indicates a Service component in the business layer.
- @Controller – Indicates a controller component in the presentation layer.

So, which one to use? It’s really doesn’t matter. Let see the source code of `@Repository`,`@Service` or `@Controller`.

```
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {

	String value() default "";

}
```

You will noticed that all `@Repository`,`@Service` or `@Controller` are annotated with `@Component`. So, can we use just @Component for all the components for auto scanning? Yes, you can, and Spring will auto scan all your components with @Component annotated.

It’s working fine, but not a good practice, for readability, you should always declare @Repository,@Service or @Controller for a specified layer to make your code more easier to read, as following :