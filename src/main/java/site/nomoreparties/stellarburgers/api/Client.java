package site.nomoreparties.stellarburgers.api;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Client {
    private BaseApiClient client = new BaseApiClient();
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";
    private final String INGREDIENTS_ENDPOINT = "ingredients";
    private final String ORDERS_ENDPOINT = "orders";
    private final String ORDERS_ALL_ENDPOINT = "orders/all";
    private final String USER_ENDPOINT = "auth/user";
    private final String REGISTER_ENDPOINT = "auth/register";
    private final String AUTH_ENDPOINT = "auth/login";
    private final String LOGOUT_ENDPOINT = "auth/logout";
    private final String TOKEN_ENDPOINT = "auth/token";
    private final String PASSWORD_RESET_ENDPOINT = "site/nomoreparties/stellarburgers/api/password-reset";

    public Response getIngredients() {
        return client.getRequest(BASE_URL + INGREDIENTS_ENDPOINT);
    }

    public Response createOrder(IngredientsHashList ingredientsHashList) {
        return client.postRequest(BASE_URL + ORDERS_ENDPOINT, ingredientsHashList);
    }

    public Response createOrder(IngredientsHashList ingredientsHashList, String authToken) {
        return client.postRequest(BASE_URL + ORDERS_ENDPOINT, authToken, ingredientsHashList);
    }

    public Response createUser(User user) {
        return client.postRequest(BASE_URL + REGISTER_ENDPOINT, user);
    }

    public Response loginUser(User user) {
        return client.postRequest(BASE_URL + AUTH_ENDPOINT, user);
    }

    public Response getUserInfo(String auth) {
        return client.getRequest(BASE_URL + USER_ENDPOINT, auth);
    }

    public Response updateUserInfo(String auth, User user) {
        return client.patchRequest(BASE_URL + USER_ENDPOINT, auth, user);
    }

    public Response updateUserInfo(User user) {
        return client.patchRequest(BASE_URL + USER_ENDPOINT, user);
    }

    public Response logoutUser(Token token) {
        return client.postRequest(BASE_URL + LOGOUT_ENDPOINT, token);
    }

    public Response updateToken(User user) {
        return client.postRequest(BASE_URL + TOKEN_ENDPOINT, user);
    }


    public Response deleteUser(String auth) {
        return client.deleteRequest(BASE_URL + TOKEN_ENDPOINT, auth);
    }

    public Response getAllOrders() {
        return client.getRequest(BASE_URL + ORDERS_ALL_ENDPOINT);
    }

    public Response getOrdersByUser(String auth) {
        return client.getRequest(BASE_URL + ORDERS_ENDPOINT, auth);
    }

    public Response getOrdersByUser() {
        return client.getRequest(BASE_URL + ORDERS_ENDPOINT);
    }

    protected class BaseApiClient {
        private final RestAssuredConfig config = RestAssuredConfig.newConfig()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation())
                .redirect(new RedirectConfig().followRedirects(true));

        protected Response getRequest(String uri) {
            return given().config(config).get(uri);
        }

        protected Response getRequest(String uri, String token) {
            Header authHeader = new Header("Authorization", token);
            return given().header(authHeader).config(config).get(uri);
        }

        protected Response getRequest(String uri, HashMap<String, Object> params) {
            return given().config(config).queryParams(params).get(uri);
        }

        protected Response postRequest(String uri, Object payload) {
            Header contentHeader = new Header("Content-Type", "application/json");
            return given().config(config)
                    .header(contentHeader)
                    .body(payload)
                    .when()
                    .post(uri);
        }

        protected Response postRequest(String uri, String token, Object payload) {
            Header authHeader = new Header("Authorization", token);
            Header contentHeader = new Header("Content-Type", "application/json");
            return given().config(config)
                    .header(authHeader)
                    .header(contentHeader)
                    .body(payload)
                    .when()
                    .post(uri);
        }

        protected Response deleteRequest(String uri) {
            return given().config(config)
                    .delete(uri);
        }

        protected  Response putRequest(String uri, Object payload) {
            return given().config(config)
                    .header("Content-Type", "application/json")
                    .body(payload)
                    .put(uri);
        }

        protected  Response putRequest(String uri, HashMap<String, Object> params) {
            return given().config(config)
                    .header("Content-Type", "application/json")
                    .queryParams(params)
                    .put(uri);
        }

        protected  Response putRequest(String uri) {
            return given().config(config)
                    .put(uri);
        }

        protected Response patchRequest(String uri, String token, Object payload) {
            Header authHeader = new Header("Authorization", token);
            Response response = given().config(config).contentType(ContentType.JSON).header(authHeader).body(payload).patch(uri);
            return response;
        }

        protected Response patchRequest(String uri, Object payload) {
            return given().config(config).body(payload).patch(uri);
        }

        protected Response deleteRequest(String uri, String authToken) {
            Header authHeader = new Header("Authorization", authToken);
            return given().config(config)
                    .header(authHeader)
                    .delete(uri);
        }
    }
}
