package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.Token;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Utils.Response;

public interface TokenService {

    String generateJwtToken(User user);
    Token createPasswordChangeToken(User user);
    void deleteExpiredTokens();
}
