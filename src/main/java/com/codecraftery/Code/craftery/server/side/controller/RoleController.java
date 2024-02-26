package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.dto.CategoryDto;
import com.codecraftery.Code.craftery.server.side.dto.RoleDto;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.categoryExceptions.CategoryServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleCreationException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleNotFoundException;
import com.codecraftery.Code.craftery.server.side.exceptions.roleExceptions.RoleServiceException;
import com.codecraftery.Code.craftery.server.side.exceptions.validationExcpetions.ValidationException;
import com.codecraftery.Code.craftery.server.side.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getRoles() throws RoleServiceException {

        return ResponseEntity.ok(roleService.getAll());
    }


    @PostMapping("/create-role")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) throws ValidationException, RoleCreationException {
        return new ResponseEntity<>(roleService.addRole(roleDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-role/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) throws RoleServiceException, RoleNotFoundException {

        roleService.deleteById(id);
        return ResponseEntity.ok().build();


    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) throws RoleServiceException, RoleNotFoundException {

        RoleDto roleDto = roleService.findById(id);

        return ResponseEntity.ok(roleDto);


    }

    @PutMapping("/update-role/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) throws ValidationException, RoleServiceException, RoleNotFoundException {

        RoleDto updatedRole = roleService.updateRole(roleDto, id);
        return ResponseEntity.ok(updatedRole);

    }
}


