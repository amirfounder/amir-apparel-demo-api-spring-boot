package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private Address address;

}
