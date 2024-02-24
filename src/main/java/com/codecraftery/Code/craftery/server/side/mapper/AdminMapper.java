package com.codecraftery.Code.craftery.server.side.mapper;

import com.codecraftery.Code.craftery.server.side.dto.AdminDto;
import com.codecraftery.Code.craftery.server.side.model.Admin;

public class AdminMapper {

    public static AdminDto mapToAdminDto(Admin admin){
      return  AdminDto.builder()
                .email(admin.getEmail())
                .name(admin.getName())
                .build();
    }
    
}
