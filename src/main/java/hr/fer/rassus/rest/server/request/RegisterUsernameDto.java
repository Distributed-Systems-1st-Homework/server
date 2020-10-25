package hr.fer.rassus.rest.server.request;

public class RegisterUsernameDto {
    String username;

    public RegisterUsernameDto(String username) {
        this.username = username;
    }

    public RegisterUsernameDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RegisterUsernameDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
