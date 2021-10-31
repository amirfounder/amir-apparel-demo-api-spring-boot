package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.amirfounder.amirappareldemoapispringboot.utils.Constants.APPLICATION_USERS_PATH;

@RestController
@RequestMapping(path = APPLICATION_USERS_PATH)
public class ApplicationUserController {

    private final Logger logger = LogManager.getLogger(ApplicationUserController.class);

    private final ApplicationUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ApplicationUserController(
            ApplicationUserService userService,
            ModelMapper modelMapper
    ) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ApplicationUserDTO> saveUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody ApplicationUser requestUser
    ) {
        logger.info("request received for saveUser");
        ApplicationUser user = userService.saveUser(requestUser, bearerToken);
        ApplicationUserDTO userDTO = modelMapper.map(user, ApplicationUserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<ApplicationUserDTO> getByEmail(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String email
    ) {
        logger.info("Request received for getUserByEmail");
        ApplicationUser user = userService.getByEmail(email, bearerToken);
        ApplicationUserDTO userDTO = modelMapper.map(user, ApplicationUserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
