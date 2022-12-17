package site.nomoreparties.stellarburgers.api;

public class Token {
    private String token; //refreshToken

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
