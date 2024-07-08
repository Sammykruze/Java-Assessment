import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import question1.JwtUtil;

public class JwtUtilTest {

    @Test
    public void testValidToken() {
        String token = JwtUtil.generateToken("user");
        assertEquals("verification pass", JwtUtil.verifyToken(token));
    }

    @Test
    public void testInvalidToken() {
        String token = "invalidToken";
        assertEquals("verification fails", JwtUtil.verifyToken(token));
    }
}

