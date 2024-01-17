package com.marinabits.energietechs.service;

import com.marinabits.energietechs.api.model.request.ChangePasswordRequest;
import com.marinabits.energietechs.api.model.request.CreateUserRequest;
import com.marinabits.energietechs.api.model.response.UserResponse;
import com.marinabits.energietechs.infra.exception.RefreshTokenException;
import com.marinabits.energietechs.mapper.UserMapper;
import com.marinabits.energietechs.infra.security.jwt.JwtTokenHelper;
import com.marinabits.energietechs.infra.util.UuidUtil;
import com.marinabits.energietechs.repository.entity.Language;
import com.marinabits.energietechs.repository.entity.Role;
import com.marinabits.energietechs.repository.entity.User;
import com.marinabits.energietechs.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;

@Log4j2
@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper userMapper;


    public UserService(final UserRepository repository,
                       final UserMapper userMapper) {

        this.repository = repository;
        this.userMapper = userMapper;
    }

    /**
     * This method is responsible to create a new user.
     *
     * @param request data to create a new user
     * @return UserView of created user.
     */
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        if (this.repository.findByUsernameIgnoreCase(request.username()).isPresent())
            throw new ValidationException("Username exists!");

        // Add user
        User userEntity = this.userMapper.toUser(request);
        userEntity.setLanguage(new Language("en", "English"));
        User user = this.repository.save(userEntity);

        // Add user authorities
        userEntity.setAuthorities(Role.CLIENT);

        // Update user to add authorities
        this.repository.save(userEntity);

        System.out.println("Saved User: " + userEntity);

        // Return user view
        return this.userMapper.toView(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.repository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", username)));
    }


    /**
     * Method to generate the refresh token
     *
     * @param user required to hve a new refresh token
     * @return the updated user with new refresh token
     */
    @Transactional
    public User generateRefreshToken(User user) {
        user.setTokenExpiryDate(now().plus(JwtTokenHelper.refreshTokenExpiration(), DAYS));
        user.setRefreshToken(UuidUtil.newUuid().toString());
        // Update user to add authorities
        return this.repository.save(user);

    }

    /**
     * This method return the user based on a valid refresh token.
     *
     * @param token refresh token to find user.
     * @return an optional user represented by the token.
     */
    public Optional<User> findByRefreshToken(String token) {
        return this.repository.findByRefreshToken(UuidUtil.uuidFrom(token).toString());
    }

    /**
     * Method to verify the user refresh token validly, either it is expired or not.
     * If valid return the same user object, else throw an exception indicating that user token is expired.
     *
     * @param user to validate its token expiration status.
     * @return user that has valid refresh token.
     */
    @Transactional
    public User verifyRefreshTokenExpiration(User user) {
        if (user.getTokenExpiryDate().compareTo(now()) < 0) {
            this.invalidateRefreshTokenById(user.getId());
            throw new RefreshTokenException(user.getRefreshToken().toString(),
                    "Refresh token expired. Please signin!");
        }

        return user;
    }

    /**
     * Method to reset user refresh token and its expiration date.
     *
     * @param userId to reset token values for.
     */
    @Transactional
    public void invalidateRefreshTokenById(Integer userId) {
        this.repository.invalidateRefreshTokenById(userId);
    }

    /**
     * Method update user password.
     *
     * @param userId of user need to be updated.
     */
    public void updatePassword(Integer userId, ChangePasswordRequest changePassRequest) {
        if (!changePassRequest.newPassword().equals(changePassRequest.newPasswordAgain()))
            throw new IllegalArgumentException("Passwords doesn't match!");

        var user = this.repository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException(
                        format("User with id - %d, not found", userId)));
        this.userMapper.updatePassword(changePassRequest, user);
        this.repository.save(user);
    }

    public UserMapper mapper() {
        return this.userMapper;
    }

    public User getUserByUserId(final Integer userId) {

        return this.repository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException(
                        format("User with id - %d, not found", userId)));
    }
}
