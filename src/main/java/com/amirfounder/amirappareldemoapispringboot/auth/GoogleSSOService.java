package com.amirfounder.amirappareldemoapispringboot.auth;

import com.amirfounder.amirappareldemoapispringboot.exceptions.InternalServerError;
import com.amirfounder.amirappareldemoapispringboot.exceptions.Unauthorized;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;

import static com.amirfounder.amirappareldemoapispringboot.utils.Constants.GOOGLE_CLIENT_ID;

@Service
public class GoogleSSOService {

    Logger logger = LogManager.getLogger(GoogleSSOService.class);

    public boolean authenticateJwt(String jwt, String email) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        )
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();

        GoogleIdToken idToken;

        try {
            idToken = verifier.verify(jwt);
            logger.info("Verified token");
        } catch (GeneralSecurityException gse) {
            logger.error(gse.getMessage());
            throw new InternalServerError(gse.getMessage());
        } catch (Exception e) {
            logger.error("There was a problem reading the token");
            throw new InternalServerError("There was a problem reading the token");
        }

        if (idToken == null) {
            logger.error("Could not verify token");
            throw new Unauthorized("Could not verify token");
        }

        return Objects.equals(
                idToken.getPayload().getEmail(),
                email
        );

    }

}
