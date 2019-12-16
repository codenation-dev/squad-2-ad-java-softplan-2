package com.codenation.config;

import com.codenation.entity.Authority;
import com.codenation.entity.Role;
import com.codenation.entity.User;
import com.codenation.repository.AuthorityRepository;
import com.codenation.repository.RoleRepository;
import com.codenation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {

    if (alreadySetup)
      return;
    Authority readPrivilege
            = createPrivilegeIfNotFound("READ");
    Authority writePrivilege
            = createPrivilegeIfNotFound("WRITE");
    Authority storePrivilege
            = createPrivilegeIfNotFound("STORE");
    Authority developPrivilege
            = createPrivilegeIfNotFound("DEVELOP");
    Authority stagePrivilege
            = createPrivilegeIfNotFound("STAGE");
    Authority productionPrivilege
            = createPrivilegeIfNotFound("PRODUCTION");
    Authority createUserPrivilege
            = createPrivilegeIfNotFound("CREATE_USER");
    Authority alterUserPrivilege
            = createPrivilegeIfNotFound("ALTER_USER");

    List<Authority> adminPrivileges = new ArrayList<>(Arrays.asList(
            readPrivilege,
            writePrivilege,
            storePrivilege,
            developPrivilege,
            stagePrivilege,
            productionPrivilege,
            createUserPrivilege,
            alterUserPrivilege));


    createRoleIfNotFound("USER", new ArrayList<>(Collections.singletonList(readPrivilege)));

    createRoleIfNotFound("LOGGER", new ArrayList<>(Collections.singletonList(writePrivilege)));

    createRoleIfNotFound("DEV", new ArrayList<>(Arrays.asList(readPrivilege, developPrivilege)));

    createRoleIfNotFound("STAGE", new ArrayList<>(Arrays.asList(readPrivilege, stagePrivilege)));

    createRoleIfNotFound("PRODUCTION", new ArrayList<>(Arrays.asList(readPrivilege, productionPrivilege)));

    Role adminRole = createRoleIfNotFound("ADMIN", adminPrivileges);

    User user = new User();
    user.setName("Administrator");
    user.setPassword(passwordEncoder.encode("secret"));
    user.setEmail("admin@admin.com");
    user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));
    userRepository.save(user);

    alreadySetup = true;
  }

  @Transactional
  public Authority createPrivilegeIfNotFound(String name) {

    Authority authority;
    Optional<Authority> authorityOptional = authorityRepository.findByName(name);
    if (!authorityOptional.isPresent()) {
      authority = new Authority(name);
      authorityRepository.save(authority);

      return authority;
    }
    return authorityOptional.get();
  }

  @Transactional
  public Role createRoleIfNotFound(
          String name, Collection<Authority> privileges) {

    Role role;
    Optional<Role> roleOptional = roleRepository.findByNameIgnoreCase(name);
    if (!roleOptional.isPresent()) {
      role = new Role(name);
      role.setAuthorities(privileges);
      roleRepository.save(role);

      return role;
    }

    return roleOptional.get();
  }
}
