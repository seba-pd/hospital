package com.sebapd.hospital.repository;

import com.sebapd.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseUserRepository<T extends User> extends JpaRepository<T, Long> {
}
