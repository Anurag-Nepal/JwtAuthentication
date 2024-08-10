package com.anurag.demo.Repository;

import com.anurag.demo.Entity.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
}
