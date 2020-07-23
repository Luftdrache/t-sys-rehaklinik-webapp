package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Position;
import com.tsystems.rehaklinik.services.AdminService;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(("/admin"))
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminService adminService;


    /**
     * Returns admin page with all employees list on it
     *
     * @param model model
     * @return admin_page.jsp to view
     */
    @GetMapping("/start-page")
    public String showAllEmployees(ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController - handler method showAllEmployees()");
        List<EmployeeDTO> allEmployeesFound = adminService.showAllEmployees();
        if (allEmployeesFound != null) {
            model.addAttribute("allEmployees", allEmployeesFound);
            logger.info("MedHelper_LOGS: The action showAllEmployees() completed successfully");
        } else {
            model.addAttribute("messageAboutEmployees",
                    "INFO: There is no information about employees in the database");
            logger.info("MedHelper_LOGS: The action showAllEmployees() completed without result");
        }
        return "admin_page";
    }


    /**
     * Returns page for new employees adding
     *
     * @param model model
     * @return form page for new employees adding
     */
    @GetMapping("/add-employee")
    public String addEmployeeForm(ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController - handler method addEmployeeForm()");
        return "add_new_employee";
    }


    /**
     * Is used to add a new employee to the system
     *
     * @param employee      the new employee from admin page
     * @param bindingResult the binding results
     * @param model         model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-employee")
    public String addEmployee(@Valid @ModelAttribute("addEmployee") Employee employee, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController:  handler method addEmployee()");
        logger.info("MedHelper_LOGS: New employee from JSP = " + employee.toString());
        if (bindingResult.hasErrors()) {
            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    model.addAttribute("message", "<p>Details: </p>" + fieldError.getDefaultMessage());
                    logger.debug("BindingResult Error: " + fieldError.getCode() + ". Details: " + fieldError.getDefaultMessage());
                }
                return "error_page";
            }
        }
        Employee newEmployee = adminService.addNewEmployee(employee);
        logger.info("MedHelper_LOGS: the new employee added successfully(" + employee.toString() + ")");
        model.addAttribute("message", "The new employee added successfully: ");
        model.addAttribute("newEmployee", newEmployee);
        return "add_new_employee";
    }


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    //    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//        logger.info("initBinder works");
//    }


//    https://stackoverflow.com/questions/44924656/how-to-register-global-databinding-for-localdate-in-spring-mvc
}
