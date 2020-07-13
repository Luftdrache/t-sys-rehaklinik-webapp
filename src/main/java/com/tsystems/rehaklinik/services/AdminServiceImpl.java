package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AdminService")
public class AdminServiceImpl implements AdminService {


    @Autowired
    private EmployeeDAO employeeDAO;

}
