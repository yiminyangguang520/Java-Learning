package demo;

import demo.model.Authority;
import demo.repository.AuthorityRepository;
import demo.model.User;
import demo.repository.UserRepository;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author min
 */
@EnableJpaRepositories(basePackages="demo.repository")
@EntityScan("demo.model")
@SpringBootApplication
public class DemoApplication {

  @Autowired
  DataSource dataSource;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    UserRepository userRepository = context.getBean(UserRepository.class);
    AuthorityRepository authorityRepository = context.getBean(AuthorityRepository.class);

    Authority adminAuthority = getOrGreateAuthority("ROLE_ADMIN", authorityRepository);
    Authority basicAuthority = getOrGreateAuthority("ROLE_BASIC", authorityRepository);

    User admin = new User("admin", "123456");
    encodePassword(admin);
    admin.getAuthorities().add(adminAuthority);
    admin.getAuthorities().add(basicAuthority);

    User test = new User("test", "test");
    encodePassword(test);
    test.getAuthorities().add(basicAuthority);

    userRepository.save(admin);
    userRepository.save(test);
  }

  private static void encodePassword(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
  }

  private static Authority getOrGreateAuthority(String authorityText, AuthorityRepository repository) {
    Authority authority = repository.findByAuthority(authorityText);
    if (authority == null) {
      authority = new Authority(authorityText);
      repository.save(authority);
    }
    return authority;
  }

//  @Bean
//  public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
//    return entityManagerFactory.unwrap(SessionFactory.class);
//  }
//
//  @Bean
//  public MessageSource messageSource() {
//    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setBasename("classpath:org/springframework/security/messages");
//
//    ReloadableResourceBundleMessageSource messageSourceLocal = new ReloadableResourceBundleMessageSource();
//    messageSourceLocal.setBasename("classpath:messages");
//    messageSourceLocal.setParentMessageSource(messageSource);
//
//    return messageSourceLocal;
//  }

//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//    //JpaVendorAdapteradapter can be autowired as well if it's configured in application properties.
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setGenerateDdl(false);
//
//    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//    factory.setJpaVendorAdapter(vendorAdapter);
//    //Add package to scan for entities.
//    factory.setPackagesToScan("demo.model");
//    factory.setDataSource(dataSource);
//    return factory;
//  }
//
//  @Bean
//  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//    JpaTransactionManager txManager = new JpaTransactionManager();
//    txManager.setEntityManagerFactory(entityManagerFactory);
//    return txManager;
//  }
}
