package com.codecraftery.Code.craftery.server.side.service;

import com.codecraftery.Code.craftery.server.side.dto.RoleDto;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
public interface RoleService {
    public RoleDto findById(Long id) throws RoleServiceException, RoleNotFoundException;
    public List<RoleDto> getAll() throws RoleServiceException;
    public RoleDto addRole(RoleDto roleDto) throws RoleCreationException, ValidationException;
    public void deleteById(Long id) throws RoleServiceException, RoleNotFoundException;
    public RoleDto updateRole(RoleDto roleDto,Long id) throws RoleServiceException, RoleNotFoundException, ValidationException;
}
