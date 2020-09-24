package ir.seefa.tutorial.spring.springboot.security;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 24 Sep 2020 T 04:46:22
 */
public class JwtResponse {

    private String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
