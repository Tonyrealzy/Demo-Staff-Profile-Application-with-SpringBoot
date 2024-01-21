package com.example.demo.services;

import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImp implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public void createStaff(String name, int age) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setAge(age);
        staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaff() {
      //You can do this instead...
//        List<Staff> staffList = staffRepository.findAll();
//        return staffList;
        return staffRepository.findAll();
    }

    public Staff getStaff(long id){
        Optional<Staff> optionalStaff = staffRepository.findById(id);

        if(optionalStaff.isPresent()){
            Staff staff = optionalStaff.get();
            return staff;
        }
        else {
            return null;
        }
    }

    @Override
    public void updateStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(long id) {
//        Optional<Staff> optionalStaff = staffRepository.findById(id);
//
//        if (optionalStaff.isPresent()) {
//            staffRepository.deleteById(id);
//        }
        staffRepository.deleteById(id);
    }


}