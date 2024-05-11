package rs.ac.bg.fon.njt.server.Utils;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public static String generateTempPassword() {
        return "tempPassword123";
    }
}
