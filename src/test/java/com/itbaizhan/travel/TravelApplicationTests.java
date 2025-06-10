package com.itbaizhan.travel;

import com.itbaizhan.travel.mapper.AdminMapper;
import com.itbaizhan.travel.mapper.ProductMapper;
import com.itbaizhan.travel.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ProductMapper productMapper;
    @Test
    void contextLoads() {
//        Page<Admin> page = adminService.findPage(1, 5);
//        System.out.println(page);
//        Admin desc = adminMapper.findDesc(1);
//        System.out.println(desc);
//        List<RoleWithStatus> role = adminService.findRole(1);
//        System.out.println(role);
//        Page<Product> productPage = productMapper.findProductPage(new Page(1, 5));
//        System.out.println(productPage);
        mailUtils.sendMail("461618768@qq.com","这是一封测试邮件","测试");
    }

}
