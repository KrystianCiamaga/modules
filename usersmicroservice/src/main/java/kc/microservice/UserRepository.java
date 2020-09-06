package kc.microservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.User;

import javax.persistence.Entity;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    User findByLogin(String s);


}
