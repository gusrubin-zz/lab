package com.gusrubin.springauthorizationserver.infrastructure.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gusrubin.springauthorizationserver.infrastructure.role.UserRoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_ENTITY")
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;
    private Boolean accountExpired;
    private Boolean accountLocked;
    private Boolean credentialsExpired;
    private Boolean enabled;

}
