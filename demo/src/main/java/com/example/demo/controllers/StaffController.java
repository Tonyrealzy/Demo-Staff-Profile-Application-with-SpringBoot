package com.example.demo.controllers;


import com.example.demo.entity.Staff;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/Staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home()
    {
        String info = "Welcome to the homepage of my first Spring boot project!";
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Staff/index");
        mav.addObject("info", info);
        return mav;
    }

//    @RequestMapping(value = "/Create", method = RequestMethod.GET)
//    public ModelAndView Create()
//    {
//        ModelAndView mav = new ModelAndView("redirect:/Staff/List");
//        mav.setViewName("Staff/create");
//        mav.addObject("message", "Staff entry created successfully");
//        return mav;
//    }

    @GetMapping("/Create")
    public String showCreateForm(Model model) {
        // Add any necessary model attributes
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Staff/create");
        mav.addObject("message", "Staff entry created successfully");
        model.addAttribute("staff", new Staff());
        return "Staff/create";
    }

    @PostMapping("/Create")
    public String createStaff(Staff staff, Model model) {
        // Add logic to save the staff entry to your database or perform necessary actions
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Staff entry created successfully");

        // Assuming you want to redirect to the same page after submitting
        model.addAttribute("message", "Staff entry created successfully");
        return "redirect:/Staff/Create";
    }
    @RequestMapping(value = "/Update/{id}", method = RequestMethod.POST)
    public ModelAndView Update(Staff staff, @PathVariable(value = "id") long id)
    {
        staff.setId(id);
        staffService.updateStaff(staff);
        return null;
    }

//    @RequestMapping(value = "/Update/{id}", method = RequestMethod.POST)
//    public ModelAndView processUpdateForm(@PathVariable(value="id") long id, @ModelAttribute Staff updatedStaff) {
//        // Logic for updating the staff entry in the database
//
//        ModelAndView mav = new ModelAndView("redirect:/Staff/List"); // Change the mapping to /List
//        mav.addObject("message", "Staff entry updated successfully");
//        return mav;
//    }

    @RequestMapping(value = "/View", method = RequestMethod.GET)
    public ModelAndView View(@RequestParam(value="id") long id)
    {
        Staff staff = staffService.getStaff(id);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("staff/View");
        mav.addObject("staff", staff);
        return mav;
    }

    @RequestMapping(value = "/Edit/{id}", method = RequestMethod.GET)
    public ModelAndView Edit(@PathVariable(value="id") long id)
    {
        Staff staff = staffService.getStaff(id);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("staff/Edit");
        mav.addObject("staff", staff);
        return mav;
    }

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.GET)
    public ModelAndView Delete(@PathVariable(value="id") long id)
    {
        // Retrieve the staff to be deleted
        Staff staff = staffService.getStaff(id);

        // Check if staff exists
        if (staff != null) {
            // Delete the staff entry
            staffService.deleteStaff(id);

            // Create ModelAndView for success message or redirect to List page
            ModelAndView mav = new ModelAndView("redirect:/Staff/Update/id");
            mav.addObject("message", "Staff entry deleted successfully");
            List<Staff> staffList = staffService.getAllStaff();
            mav.setViewName("staff/List");
            mav.addObject("staff", staffList);
            return mav;
        } else {
            // Create ModelAndView for error message or redirect to List page
            ModelAndView mav = new ModelAndView("redirect:/Staff/List");
            mav.addObject("error", "Staff entry not found");
            return mav;
        }
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public ModelAndView List()
    {
        ModelAndView mav = new ModelAndView();
        List<Staff> staffList = staffService.getAllStaff();
        mav.setViewName("staff/List");
        mav.addObject("staff", staffList);
        return mav;
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public ModelAndView Register(@RequestParam(value = "UserName") String name, @RequestParam(value = "UserAge") int age)
    {
        staffService.createStaff(name, age);
        return null;
    }


}
