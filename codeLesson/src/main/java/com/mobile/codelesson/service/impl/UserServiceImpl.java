package com.mobile.codelesson.service.impl;

import com.mobile.codelesson.domain.dtos.req.UserProfileDTO;
import com.mobile.codelesson.domain.dtos.req.UserRegisterDTO;
import com.mobile.codelesson.domain.entities.Token;
import com.mobile.codelesson.domain.entities.User;
import com.mobile.codelesson.repositories.TokenRepository;
import com.mobile.codelesson.repositories.UserRepository;
import com.mobile.codelesson.service.contracts.UserService;
import com.mobile.codelesson.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final JWTTools jwtTools;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(JWTTools jwtTools, TokenRepository tokenRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public User findUserAuthenticated() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByemail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createUser(UserRegisterDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user, User.class);
        userRepository.save(newUser);
    }

    @Override
    public Boolean isPasswordOk(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updatePassword(User user, String password) {
        User user1 = userRepository.findById(user.getId()).orElse(null);
        user1.setPassword(passwordEncoder.encode(password));
        userRepository.save(user1);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateProfile(User user, UserProfileDTO userProfileDTO) {
        user.setName(userProfileDTO.getName());
        user.setLastName(userProfileDTO.getLastName());
        user.setEmail(userProfileDTO.getEmail());
        userRepository.save(user);
    }

}
