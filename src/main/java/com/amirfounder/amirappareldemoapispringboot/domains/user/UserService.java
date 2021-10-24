package com.amirfounder.amirappareldemoapispringboot.domains.user;

public interface UserService {

    User saveUser(User user, String bearerToken);

    User findByEmail(String email);

}
