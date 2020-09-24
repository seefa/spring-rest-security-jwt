package ir.seefa.tutorial.spring.springboot.model;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 24 Sep 2020 T 05:59:44
 */
public class UserDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
