package com.gusrubin.springauthorizationserver.infrastructure.user;

import java.util.List;
import org.modelmapper.AbstractConverter;

import com.gusrubin.springauthorizationserver.domain.role.UserRole;
import com.gusrubin.springauthorizationserver.infrastructure.role.UserRoleEntity;

public class RoleListConverter extends AbstractConverter<List<UserRoleEntity>, List<UserRole>> {

    @Override
    protected List<UserRole> convert(List<UserRoleEntity> roles) {
	return roles.stream().map(this::toUserRole).toList();
    }

    private UserRole toUserRole(UserRoleEntity entity) {
	return UserRole.builder().id(entity.getId()).roleName(entity.getRole()).build();
    }
}
