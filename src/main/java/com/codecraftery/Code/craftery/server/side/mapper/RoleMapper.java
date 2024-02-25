package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.RoleDto;
import com.codecraftery.Code.craftery.server.side.model.Role;

public class RoleMapper {

    public static RoleDto mapToRoleDto(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
    public static Role mapToRole(RoleDto roleDto){
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .build();
    }
}
