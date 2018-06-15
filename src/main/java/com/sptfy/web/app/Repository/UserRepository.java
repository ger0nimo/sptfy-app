package com.sptfy.web.app.Repository;

import com.sptfy.web.app.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


}
