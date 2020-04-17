package com.niraj.csvprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BackToBackPatternClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author min
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @PostConstruct
  public void getDbManager() {
    System.setProperty("java.awt.headless", "false");
    DatabaseManagerSwing.main(

        new String[]{"--url", "jdbc:hsqldb:mem:db/test", "--user", "sa", "--password", ""});
  }

  // tag::readerwriterprocessor[]
  @Bean
  public FlatFileItemReader<Person> reader() {
    return new FlatFileItemReaderBuilder<Person>()
        .name("personItemReader")
        .resource(new ClassPathResource("sample-data.csv"))
        .delimited()
        .names(new String[]{"firstName", "lastName", "age"})
        .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
          setTargetType(Person.class);
        }})
        .build();
  }

  @Bean
  public FirstNameProcessor firstNameProcessor() {
    return new FirstNameProcessor();
  }

  @Bean
  public LastNameProcessor lastNameProcessor() {
    return new LastNameProcessor();
  }

  @Bean
  public AgeProcessor ageProcessor() {
    return new AgeProcessor();
  }

  @Bean
  public ValidationProcessor validationProcessor() {
    return new ValidationProcessor();
  }


  @Bean
  public ItemWriter<Person> itemWriter(DataSource dataSource) {
    BackToBackPatternClassifier classifier = new BackToBackPatternClassifier();
    classifier.setRouterDelegate(new AggGroupClassifier());
    classifier.setMatcherMap(new HashMap<String, ItemWriter<? extends Person>>() {
      {
        put("TEENAGER", teenAgeWriter(dataSource));
        put("YOUNGADULT", youngAdultWriter(dataSource));
        put("SENIORCITIZENS", seniorCitizensWriter(dataSource));

      }
    });
    ClassifierCompositeItemWriter<Person> writer = new ClassifierCompositeItemWriter<Person>();
    writer.setClassifier(classifier);
    return writer;
  }


  @Bean
  public JdbcBatchItemWriter<Person> teenAgeWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Person>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO TEENAGER (first_name, last_name) VALUES (:firstName, :lastName)")
        .dataSource(dataSource)
        .build();
  }

  @Bean
  public JdbcBatchItemWriter<Person> youngAdultWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Person>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO YOUNGADULT (first_name, last_name) VALUES (:firstName, :lastName)")
        .dataSource(dataSource)
        .build();
  }

  @Bean
  public JdbcBatchItemWriter<Person> seniorCitizensWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Person>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO SENIORCITIZENS (first_name, last_name) VALUES (:firstName, :lastName)")
        .dataSource(dataSource)
        .build();
  }
  // end::readerwriterprocessor[]

  // tag::jobstep[]
  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step businessProcessingOne(DataSource dataSource) {
    return stepBuilderFactory.get("businessProcessingOne")
        .<Person, Person>chunk(10)
        .reader(reader())
        .processor(getProcessor())
        .writer(itemWriter(dataSource))
        .build();
  }

  public CompositeItemProcessor<Person, Person> getProcessor() {
    CompositeItemProcessor<Person, Person> compositeItemProcessor = new CompositeItemProcessor<>();
    List<ItemProcessor<Person, Person>> list = new ArrayList<>();
    list.add(validationProcessor());
    list.add(firstNameProcessor());
    list.add(lastNameProcessor());
    list.add(ageProcessor());
    compositeItemProcessor.setDelegates(list);
    return compositeItemProcessor;
  }

  // end::jobstep[]
}
