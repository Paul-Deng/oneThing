package fund.paul.user.controller;

import fund.paul.db.utils.CustomLambdaQueryWrapper;
import fund.paul.user.domain.pojo.SysUsersRoles;
import fund.paul.user.domain.service.ISysUsersRolesService;

import java.util.Collection;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paul
 * @date 2024/1/23 13:35
 */
@RestController
@RequestMapping("/api/userRoles")
@Slf4j
public class UserRoleController {

    @Autowired
    private ISysUsersRolesService userRoleService;

    @PostMapping
    public ResponseEntity<Boolean> createUserRole(@RequestBody SysUsersRoles userRole) {
        boolean result = userRoleService.saveOrUpdate(userRole);
        if (result) {
            log.info("User-Role relationship created successfully: {}", userRole);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            log.error("Failed to create User-Role relationship: {}", userRole);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<Boolean> deleteUserRole(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
        boolean result = userRoleService.remove(new CustomLambdaQueryWrapper<SysUsersRoles>()
                .eq(SysUsersRoles::getUserId, userId).eq(SysUsersRoles::getRoleId, roleId));
        if (result) {
            log.info("User-Role relationship deleted successfully for userId={}, roleId={}", userId, roleId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.error("Failed to delete User-Role relationship for userId={}, roleId={}", userId, roleId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<Boolean> updateUserRoleStatus(@RequestBody SysUsersRoles userRole) {
        boolean result = userRoleService.updateById(userRole);
        if (result) {
            log.info("User-Role relationship status updated successfully: {}", userRole);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.error("Failed to update User-Role relationship status: {}", userRole);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SysUsersRoles>> getUserRolesByUserId(@PathVariable("userId") Collection<String> userIdSet) {
        List<SysUsersRoles> roles = userRoleService.list(new CustomLambdaQueryWrapper<SysUsersRoles>().in(SysUsersRoles::getUserId, userIdSet));
        log.info("Retrieved user roles for userId={}", userIdSet);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}