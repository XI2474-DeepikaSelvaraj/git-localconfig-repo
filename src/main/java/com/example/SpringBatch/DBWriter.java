package com.example.SpringBatch;

import com.example.SpringBatch.model.Users;
import com.example.SpringBatch.repository.UsersRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriter implements ItemWriter<Users> {
    @Autowired
    UsersRepository repo;

    @Override
    public void write(List<? extends Users> list) throws Exception {
        repo.saveAll(list);

    }
}
