package com.yamin.e_comm.repository;

import com.yamin.e_comm.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository takes in two parameter - one model, another primary key type
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
