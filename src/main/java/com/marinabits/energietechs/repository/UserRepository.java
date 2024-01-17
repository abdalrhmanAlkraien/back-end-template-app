package com.marinabits.energietechs.repository;

import com.marinabits.energietechs.infra.exception.NotFoundException;
import com.marinabits.energietechs.repository.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ApplicationRepository<User, Integer> {

    @Modifying
    @Query("""
            UPDATE User user
            SET user.refreshToken = null,
                user.tokenExpiryDate = null
            WHERE user.id = :userId
            """)
    void invalidateRefreshTokenById(Integer userId);

    @Override
    <S extends User> @NonNull List<S> saveAll(@NonNull Iterable<S> entities);

    @Override
    <S extends User> @NonNull S save(@NonNull S entity);

    @Override
    @NonNull
    Optional<User> findById(@NonNull Integer id);

    default User getById(Integer id) {
        return findById(id)
                .filter(User::isEnabled)
                .orElseThrow(() -> new NotFoundException(User.class, id));

    }

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByRefreshToken(String token);


}
