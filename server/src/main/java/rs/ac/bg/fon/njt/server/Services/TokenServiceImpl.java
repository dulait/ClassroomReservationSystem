package rs.ac.bg.fon.njt.server.Services;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Enums.TokenType;
import rs.ac.bg.fon.njt.server.Models.Token;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.TokenRepository;
import rs.ac.bg.fon.njt.server.Utils.Response;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    
    private final TokenRepository tokenRepository;
    
    @Override
    public String generateJwtToken(User user) {
        return "aksjhfahjgdjghasdghfasdh";
    }

    @Override
    public Token createPasswordChangeToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setCreatedAt(new Date());
        token.setExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60));
        token.setTokenType(TokenType.PasswordReset);
        
        String tokenString = RandomStringUtils.randomAlphanumeric(10);
        token.setToken(tokenString);
        
        tokenRepository.save(token);
        
        return token;
    }

    @Override
    @Scheduled(fixedRate = 2700000)
    @Transactional
    public void deleteExpiredTokens() {
        Date now = new Date();
        List<Token> expiredTokens = tokenRepository.findByExpiresAtBefore(now);
        if (!expiredTokens.isEmpty()) {
            tokenRepository.deleteAll(expiredTokens);
        }
    }

}
