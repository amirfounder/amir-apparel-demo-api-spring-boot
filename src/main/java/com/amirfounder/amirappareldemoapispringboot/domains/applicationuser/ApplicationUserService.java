package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

public interface ApplicationUserService {

    ApplicationUser saveUser(ApplicationUser user, String bearerToken);

    ApplicationUser getByEmail(String email, String bearerToken);

}
