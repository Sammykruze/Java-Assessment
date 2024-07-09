import static org.junit.jupiter.api.Assertions.assertEquals;
import static question1.UserInputValidation.generateToken;
import static question1.UserInputValidation.verifyToken;

import org.junit.jupiter.api.Test;


public class JwtUtilTest {

    @Test
    public void testValidToken() {
        String token = generateToken("samuel");
        assertEquals("verification pass", verifyToken(token));
    }

    @Test
    public void testInvalidToken() {
        String token = "invalidToken";
        assertEquals("verification fails", verifyToken(token));
    }
}

