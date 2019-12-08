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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    List<Authority> adminPrivileges = Arrays.asList(
            readPrivilege,
            writePrivilege,
            storePrivilege,
            developPrivilege,
            stagePrivilege,
            productionPrivilege,
            createUserPrivilege,
            alterUserPrivilege);

    createRoleIfNotFound("ADMIN", adminPrivileges);

    createRoleIfNotFound("USER", Collections.singletonList(readPrivilege));

    createRoleIfNotFound("LOGGER", Collections.singletonList(writePrivilege));

    createRoleIfNotFound("DEV", Arrays.asList(readPrivilege, developPrivilege));

    createRoleIfNotFound("STAGE", Arrays.asList(readPrivilege, stagePrivilege));

    createRoleIfNotFound("PRODUCTION", Arrays.asList(readPrivilege, productionPrivilege));

    createRoleIfNotFound("DEV", Arrays.asList(readPrivilege, createUserPrivilege));


    Role adminRole = roleRepository.findByName("ADMIN");
    User user = new User();
    user.setName("ADMIN");
    user.setPassword(passwordEncoder.encode("secret"));
    user.setEmail("admin@admin");
    user.setRoles(Collections.singletonList(adminRole));
    userRepository.save(user);

    alreadySetup = true;
  }

  @Transactional
  private Authority createPrivilegeIfNotFound(String name) {

    Authority authority = authorityRepository.findByName(name);
    if (authority == null) {
      authority = new Authority(name);
      authorityRepository.save(authority);
    }
    return authority;
  }

  @Transactional
  private Role createRoleIfNotFound(
          String name, Collection<Authority> privileges) {

    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      role.setAuthorities(privileges);
      roleRepository.save(role);
    }
    return role;
  }
}
