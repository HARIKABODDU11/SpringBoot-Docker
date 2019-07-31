package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

//    @Query(value = "SELECT user FROM User user where firstName=?1")
//    List<User> userByName(String name);
}
