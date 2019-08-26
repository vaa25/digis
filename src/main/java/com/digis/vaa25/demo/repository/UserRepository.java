package com.digis.vaa25.demo.repository;

import com.digis.vaa25.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
