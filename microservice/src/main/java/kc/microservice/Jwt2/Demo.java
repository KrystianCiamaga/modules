package kc.microservice.Jwt2;

import kc.microservice.Role;
import kc.microservice.User;
import kc.microservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Demo implements CommandLineRunner {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public Demo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        User user=new User();
        user.setFirstName("Ala");
        user.setLastName("Nowak");
        user.setEmail("moj@o2.pl");
        user.setLogin("wlasny");
        user.setPassword(passwordEncoder.encode("haslo"));
        user.setRole(Role.CLIENT);
        userRepository.save(user);

        User user2=new User();
        user2.setFirstName("Zbigniew");
        user2.setLastName("Nowak");
        user2.setEmail("moj@o2.pl");
        user2.setLogin("wlasny2");
        user2.setPassword(passwordEncoder.encode("haslo2"));
        user2.setRole(Role.DEVELOPER);
        userRepository.save(user2);



    }
}
