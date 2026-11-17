import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static org.hamcrest.Matchers.*;

public class PostFormDataTest {

    @Test
    public void testPostFormRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .accept("application/json")
                .log().all()
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }
}
