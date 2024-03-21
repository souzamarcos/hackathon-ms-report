package com.fiap.hackathon.api.misc.token;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiap.hackathon.usecase.misc.secret.SecretUtils;
import com.fiap.hackathon.usecase.misc.secret.TokenJwtSecret;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TokenJwtUtilsTest {
    private static final String TOKEN_SECRET = "TEST-SECRET";
    private static final String TOKEN_ISSUER = "TEST-ISSUER";


    @Mock
    SecretUtils secretUtils;

    @InjectMocks
    TokenJwtUtils tokenJwtUtils;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readTokenOkay() {
        var expected = "ABC001";
        String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImlzcyI6IlRFU1QtSVNTVUVSIiwiaWQiOiJBQkMwMDEiLCJlbWFpbCI6ImVtYWlsQGdtYWlsLmNvbSIsIm5hbWUiOiJOb21lIiwidHlwZSI6MCwiaWF0IjoxNzA2NDEzNDMyLCJqdGkiOiJmMDQyM2U0Yy03YzEwLTRiMzUtOGQ5NS1kYmZhZmMzY2ZkYTQifQ._yL06ZjOO0k2Iin1udWAeou-MYVukcZ9XVOsJScVNiU";

        when(secretUtils.getTokenJwtSecret()).thenReturn(new TokenJwtSecret(TOKEN_SECRET, TOKEN_ISSUER));

        DecodedJWT result = tokenJwtUtils.readToken(validToken);
        assertEquals(expected, result.getClaim("id").asString());
    }

    @Test
    void readTokenExpired() {
        String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImNsaWVudElkIjoxLCJpc3MiOiJURVNULUlTU1VFUiIsImNwZiI6IjE2NTY1ODI0NzM4IiwiZXhwIjoxNjk4NjI4MjE2LCJpYXQiOjE2OTg2MjgxNTYsImp0aSI6ImRmYjM1OWFjLTI3MGItNDUxMC05YjcxLTZiYjE3YzI5MzQ1NCJ9.iVcSUx-UShDr1rSSJt2lweRDcwtM-AkRX2buCeI5e0E";

        when(secretUtils.getTokenJwtSecret()).thenReturn(new TokenJwtSecret(TOKEN_SECRET, TOKEN_ISSUER));

        assertThrows(JWTVerificationException.class, () -> tokenJwtUtils.readToken(expiredToken));
    }

    @Test
    void readTokenInvalidSecretAndIssuer() {
        String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImlzcyI6IlRFU1QtSVNTVUVSIiwiaWQiOiJBQkMwMDEiLCJlbWFpbCI6ImVtYWlsQGdtYWlsLmNvbSIsIm5hbWUiOiJOb21lIiwidHlwZSI6MCwiaWF0IjoxNzA2NDEzNDMyLCJqdGkiOiJmMDQyM2U0Yy03YzEwLTRiMzUtOGQ5NS1kYmZhZmMzY2ZkYTQifQ._yL06ZjOO0k2Iin1udWAeou-MYVukcZ9XVOsJScVNiU";

        when(secretUtils.getTokenJwtSecret()).thenReturn(new TokenJwtSecret("INVALID-SECRET", "INVALID-ISSUER"));

        assertThrows(JWTVerificationException.class, () -> tokenJwtUtils.readToken(validToken));
    }
}
