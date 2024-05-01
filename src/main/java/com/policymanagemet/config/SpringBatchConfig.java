package com.policymanagemet.config;
import com.policymanagemet.dto.PolicyDto;
import com.policymanagemet.model.Policy;
import com.policymanagemet.repository.PolicyRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private PolicyRepository policyRepository;

    @Bean
    public FlatFileItemReader<PolicyDto> policydtoReader() {
        FlatFileItemReader<PolicyDto> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:\\Users\\kotar\\OneDrive\\Desktop\\policy.csv"));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<PolicyDto> lineMapper() {

        DefaultLineMapper<PolicyDto> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("policyHolderName", "policyType", "email");

        BeanWrapperFieldSetMapper<PolicyDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PolicyDto.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public ItemProcessor policyDtoProcessor() {
        return new PolicyDtoProcesser();
    }

    @Bean
    public RepositoryItemWriter<Policy> policyWriter() {

        RepositoryItemWriter<Policy> writer = new RepositoryItemWriter<>();

        writer.setRepository(policyRepository);
        writer.setMethodName("save");

        return writer;
    }


    @Bean
    public Step step() {
        return stepBuilderFactory.get("step-1").<PolicyDto,Policy>chunk(10)
                .reader(policydtoReader())
                .processor(policyDtoProcessor())
                .writer(policyWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("customers-import")
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }


}






