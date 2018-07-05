package com.sptfy.web.app.Repository;

import com.sptfy.web.app.Model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {

    Users findByUsername(String username);

}
