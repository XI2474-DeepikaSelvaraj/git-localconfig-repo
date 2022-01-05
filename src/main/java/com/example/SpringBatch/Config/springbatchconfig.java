package com.example.SpringBatch.Config;

import com.example.SpringBatch.model.Users;
import org.apache.catalina.User;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


@Configuration
public class springbatchconfig {

    @Bean
    public Job job(JobBuilderFactory factory, StepBuilderFactory stepBuilderFactory, ItemReader<Users>itemReader, ItemProcessor<Users,Users>itemProcessor, ItemWriter<Users>itemWriter){

        Step step=stepBuilderFactory.get("File-upload")
                .<Users,Users>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
       Job job= factory.get("Demo-Batch")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
       return job;
    }

    @Bean
    public FlatFileItemReader<Users>flatFileItemReader(){
        FlatFileItemReader<Users> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/users.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Users>lineMapper(){
        DefaultLineMapper<Users> defaultLineMapper=new DefaultLineMapper<Users>();
        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[]{"id","name","department","salary"});
        BeanWrapperFieldSetMapper<Users> wrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
        wrapperFieldSetMapper.setTargetType(Users.class);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(wrapperFieldSetMapper);
        return  defaultLineMapper;
    }
}
