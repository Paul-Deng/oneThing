package fund.paul.user.service;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaTokenContextForThreadLocal;
import fund.paul.common.exception.BusinessException;
import fund.paul.user.domain.pojo.SysUser;
import fund.paul.user.domain.service.ISysUserService;
import fund.paul.user.domain.service.impl.AuthServiceImpl;
import fund.paul.userapi.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private ISysUserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SaManager.setSaTokenContext(new SaTokenContextForThreadLocal());
    }

    @Test
    void login_Success() {
        // 准备测试数据
        UserDTO loginDTO = new UserDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("password123");

        SysUser mockUser = new SysUser();
        mockUser.setId(1L);
        mockUser.setUsername("testUser");
        mockUser.setPassword("encodedPassword");

        // 配置mock行为
        when(userService.getByUsername("testUser")).thenReturn(mockUser);
        when(userService.validatePassword("password123", "encodedPassword")).thenReturn(true);

        // 执行测试
        String token = authService.login(loginDTO);

        // 验证结果
        assertNotNull(token);
        verify(userService).getByUsername("testUser");
        verify(userService).validatePassword("password123", "encodedPassword");
    }

    @Test
    void login_WrongPassword() {
        UserDTO loginDTO = new UserDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("wrongPassword");

        SysUser mockUser = new SysUser();
        mockUser.setUsername("testUser");
        mockUser.setPassword("encodedPassword");

        when(userService.getByUsername("testUser")).thenReturn(mockUser);
        when(userService.validatePassword("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(BusinessException.class, () -> authService.login(loginDTO));
    }

    @Test
    void register_Success() {
        UserDTO registerDTO = new UserDTO();
        registerDTO.setUsername("newUser");
        registerDTO.setPassword("password123");

        when(userService.saveIdempotency(any(), any(), any(), any())).thenReturn(true);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        UserDTO result = authService.register(registerDTO);

        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
        assertNull(result.getPassword());
    }

    @Test
    void register_UserExists() {
        UserDTO registerDTO = new UserDTO();
        registerDTO.setUsername("existingUser");
        registerDTO.setPassword("password123");

        when(userService.saveIdempotency(any(), any(), any(), any())).thenReturn(false);

        assertThrows(BusinessException.class, () -> authService.register(registerDTO));
    }
}