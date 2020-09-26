package com.hackathon2020.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CredentialUtils {

//    private final CommonDao<User, UserEntity> userDao;
//
//    @Autowired
//    public CredentialUtils(CommonDao<User, UserEntity> userDao) {
//        this.userDao = userDao;
//    }

    public String getCredentialLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal.getClass().equals(UserDetails.class)) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public Role getCredentialRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = null;
        if (principal.getClass().equals(UserDetails.class)) {
            role = ((UserDetails) principal).getRole();
        }
        return role;
    }

    public User getUserInfo() {
//        String login = getCredentialLogin();
//        SimpleCondition condition = new SimpleCondition.Builder()
//                .setSearchField("login")
//                .setSearchCondition(SearchCondition.EQUALS)
//                .setSearchValue(login)
//                .build();

//        return userDao.getByCondition(condition).stream().findFirst().orElse(null);
        return new User("kfm_login", "kfmn_pwd");
    }
}
