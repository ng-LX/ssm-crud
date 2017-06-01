package com.dsl.test;


import com.dsl.bean.Department;
import com.dsl.bean.Employee;
import com.dsl.dao.DepartmentMapper;
import com.dsl.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层
 * Created by Victor on 2017/5/18.
 * 推荐使用Spring的项目就可以使用spring的单元测试，可以自动注入我们的组件
 * 1.导入SpringTest模块
 * 2.使用@ContextConfiguration指定配置文件的位置
 * 3.直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentmapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    /**
     * 测试DepartmentMapper
     */
    @Test
    public  void testCRUD(){
        /*//1.创建Spring IOC 容器
        ApplicationContext ioc＝new ClassPathXmlApplicationContext("applicationContext.xml");
        //2。从容器中拿出mapper
        DepartmentMapper bean=ioc.getBean(DepartmentMapper.class);*/
        System.out.println(departmentmapper);
        //1.插入几个部门
        departmentmapper.insertSelective(new Department(null,"开发部"));
        departmentmapper.insertSelective(new Department(null,"测试部"));

        //2。生成员工数据
       // employeeMapper.insertSelective(new Employee(null,"jerry","M","jerry@126.com",1));
        //3.批量插入员工，批量，可以执行批量操作的sql session
        EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uuid=UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@126.com",1));
        }
        System.out.println("批量处理结束");

    }
}
