package com.example.SpringBatch.Controller;

import com.example.SpringBatch.model.Users;
import com.example.SpringBatch.repository.UsersRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class UserController {


    @Autowired
    UsersRepository repo;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @RequestMapping("/invokejob")
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";
    }
    @GetMapping("api/v1/get")
    public List<Users>getall(){
        List<Users>list=repo.findAll();
        return list;
    }
}
