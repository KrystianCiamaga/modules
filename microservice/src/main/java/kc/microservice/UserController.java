package kc.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;




    @Secured("ROLE_CLIENT")
    @GetMapping("/show")
    public String testowa(){
        return "testowy endpoint";
    }


    @PostMapping
    public String register(@RequestBody User userr ){

        User user = userRepository.findByLogin(userr.getLogin());

        if (user == null) {
            userService.addUser(userr);

            return "Added correctly";
        }
        return "Account already exist";
    }



    }



