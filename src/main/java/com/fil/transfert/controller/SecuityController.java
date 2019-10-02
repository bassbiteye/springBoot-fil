package com.fil.transfert.controller;


import com.fil.transfert.model.PartForm;
import com.fil.transfert.model.Role;
import com.fil.transfert.model.User;
import com.fil.transfert.services.UserDetailsServiceImpl;
import com.fil.transfert.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SecuityController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
    public User register(@RequestBody PartForm userForm) throws Exception {

        User user=new  User(userForm.getNom(),userForm.getPrenom(), userForm.getEtat(), userForm.getTelephone(), userForm.getImageName(),userForm.getUsername(),userForm.getEmail(),userForm.getPassword());

            user.setPassword (encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role=new Role();
        role.setId(userForm.getRol());
        roles.add(role);
        user.setRoles(roles);
        return userService.save(user);
    }



    @PreAuthorize("hasAuthority('ROLE_SUPER') && hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> findAll(){
        User user = userDetailsService.getUserconnected();
        return userService.findAll();
    }
    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/userCon",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User find(){
        User user = userDetailsService.getUserconnected();
        return user;
    }

}
