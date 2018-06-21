package jcg.demo.zheng.springbootbatchdemo.config;

import jcg.demo.zheng.springbootbatchdemo.step.SimpleProcessor;
import jcg.demo.zheng.springbootbatchdemo.step.SimpleReader;
import jcg.demo.zheng.springbootbatchdemo.step.SimpleWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class BatchConfig {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  public JobExecutionListener listener;

  @Autowired
  public SimpleAdapterReader simpleAdapter;

  private int STEP_CHUNK_SIZE = 3;

  private static final String SIMPLE_JOB_NAME = "simpleJob";
  private static final String STEP_ONE_NAME = "oneStep";
  private static final String STEP_A_NAME = "adapterStep";

  @Bean
  public Job simpleJob() {
    return jobBuilderFactory.get(SIMPLE_JOB_NAME).incrementer(new RunIdIncrementer()).listener(listener)
        .flow(oneStep()).end().build();
  }

  @Bean
  public Job simpleAdapterJob() {
    return jobBuilderFactory.get("simpleAdapterJob").incrementer(new RunIdIncrementer()).listener(listener)
        .flow(adapterStep()).end().build();
  }

  @Bean
  public Step oneStep() {
    return stepBuilderFactory.get(STEP_ONE_NAME).<String, String>chunk(STEP_CHUNK_SIZE).reader(new SimpleReader())
        .processor(new SimpleProcessor()).writer(new SimpleWriter()).build();
  }

  @Bean
  public Step adapterStep() {
    return stepBuilderFactory.get(STEP_A_NAME).<String, String>chunk(STEP_CHUNK_SIZE).reader(simpleAdaperReader())
        .processor(new SimpleProcessor()).writer(new SimpleWriter()).build();
  }

  @Bean
  public ItemReaderAdapter<String> simpleAdaperReader() {
    ItemReaderAdapter<String> adapter = new ItemReaderAdapter<String>();
    adapter.setTargetObject(simpleAdapter);
    adapter.setTargetMethod("nextItem");
    return adapter;
  }
}
