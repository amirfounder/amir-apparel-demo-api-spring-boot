package com.amirfounder.amirappareldemoapispringboot.domains.applicationuser;

import com.amirfounder.amirappareldemoapispringboot.domains.purchase.embeddables.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    @Embedded
    private Address address;

}
