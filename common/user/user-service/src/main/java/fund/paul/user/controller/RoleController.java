package fund.paul.user.controller;

import fund.paul.user.bean.SysRole;
import fund.paul.user.serice.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author paul
 * @date 2024/1/23 13:25
 */
@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController {
    @Autowired
    private ISysRoleService roleService;

    @PostMapping
    public ResponseEntity<Integer> createRole(@RequestBody SysRole role) {
        int result = roleService.createRole(role);
        if (result > 0) {
            log.info("SysRole created successfully: {}", role);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            log.error("Failed to create role: {}", role);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateRole(@PathVariable Long id, @RequestBody SysRole role) {
        role.setId(id); // 设置要更新的ID
        int result = roleService.updateRole(role);
        if (result > 0) {
            log.info("SysRole updated successfully: {}", role);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.error("Failed to update role: {}", role);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteOrDisableRole(@PathVariable Long id, @RequestBody SysRole role) {
        role.setId(id); // 设置要删除或禁用的ID
        int result = roleService.deleteOrDisableRole(role);
        if (result > 0) {
            log.info("SysRole deleted or disabled successfully: {}", role);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.error("Failed to delete or disable role: {}", role);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SysRole>> getAllRoles() {
        List<SysRole> roles = roleService.getAllRoles();
        log.info("Retrieved all roles");
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/batch")
    public ResponseEntity<List<SysRole>> getRolesByIds(@RequestParam List<Long> ids) {
        List<SysRole> roles = roleService.getRolesByIds(ids);
        log.info("Retrieved roles by IDs: {}", ids);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}

