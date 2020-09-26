package com.hackathon2020.auth;

import com.hackathon2020.security.UserDetails;
import com.hackathon2020.security.jwt.JwtTokenUtil;
import com.hackathon2020.security.jwt.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("htonUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenCache tokenCache;
//
//    private final CredentialUtils credentialUtils;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager,
//                          JwtTokenUtil jwtTokenUtil,
//                          @Qualifier("htonUserDetailService") UserDetailsService userDetailsService,
//                          TokenCache tokenCache,
//                          CredentialUtils credentialUtils) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userDetailsService = userDetailsService;
//        this.tokenCache = tokenCache;
//        this.credentialUtils = credentialUtils;
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = (UserDetails) userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        tokenCache.addToken(userDetails.getUsername(), token);
        return ResponseEntity.ok(new LoginResponse(userDetails, token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}