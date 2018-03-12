package com.ashu.demo.repository;

import com.ashu.demo.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {

    AppUser findByUsername(String username);


}
