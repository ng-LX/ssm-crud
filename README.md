# ssm-crud
## 1.环境： 

    Intellij idea 2017

    jdk7

## 2.使用及功能：

    1.数据库在工程resource中的ssm-crud.sql中，直接导入到本地数据库即可

    2.使用Maven构建spring+springMVC+mybatis项目，实现srud功能。
    
    3.持久层直接采用mybatis Generator产生，也可以根据自己需要来进行修改。
    
    4.使用mybatis的pagehelper来实现分页

    5.为了显示方便，数据库中的数据采用mybatis批量插入，数据格式采用UUID随机生成

    6.前端使用bootstrap来快速搭建美观的界面
    
    
 ## 3.数据库事务
 
 事务采用xml配置的方式，切入点指定自己写的service方法，通配符模式。如删除、增加、修改等。发生异常进行数据回滚。
  <!--开启基于注解的事务，使用xml配置形式的事务(必须主要都是使用配置式)-->
    <aop:config>
        <!--切入点表达式-->
        <!--..*表示类下所有的方法都来参与事务-->
        <aop:pointcut id="txPoint" expression="execution(* com.dsl.service..*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
    </aop:config>
    <!--配置事务增强，事务如何切入-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!--所有方法都是事务方法-->
            <tx:method name="*"/>
            <!--以get开始的所有方法  -->
            <tx:method name="get*" read-only="true"/>
        </tx:attributes>
    </tx:advice>
    <!-- Spring配置文件的核心点（数据源、与mybatis的整合，事务控制） -->
    


 
 
 


