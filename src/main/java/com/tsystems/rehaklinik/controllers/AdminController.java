package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Processes requests from an administrator
 *
 * @author Julia Dalskaya
 */
@Controller
@RequestMapping(("/admin"))
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminService adminService;

    private static final String MAIN_ADMIN_JSP = "admin_page";
    private static final String ADD_NEW_EMPLOYEE_JSP = "add_new_employee";
    private static final String EDIT_EMPLOYEE_JSP = "edit_employee_page";
    private static final String EMPLOYEE_DETAILS_JSP = "employee_details";


    /**
     * Returns admin page after admin sign in with all employees list on it
     *
     * @param model ModelMap model
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
        return MAIN_ADMIN_JSP;
    }


    /**
     * Returns page for new employees adding
     *
     * @param model ModelMap model
     * @return form page for new employees adding
     */
    @GetMapping("/add-employee")
    public String addEmployeeForm(ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController - handler method addEmployeeForm() GET");
        return ADD_NEW_EMPLOYEE_JSP;
    }


    /**
     * Is used to add a new employee to the system
     *
     * @param employee      the new employee from admin page
     * @param bindingResult the binding results
     * @param model         ModelMap model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-employee")
    public String addEmployee(@Valid @ModelAttribute("addEmployee") Employee employee, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController:  handler method addEmployee()");
        logger.info("MedHelper_LOGS: New employee from JSP = " + employee.toString());
        if (bindingResultCheck(bindingResult, model)) {
            return "error_page";
        }
        Employee newEmployee = adminService.addNewEmployee(employee);
        logger.info("MedHelper_LOGS: the new employee added successfully(" + employee.toString() + ")");
        model.addAttribute("message", "The new employee added successfully: ");
        model.addAttribute("newEmployee", newEmployee);
        return ADD_NEW_EMPLOYEE_JSP;
    }


    /**
     * Delete an employee by their ID
     *
     * @param employeeIdToDelete - ID of an employee to delete
     * @param modelMap           ModelMap
     * @return admin_page.jsp to view all employees without one deleted
     */
    @PostMapping("/delete-employee")
    public String deleteEmployeeById(@RequestParam("employeeIdToDelete") int employeeIdToDelete, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method deleteEmployeeById()");
        String deleteEmployeeByIdActionResultMessage = adminService.deleteEmployeeById(employeeIdToDelete);
        modelMap.addAttribute("message", deleteEmployeeByIdActionResultMessage);
        logger.info("MedHelper_LOGS: Result of deleteEmployeeById() action is " + deleteEmployeeByIdActionResultMessage);
        return "redirect:/admin/start-page";
    }


    /**
     * Returns an employee/employees with specified surname
     *
     * @param surname  Employee surname
     * @param modelMap ModelMap
     * @return an employee/employees with specified surname
     */
    @GetMapping("/find-employee-by-surname/{surname}")
    public String findEmployeeBySurname(@PathVariable("surname") String surname, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method findEmployeeBySurname()");
        List<EmployeeDTO> employeesFoundBySurname = adminService.findEmployeeBySurname(surname);
        if (employeesFoundBySurname != null) {
            modelMap.addAttribute("employeesFoundBySurname", employeesFoundBySurname);
            logger.info("MedHelper_LOGS: The employee(-s) with surname = " + surname + " was(were) found successfully:");
            for (EmployeeDTO empl : employeesFoundBySurname) {
                logger.info(empl.toString());
            }
        } else {
            modelMap.addAttribute("message", "There is no employee with surname = " + surname +
                    "  in database");
            logger.info("MedHelper_LOGS: There is no employee with surname = " + surname + " in database");
        }
        //*************PAGE! *********
        return null;
    }


    /**
     * Returns an employee with specified id
     *
     * @param id       Employee ID
     * @param modelMap ModelMap
     * @return an employee with specified id
     */
    @GetMapping("/find-employee-by-id/{id:\\d+}")
    public String findEmployeeById(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method findEmployeeById()");
        EmployeeDTO employeeFoundById = adminService.findEmployeeById(id);
        if (employeeFoundById != null) {
            modelMap.addAttribute("employeeFoundById", employeeFoundById);
            logger.info("MedHelper_LOGS: The employee with id = " + id + " was found successfully");
        } else {
            modelMap.addAttribute("message", "There is no employee with id = " + id + "  in database");
            logger.info("MedHelper_LOGS: There is no employee with id = " + id + " in database");
        }
//*************PAGE! *********
        return null;
    }


    /**
     * Returns page with form for editing an employee data
     *
     * @param id       Employee id to edit
     * @param modelMap ModelMap
     * @return page with form for editing an employee data
     */
    @GetMapping("/edit/{id}")
    public String editEmployeeDataForm(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method editEmployeeDataForm(), GET");
        Employee employeeToEdit = adminService.getEmployee(id);
        modelMap.addAttribute("employeeToEdit", employeeToEdit);
        return EDIT_EMPLOYEE_JSP;
    }

    /**
     * Sends edited employee's data to the database
     *
     * @param employee      employee data to edit
     * @param bindingResult the binding results
     * @param modelMap      ModelMap
     * @return page with current employee details
     */
    @PostMapping("/edit")
    public String editEmployeeData(@Valid @ModelAttribute("editEmployee") Employee employee, BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method editEmployeeData(), POST");
        if (bindingResultCheck(bindingResult, modelMap)) {
            return "error_page";
        }
        Employee editedEmployee = adminService.editEmployee(employee);
        if (editedEmployee == null) {
            logger.info("MedHelper_LOGS: Error in the editing process of the employee" + editedEmployee.toString() + ")");
            return EDIT_EMPLOYEE_JSP;
        }
        logger.info("MedHelper_LOGS: Employee edited successfully(" + editedEmployee.toString() + ")");
        modelMap.addAttribute("message", "The new employee added successfully: ");
        modelMap.addAttribute("employee", editedEmployee);
        return EMPLOYEE_DETAILS_JSP;
    }


    /**
     * Returns page with employee details
     *
     * @param id
     * @param modelMap ModelMap
     * @return page with employee details
     */
    @GetMapping("/employee-details/{id:\\d+}")
    public String seeEmployeeDetails(@PathVariable("id") int id, ModelMap modelMap) {
        if (modelMap.isEmpty()) {
            modelMap.addAttribute("employee", adminService.getEmployee(id));
        }
        return EMPLOYEE_DETAILS_JSP;
    }


    /**
     * Checks if there is any binding errors
     *
     * @param bindingResult the binding results
     * @param modelMap ModelMap
     * @return boolean result
     */
    private boolean bindingResultCheck(BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            for (Object object : bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    modelMap.addAttribute("message", "<p>Details: </p>" + fieldError.getDefaultMessage());
                    logger.debug("BindingResult Error: " + fieldError.getCode() + ". Details: " + fieldError.getDefaultMessage());
                }
                return true;
            }
        }
        return false;
    }


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
