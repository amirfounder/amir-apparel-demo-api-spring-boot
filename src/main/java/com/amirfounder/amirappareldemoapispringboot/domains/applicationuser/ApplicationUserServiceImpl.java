package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

import com.amirfounder.amirappareldemoapispringboot.exceptions.ResourceNotFound;
import com.amirfounder.amirappareldemoapispringboot.exceptions.ServiceUnavailable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    Logger logger = LogManager.getLogger(ApplicationUserServiceImpl.class);

    @Autowired
    private ApplicationUserRepository userRepository;

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
    public ApplicationUser getByEmail(String email) {
        ApplicationUser user;

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
