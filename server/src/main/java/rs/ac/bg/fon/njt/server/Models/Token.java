/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.domain;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "tokens")
public class Token {
    
    @EmbeddedId
    private TokenId id;
     
    private String token;
    private Date createdAt;
    private Date expiresAt;
    
    
    
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    public Token() {
    }

    public Token(TokenId id, String token, Date createdAt, Date expiresAt, TokenType tokenType) {
        this.id = id;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.tokenType = tokenType;
    }

    public TokenId getId() {
        return id;
    }

    public void setId(TokenId id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "Token{" + "id=" + id + ", token=" + token + ", createdAt=" + createdAt + ", expiresAt=" + expiresAt + ", tokenType=" + tokenType + '}';
    }

    
    
    
    
}
