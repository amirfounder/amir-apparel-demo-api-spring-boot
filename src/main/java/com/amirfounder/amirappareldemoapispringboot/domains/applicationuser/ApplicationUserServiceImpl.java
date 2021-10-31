package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

import com.amirfounder.amirappareldemoapispringboot.auth.GoogleSSOService;
import com.amirfounder.amirappareldemoapispringboot.auth.JwtUtil;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ResourceNotFound;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import com.amirfounder.amirappareldemoapispringboot.exceptions.Unauthorized;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    Logger logger = LogManager.getLogger(ApplicationUserServiceImpl.class);

    private final JwtUtil jwtUtil;
    private final ApplicationUserRepository userRepository;
    private final GoogleSSOService googleSSOService;

    @Autowired
    public ApplicationUserServiceImpl(
            ApplicationUserRepository userRepository,
            JwtUtil jwtUtil,
            GoogleSSOService googleSSOService
    ) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.googleSSOService = googleSSOService;
    }

    @Override
    public ApplicationUser saveUser(ApplicationUser user, String bearerToken) {

        try {
            return userRepository.save(user);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServiceUnavailable(dae.getMessage());
        }

    }

    @Override
    public ApplicationUser getByEmail(String email, String bearerToken) {
        ApplicationUser user;

        String jwt = jwtUtil.parseJwtFromHeader(bearerToken);

        if (jwt == null) {
            logger.error("Could not parse JWT from request header.");
            throw new Unauthorized("Could not parse JWT from request header.");
        }

        boolean authenticated = googleSSOService.authenticateJwt(jwt, email);

        if (!authenticated) {
            logger.error("Could not authenticate request");
            throw new Unauthorized("could not authenticate request");
        }

        try {
            user = userRepository.findByEmail(email);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServiceUnavailable(dae.getMessage());
        }

        if (user == null) {
            logger.error("Could not find user with email " + email);
            throw new ResourceNotFound("Could not find user with email " + email);
        }

        return user;
    }

}
