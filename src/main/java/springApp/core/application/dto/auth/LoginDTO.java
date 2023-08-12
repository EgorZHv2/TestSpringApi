package springApp.core.application.dto.auth;

import org.apache.juli.logging.Log;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    public LoginDTO(){}
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private String phoneNumber;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
