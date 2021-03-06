package kc.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.Role;
import user.User;

@Service
@ComponentScan(basePackages = "Jwt2")
public class Demo implements CommandLineRunner {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
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


        System.out.println(user.getRole().simpleGrantedAuthorities());


        System.out.println(user2.getRole().simpleGrantedAuthorities());

    }
}
