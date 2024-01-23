//package fund.paul.user.serice.impl;
//
//import cn.hutool.core.lang.Assert;
//import fund.paul.common.bean.SysPermissions;
//import fund.paul.user.serice.ISysPermissionsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
///**
// * 测试类
// *
// * @author paul
// * @date 2023/5/26 00:09
// */
//
//@SpringBootTest
//class SysPermissionsServiceImplTest extends BasicTestConfig {
//
//    @Autowired
//    ISysPermissionsService iSysPermissionsService;
//
//    @Test
//    void findAll() {
//        List<SysPermissions> all = iSysPermissionsService.findAll();
//        Assert.equals(all.size(), 1);
//    }
//
//    @Test
//    void findOnes() {
//    }
//
//    @Test
//    void setMenuToRole() {
//    }
//
//    @Test
//    void findByRoles() {
//    }
//
//    @Test
//    void testFindByRoles() {
//    }
//
//    @Test
//    void findByRoleCodes() {
//    }
//}