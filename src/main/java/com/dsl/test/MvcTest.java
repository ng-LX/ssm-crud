package com.dsl.test;


import com.dsl.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

/**
 * 使用Spring测试模块提供的测试请求功能，测试curd请求的正确性
 * Spring4测试的时候，需要servlet3.0的支持
 * Created by Victor on 2017/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   //使用这个Annotate会在跑单元测试的时候真实的启一个web服务，然后开始调用Controller的Rest API，待单元测试跑完之后再将web服务停掉;
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
    private MockMvc mockMvc;
    //传入Springmvc的ioc
    @Autowired
    private WebApplicationContext context;
    //虚拟mvc请求，获取处理的结果


    @Before
    public void initMockMvx(){
        this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void  testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps")
                .param("pn","1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //请求成功后，请求域中会有pageinfo来验证数据是否正确
        MockHttpServletRequest request=result.getRequest();
        PageInfo pi=(PageInfo)request.getAttribute("pageInfo");
        System.out.println("当前页码："+pi.getPageNum());
        System.out.println("总页码:"+pi.getPages());
        System.out.println("总记录数:"+pi.getTotal());
        System.out.println("在页面需要连续显示页码");
        int []nums=pi.getNavigatepageNums();
        for(int i:nums){
            System.out.println(" "+i);
        }
        //获取员工数据
        List<Employee> list=pi.getList();
        for(Employee employee:list){
            System.out.println("ID："+employee.getEmpId()+"==>Name"+employee.getEmpName());
        }
    }
}
