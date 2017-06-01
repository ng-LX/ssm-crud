package com.dsl.service;

import com.dsl.bean.Department;
import com.dsl.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Victor on 2017/6/1.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        // TODO Auto-generated method stub
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
