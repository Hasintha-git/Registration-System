package dto;

import javax.persistence.Entity;
import javax.persistence.Id;

public class LogInDto {
    private String mail;
    private String password;

    public LogInDto(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public LogInDto() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
