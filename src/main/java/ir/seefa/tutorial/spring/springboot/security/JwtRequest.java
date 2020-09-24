package ir.seefa.tutorial.spring.springboot.security;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 24 Sep 2020 T 04:44:03
 */
public class JwtRequest {
    private String username;
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
