package rs.ac.bg.fon.njt.server.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.TokenType;
import rs.ac.bg.fon.njt.server.Models.Token;

import rs.ac.bg.fon.njt.server.Models.User;

import rs.ac.bg.fon.njt.server.Repositories.TokenRepository;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    
     private final TokenRepository tokenRepository;
    
    @Override
    public String generateJwtToken(User user) {
         String subject = String.valueOf(user.getId());
         String issuer = String.valueOf(user.getEmail());
         Date now = new Date();
         Date expiration = new Date(now.getTime() + 3600000); // 1 hour
        String secretKey = "maximusTeam";

         String token = Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith( Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
         
         
         Token tokenEntity = new Token();
         tokenEntity.setCreatedAt(new Date());
         tokenEntity.setExpiresAt(expiration);
         tokenEntity.setToken(token);
         tokenEntity.setTokenType(TokenType.JWT);
         tokenEntity.setUser(user);
         tokenRepository.save(tokenEntity);
         
        
         return token;
    }
    
}
