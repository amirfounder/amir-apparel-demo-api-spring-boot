package com.amirfounder.amirappareldemoapispringboot.domains.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.amirfounder.amirappareldemoapispringboot.utils.Constants.USERS_PATH;

@RestController
@RequestMapping(path = USERS_PATH)
public class UserController {

    private final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(
            UserService userService,
            ModelMapper modelMapper
    ) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(
            @RequestBody User requestUser,
            @RequestHeader String bearerToken
    ) {
        logger.info("request received for saveUser");
        User user = userService.saveUser(requestUser, bearerToken);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserDTO> getByEmail(
            @PathVariable String email
    ) {
        logger.info("Request received for getByEmail");

        User user = userService.findByEmail(email);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
