package rs.ac.bg.fon.njt.server.Services;

import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Models.User;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String generateJwtToken(User user) {
        return "aksjhfahjgdjghasdghfasdh";
    }
}
