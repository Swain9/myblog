package com.maolin.jpa.repository;

import com.maolin.jpa.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 45022
 * @since 2017/11/8 19:55
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
