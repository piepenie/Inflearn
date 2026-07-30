package com.jwt.user.entity;

import com.jwt.auth.dto.AuthUser;
import com.jwt.auth.enums.Role;
import com.jwt.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id) {
        this.id = id;
    }

    public static User fromAuthUser(AuthUser authUser) {
        return new User(authUser.getId());
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
