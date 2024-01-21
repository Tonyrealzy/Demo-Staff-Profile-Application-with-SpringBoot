package com.example.demo.services;

import com.example.demo.entity.Staff;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface StaffService
{
    public void createStaff(String name, int age);
    public List<Staff> getAllStaff();

    public Staff getStaff(long id);

    public void updateStaff(Staff staff);

    public void deleteStaff(long id);

}
