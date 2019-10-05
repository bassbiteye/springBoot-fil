package com.fil.transfert.controller;


import com.fil.transfert.model.Message;
import com.fil.transfert.model.PartForm;
import com.fil.transfert.model.Role;
import com.fil.transfert.model.User;
import com.fil.transfert.repository.RoleRepository;
import com.fil.transfert.repository.UserRepository;
import com.fil.transfert.services.RoleService;
import com.fil.transfert.services.UserDetailsServiceImpl;
import com.fil.transfert.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SecuityController {
    @Autowired
    private UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    UserRepository userRepository;
//ajout utilisateur
    @PostMapping(value = "/register")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER','ROLE_ADMIN')")
    public Message register(PartForm userForm) throws Exception {

        User user=new  User(userForm.getNom(),userForm.getPrenom(), "", userForm.getTelephone(), userForm.getImageName(),userForm.getUsername(),userForm.getEmail(),userForm.getPassword());
        User userconnected = userDetailsService.getUserconnected();
            user.setPassword (encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role=new Role();
        role.setId(userForm.getRol());
        roles.add(role);
        user.setRoles(roles);
        user.setEtat("actif");
        if(userconnected.getPartenaire()!=null){
            user.setPartenaire(userconnected.getPartenaire());
        }
        userService.save(user);
        Message message = new Message(200,"utilisateur ajouté avec success");
        return message;
    }
    //liste profile
    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/profile")
    public List<Role> profile(){
       return  roleRepository.findAll();
    }
    // liste des utilisateurs du systeme
    @GetMapping(value = "/listeSysteme")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public List <User> listeUserSys() throws Exception {

        List user = userService.findUsersByPartenaireIsNullAndCompteIsNull();

        return user;
    }
    // liste des utilisateurs

    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> findAll(){
        User user = userDetailsService.getUserconnected();
        return userService.findAll();
    }
    //user connecté
    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/userCon",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User find(){
        User user = userDetailsService.getUserconnected();
        return user;
    }
    // bloquer un user
    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/etat/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Message bloquer(@PathVariable("id") long id) throws Exception{
            User user = userService.findById(id).orElseThrow(
                    () -> new Exception("utilisateur introuvable")
            );
            if (user != null) {
                if(user.getId()==(1)){
                    Message message = new Message(200,"Impossible de bloquer cet utilisateur");
                    return message;
                }
                if(user.getEtat().equals("bloquer")){
                    user.setEtat("actif");
                    userService.save(user);
                    Message message = new Message(200,"l' utilisateur est actif");
                    return message;
                }
                 else if(user.getEtat().equals("actif")){
                    user.setEtat("bloquer");
                    userService.save(user);
                    Message message = new Message(200,"l' utilisateur est bloqué");
                    return message;
                }
            }


        Message message = new Message(200,"erreur");
        return message;
}



}
