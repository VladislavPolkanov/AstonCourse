import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRawTextTest {

    @Test
    public void testPostRequest() {
        String requestBody = "{\"test\": \"value\"}";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("data", equalTo("{\"test\": \"value\"}"))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/post"));
    }

}
