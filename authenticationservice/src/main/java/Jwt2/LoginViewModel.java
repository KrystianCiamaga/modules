package Jwt2;

import lombok.Setter;

@Setter
public class LoginViewModel {


    private String username;
    private String password;

    public LoginViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
