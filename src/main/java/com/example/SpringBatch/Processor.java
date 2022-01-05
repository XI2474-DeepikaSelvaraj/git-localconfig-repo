package com.example.SpringBatch;

import com.example.SpringBatch.model.Users;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;


public class Processor implements ItemProcessor<Users,Users> {

   private static final HashMap<String,String>hmap=new HashMap<>();

   public Processor(){
       hmap.put("001","CSE");
       hmap.put("002","ECE");
       hmap.put("003","IT");
   }

    @Override
    public Users process(Users users) throws Exception {
       String dept=users.getDepartment();
       String deptname=hmap.get(dept);
       users.setDepartment(deptname);
        return users;
    }
}
