package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.util.BindingCheck;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;


/**
 * Processes requests from an administrator
 *
 * @author Julia Dalskaya
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminService adminService;

    private static final String MAIN_ADMIN_JSP = "admin_main_page";
    private static final String ADD_NEW_EMPLOYEE_JSP = "admin_add_new_employee";
    private static final String EDIT_EMPLOYEE_JSP = "admin_edit_employee_page";
    private static final String EMPLOYEE_DETAILS_JSP = "admin_employee_details";
    private static final String ERROR_PAGE_JSP = "input_data_error_page";
    private static final String EMPLOYEE_FOUND_BY_SURNAME = "admin_found_by_surname";

    private static final String MESSAGE = "message";
    private static final String EMPLOYEE = "employee";


    /**
     * Returns admin page after admin sign in with all employees list on it
     *
     * @param model ModelMap model
     * @return admin_main_page.jsp to view
     */
    @GetMapping("/start-page")
    public String showAllEmployees(ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController - handler method showAllEmployees()");
        List<EmployeeShortViewDTO> allEmployeesFound = adminService.showAllEmployees();
        if (!allEmployeesFound.isEmpty()) {
            model.addAttribute("allEmployees", allEmployeesFound);
            logger.info("MedHelper_LOGS: The action showAllEmployees() completed successfully");
        } else {
            model.addAttribute(MESSAGE,
                    "INFO: There is no information about employees in the database");
            logger.info("MedHelper_LOGS: The action showAllEmployees() completed without result");
        }
        return MAIN_ADMIN_JSP;
    }


    /**
     * Returns page for new employees adding
     *
     * @return form page for new employees adding
     */
    @GetMapping("/add-employee")
    public String addEmployee() {
        logger.info("MedHelper_LOGS: In AdminController - handler method addEmployeeForm() GET");
        return ADD_NEW_EMPLOYEE_JSP;
    }


    /**
     * Is used to add a new employee to the system
     *
     * @param employeeDTO   the new employee from admin page
     * @param bindingResult the binding results
     * @param model         ModelMap model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-employee")
    public String addEmployee(
            @Valid @ModelAttribute("addEmployee") EmployeeDTO employeeDTO, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In AdminController:  handler method addEmployee()");
        if (BindingCheck.bindingResultCheck(bindingResult, model)) {
            return ERROR_PAGE_JSP;
        }
        logger.info("MedHelper_LOGS: New employee from JSP, surname = {}", employeeDTO.getSurname());
        EmployeeDTO newEmployee = adminService.addNewEmployee(employeeDTO);
        logger.info("MedHelper_LOGS: the new employee with surname = {} added successfully", newEmployee.getSurname());
        model.addAttribute(MESSAGE, "The new employee added successfully");
        model.addAttribute(EMPLOYEE, newEmployee);
        return EMPLOYEE_DETAILS_JSP;
    }


    /**
     * Returns page for editing an employee data
     *
     * @param id       Employee id to edit
     * @param modelMap ModelMap
     * @return page with form for editing an employee data if employee exists or warning message
     */
    @GetMapping("/edit/{id}")
    public String editEmployeeData(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method editEmployeeDataForm(), GET");
        EmployeeDTO employeeToEdit = adminService.getEmployeeById(id);
        if (employeeToEdit != null) {
            logger.info("MedHelper_LOGS: In AdminController: editEmployeeData()," +
                    " GET: employee with id = {} found successfully", id);
            modelMap.addAttribute("employeeToEdit", employeeToEdit);
        } else {
            logger.info("MedHelper_LOGS: In AdminController: seeEmployeeDetails(), GET: employee with id = {} not found", id);
            modelMap.addAttribute(MESSAGE, "There is no any employee with such id in the database");
        }
        return EDIT_EMPLOYEE_JSP;
    }


    /**
     * Sends edited employee's data to the database
     *
     * @param employeeDTO   employee data to edit
     * @param bindingResult the binding results
     * @param modelMap      ModelMap
     * @return page with current employee details
     */
    @PostMapping("/edit")
    public String editEmployeeData(@Valid @ModelAttribute("editEmployee") EmployeeDTO employeeDTO,
                                   BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method editEmployeeData(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        EmployeeDTO editedEmployee = adminService.editEmployee(employeeDTO);
        if (editedEmployee == null) {
            logger.info("MedHelper_LOGS: Error in the editing process of the employee");
            return EDIT_EMPLOYEE_JSP;
        }
        logger.info("MedHelper_LOGS: Employee edited successfully( surname = {} )", editedEmployee.getSurname());
        modelMap.addAttribute(MESSAGE, "Employee edited successfully");
        modelMap.addAttribute(EMPLOYEE, editedEmployee);
        return EMPLOYEE_DETAILS_JSP;
    }

    /**
     * Delete an employee by their ID
     *
     * @param employeeIdToDelete - ID of an employee to delete
     * @param redirectAttributes redirect attributes
     * @return admin_main_page.jsp to view all employees without one deleted
     */
    @PostMapping("/delete-employee")
    public RedirectView deleteEmployeeByIdRedirect(@RequestParam("employeeIdToDelete") int employeeIdToDelete,
                                                   RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In AdminController - handler method deleteEmployeeById()");
        String deleteEmployeeByIdActionResultMessage = adminService.deleteEmployeeById(employeeIdToDelete);
        logger.info("MedHelper_LOGS: Result of deleteEmployeeById() action is: {}", deleteEmployeeByIdActionResultMessage);
        RedirectView redirectView = new RedirectView("/admin/start-page", true);
        redirectAttributes.addFlashAttribute(MESSAGE, deleteEmployeeByIdActionResultMessage);
        return redirectView;
    }


    /**
     * Returns page with employee details
     *
     * @param id       employee id
     * @param modelMap ModelMap
     * @return page with employee details
     */
    @GetMapping("/employee-details/{id:\\d+}")
    public String seeEmployeeDetails(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method seeEmployeeDetails(), GET");
        EmployeeDTO employeeDetails = adminService.getEmployeeById(id);
        if (employeeDetails != null) {
            logger.info("MedHelper_LOGS: In AdminController: seeEmployeeDetails(), " +
                    "GET: employee with id = {} found successfully", id);
            modelMap.addAttribute(EMPLOYEE, employeeDetails);
        } else {
            logger.info("MedHelper_LOGS: In AdminController: seeEmployeeDetails()," +
                    " GET: employee with id = {} not found", id);
            modelMap.addAttribute("employeeID", id);
            modelMap.addAttribute(MESSAGE, "There is no data about an employee with such id in the database");
        }
        return EMPLOYEE_DETAILS_JSP;
    }


    /**
     * Returns an employee/employees with specified surname
     *
     * @param surname  Employee's surname
     * @param modelMap ModelMap
     * @return an employee/employees with specified surname
     */
    @GetMapping(value = "/find-employee-by-surname")
    public String findEmployeeBySurname(@RequestParam(value = "surname", required = false) String surname, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method findEmployeeBySurname()");
        List<EmployeeShortViewDTO> employeesFoundBySurname = adminService.findEmployeeBySurname(surname);
        if (!employeesFoundBySurname.isEmpty()) {
            modelMap.addAttribute("allEmployees", employeesFoundBySurname);
            logger.info("MedHelper_LOGS: The employee(-s) with surname = {} was(were) found successfully", surname);
        } else {
            modelMap.addAttribute(MESSAGE, "There is no employee with surname = " + surname +
                    "  in database");
            logger.info("MedHelper_LOGS: There is no employee with surname = {} in database ", surname);
        }
        return EMPLOYEE_FOUND_BY_SURNAME;
    }

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
