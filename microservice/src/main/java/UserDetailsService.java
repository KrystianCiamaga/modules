import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

       User user=userRepository.findByLogin(s);

        if(user == null){

            throw new UsernameNotFoundException("invalid username or password");
        }

        return new UserDetail(user);
    }
}
