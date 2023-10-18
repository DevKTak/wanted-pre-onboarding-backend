package com.wanted.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wanted.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
