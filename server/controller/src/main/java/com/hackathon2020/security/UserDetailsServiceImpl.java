package com.hackathon2020.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Qualifier("htonUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserDetails("kfmn_login", "kfmn_pwd", true,
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + Role.CLIENT.name())),
                Role.CLIENT);
//        SimpleCondition condition = new SimpleCondition.Builder()
//                .setSearchField("login")
//                .setSearchCondition(SearchCondition.EQUALS)
//                .setSearchValue(login)
//                .build();
//        User user = userDao.getByCondition(condition).stream().findFirst().orElse(null);
//        if (user != null) {
//            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().name()));
//            return new UserDetails(
//                    user.getLogin(),
//                    user.getPwd(),
//                    user.getEnabled(),
//                    user.getEnabled(),
//                    user.getEnabled(),
//                    user.getEnabled(),
//                    authorities,
//                    user.getRole());
//        } else {
//            throw new BadCredentialsException("Wrong user name");
//        }
    }
}
