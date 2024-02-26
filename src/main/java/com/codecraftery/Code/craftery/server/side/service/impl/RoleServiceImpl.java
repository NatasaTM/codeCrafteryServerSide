package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.dto.RoleDto;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationErrorMessageBuilder;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;
import com.codecraftery.Code.craftery.server.side.model.Role;
import com.codecraftery.Code.craftery.server.side.repository.RoleRepository;
import com.codecraftery.Code.craftery.server.side.service.RoleService;
import com.codecraftery.Code.craftery.server.side.validation.impl.RoleValidator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codecraftery.Code.craftery.server.side.mapper.RoleMapper.mapToRole;
import static com.codecraftery.Code.craftery.server.side.mapper.RoleMapper.mapToRoleDto;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final ValidationErrorMessageBuilder<Role> validationErrorMessageBuilder;

    @Override
    public RoleDto findById(Long id) throws RoleNotFoundException, RoleServiceException {
        try {
            Role role = roleRepository.findById(id)
                    .orElseThrow(() -> new RoleNotFoundException("Role with ID " + id + " not found"));
            RoleDto roleDto = mapToRoleDto(role);
            return roleDto;
        } catch (DataAccessException e) {
            // Log the error if needed
            logger.error("Error finding role with ID " + id, e);
            throw new RoleServiceException("Error finding Role with ID ");
        }
    }

    @Override
    public List<RoleDto> getAll() throws RoleServiceException {
        try {
            List<RoleDto> roleDtos = roleRepository.findAll().stream()
                    .map(role -> mapToRoleDto(role))
                    .collect(Collectors.toList());

            return roleDtos;
        } catch (DataAccessException ex) {
            logger.error("Error while retrieving categories from the database", ex);
            throw new RoleServiceException("Failed to retrieve roles", ex);
        }
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) throws ValidationException, RoleCreationException {
        Set<ConstraintViolation<Role>> violations = roleValidator.validate(mapToRole(roleDto));
        if (!violations.isEmpty()) {
            logger.error("RoleValidation");
            throw new ValidationException(validationErrorMessageBuilder.buildValidationErrorMessage(violations));

        }
        try {
            Role role = mapToRole(roleDto);
            Role savedRole = roleRepository.save(role);
            if (savedRole == null) {
                throw new RoleCreationException("Error saving role to database: ");
            }
            return mapToRoleDto(savedRole);
        } catch (DataAccessException e) {
            logger.error("Error occurred while adding role to database" + e);
            throw new RoleCreationException("Error saving role to database: ");
        }
    }

    @Override
    public void deleteById(Long id) throws RoleNotFoundException, RoleServiceException {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("Role with ID " + id + " not found");
        }
        try {
            roleRepository.deleteById(id);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new RoleServiceException("Error while deleting role", ex);
        } catch (Exception e) {
            logger.error("Failed to delete role" + e);
            throw new RoleServiceException("Failed to delete role");
        }

    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long id) throws ValidationException, RoleNotFoundException, RoleServiceException {
        Set<ConstraintViolation<Role>> violations = roleValidator.validate(mapToRole(roleDto));
        if (!violations.isEmpty()) {
            logger.error("RoleValidation");
            throw new ValidationException(validationErrorMessageBuilder.buildValidationErrorMessage(violations));
        }

        try {
            Role role = roleRepository.findById(id).get();
            if (role == null) {
                throw new RoleNotFoundException("Role with ID " + id + " not found");
            }

            role.setName(roleDto.getName());

            return mapToRoleDto(roleRepository.save(role));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new RoleServiceException("Error updating role: " + e.getMessage(), e);
        }
    }
}


