package com.rushshopping;

import com.rushshopping.dao.UserDOMapper;
import com.rushshopping.pojo.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBootApplication 主启动类
 *
 */
@SpringBootApplication(scanBasePackages = {"com.rushshopping"})
@RestController
@MapperScan("com.rushshopping.dao")
public class App 
{

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO == null)
            return "User doesn't exist.";
        return userDO.getName();
    }

    public static void main( String[] args )
    {
        System.out.println( "Welcome to our website!" );
        SpringApplication.run(App.class,args);
    }
}
