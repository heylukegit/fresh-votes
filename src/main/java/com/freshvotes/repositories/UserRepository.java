package com.freshvotes.repositories;

import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    It's really wizard stuff here.
    We only need to name the method by correct attributes that we have in our
    table, Spring will do the rest work for us.
     */
    User findByUsername(String username);

}
