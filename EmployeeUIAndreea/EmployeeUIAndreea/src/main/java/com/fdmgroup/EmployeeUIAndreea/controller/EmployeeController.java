package com.fdmgroup.EmployeeUIAndreea.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.EmployeeUIAndreea.model.Employee;
import com.fdmgroup.EmployeeUIAndreea.service.FeignClientEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controller class for managing employee-related operations.
 */
@Controller
public class EmployeeController {

	private FeignClientEmployeeService employeeService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Constructor for EmployeeController.
	 *
	 * @param employeeService the FeignClientEmployeeService instance
	 */
	public EmployeeController(FeignClientEmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	/**
	 * Retrieves all employees and renders the view.
	 *
	 * @param model the Model object
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Get all employees",
		description = "This API endpoint retrieves all employees.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@RequestMapping("allEmployees")
	public String allEmployees(Model model) {
		logger.info("Entering allEmployees() method");
		model.addAttribute("listOfEmployees", employeeService.getAllEmployees());
		logger.info("Exiting allEmployees() method");
		return "all-employees";
	}

	/**
	 * Renders the view for adding an employee.
	 *
	 * @param model the Model object
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Add an employee",
		description = "This API endpoint renders the view for adding an employee.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@RequestMapping("addEmployee")
	public String addEmployee(Model model) {
		logger.info("Entering addEmployee() method");
		model.addAttribute("employee", new Employee());
		logger.info("Exiting addEmployee() method");
		return "add-employee";
	}

	/**
	 * Submits employee data for addition.
	 *
	 * @param employee the Employee object to add
	 * @return a forward URL for redirecting to the allEmployees endpoint
	 */
	@Operation(
		summary = "Submit employee data",
		description = "This API endpoint submits employee data for addition.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@PostMapping("addEmployeeSubmit")
	public String addBookSubmit(@ModelAttribute Employee employee) {
		logger.info("Entering addBookSubmit() method");
		employeeService.createEmployee(employee);
		logger.info("Exiting addBookSubmit() method");
		return "forward:/allEmployees";
	}

	/**
	 * Renders the view for editing an employee.
	 *
	 * @param model the Model object
	 * @param id the ID of the employee to edit
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Edit an employee",
		description = "This API endpoint renders the view for editing an employee.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@GetMapping("editEmployee")
	public String editEmployee(Model model, @RequestParam long id) {
		logger.info("Entering editEmployee() method");
		model.addAttribute("employee", employeeService.getById(id));
		logger.info("Exiting editEmployee() method");
		return "edit-employee";
	}

	/**
	 * Submits edited employee data.
	 *
	 * @param employee the Employee object with edited data
	 * @return a forward URL for redirecting to the allEmployees endpoint
	 */
	@Operation(
		summary = "Submit edited employee data",
		description = "This API endpoint submits edited employee data.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@PostMapping("editEmployeeSubmit")
	public String editEmployeeSubmit(@ModelAttribute Employee employee) {
		logger.info("Entering editEmployeeSubmit() method");
		employeeService.updateEmployee(employee.getId(), employee);
		logger.info("Exiting editEmployeeSubmit() method");
		return "forward:/allEmployees";
	}

	/**
	 * Deletes an employee by ID.
	 *
	 * @param id the ID of the employee to delete
	 * @return a forward URL for redirecting to the allEmployees endpoint
	 */
	@Operation(
		summary = "Delete an employee",
		description = "This API endpoint deletes an employee by ID.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@GetMapping("deleteEmployee")
	public String deleteEmployeeById(@RequestParam long id) {
		logger.info("Entering deleteEmployeeById() method");
		employeeService.deleteEmployee(id);
		logger.info("Exiting deleteEmployeeById() method");
		return "forward:/allEmployees";
	}

	/**
	 * Searches for an employee by ID and renders the corresponding view.
	 *
	 * @param id the ID of the employee to search for
	 * @param model the Model object
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Search for an employee by ID",
		description = "This API endpoint searches for an employee by ID and renders the corresponding view.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			),
			@ApiResponse(
				responseCode = "404",
				description = "Employee not found",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@GetMapping("/searchEmployee")
	public String searchEmployee(@RequestParam("id") long id, Model model) {
		logger.info("Entering searchEmployee() method");
		Employee employee = employeeService.getById(id);
		if (employee != null) {
			model.addAttribute("employee", employee);
			logger.info("Exiting searchEmployee() method with employee found");
			return "search-result";
		} else {
			model.addAttribute("errorMessage", "Employee not found");
			logger.info("Exiting searchEmployee() method with employee not found");
			return "not-found";
		}
	}

	/**
	 * Searches for employees by full name and renders the corresponding view.
	 *
	 * @param model the Model object
	 * @param searchInput the search input (full name)
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Search for employees by full name",
		description = "This API endpoint searches for employees by full name and renders the corresponding view.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			),
			@ApiResponse(
				responseCode = "404",
				description = "No matching employees found",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@GetMapping("/search-employees")
	public String searchEmployees(Model model, @RequestParam("searchInput") String searchInput) {
		logger.info("Entering searchEmployees() method");
		List<Employee> employees = employeeService.findByFullNameContainsIgnoreCase(searchInput);
		if (!employees.isEmpty()) {
			model.addAttribute("employee", employees);
			logger.info("Exiting searchEmployees() method with matching employees found");
			return "search-result";
		} else {
			model.addAttribute("errorMessage", "No matching employees found");
			logger.info("Exiting searchEmployees() method with no matching employees found");
			return "not-found";
		}
	}

	/**
	 * Handles the search employees form submission.
	 *
	 * @param model the Model object
	 * @param searchInput the search input (full name)
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Handle search employees form submission",
		description = "This API endpoint handles the search employees form submission.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			),
			@ApiResponse(
				responseCode = "404",
				description = "No matching employees found",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@PostMapping("/search-employees")
	public String handleSearchEmployees(Model model, @RequestParam("searchInput") String searchInput) {
		logger.info("Entering handleSearchEmployees() method");
		List<Employee> employees = employeeService.findByFullNameContainsIgnoreCase(searchInput);
		if (!employees.isEmpty()) {
			model.addAttribute("employee", employees);
			logger.info("Exiting handleSearchEmployees() method with matching employees found");
			return "search-result";
		}
		logger.info("Exiting handleSearchEmployees() method with no matching employees found");
		return "not-found";
	}

	/**
	 * Submits the search employees form and renders the corresponding view.
	 *
	 * @param model the Model object
	 * @param searchInput the search input (full name)
	 * @return the name of the view to render
	 */
	@Operation(
		summary = "Submit search employees form",
		description = "This API endpoint submits the search employees form and renders the corresponding view.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			),
			@ApiResponse(
				responseCode = "404",
				description = "No matching employees found",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@PostMapping("/search-employees-submit")
	public String searchEmployeesSubmit(Model model, @RequestParam("searchInput") String searchInput) {
		logger.info("Entering searchEmployeesSubmit() method");
		List<Employee> employees = employeeService.findByFullNameContainsIgnoreCase(searchInput);
		if (!employees.isEmpty()) {
			model.addAttribute("employee", employees);
			logger.info("Exiting searchEmployeesSubmit() method with matching employees found");
			return "search-result";
		}
		logger.info("Exiting searchEmployeesSubmit() method with no matching employees found");
		return "not-found";
	}

	/**
	 * Retrieves and displays employee details based on the provided employee ID.
	 *
	 * @param model the model object to hold the employee data
	 * @param id    the ID of the employee to retrieve
	 * @return the view name for displaying the employee details
	 */
	@Operation(
		summary = "Show employee details",
		description = "Retrieves and displays employee details based on the provided employee ID.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content = @Content(mediaType = "text/html")
			),
			@ApiResponse(
				responseCode = "404",
				description = "Employee not found",
				content = @Content(mediaType = "text/html")
			)
		}
	)
	@GetMapping("showEmployee")
	public String showEmployee(Model model, @RequestParam long id) {
	    logger.info("Entering showEmployee() method");
	    Employee employee = employeeService.getById(id);
	    if (employee != null) {
	        model.addAttribute("employee", employee);
	        logger.info("Exiting showEmployee() method with employee found");
	        return "show-employee";
	    } else {
	        model.addAttribute("errorMessage", "Employee not found");
	        logger.info("Exiting showEmployee() method with employee not found");
	        return "not-found";
	    }
	}

	
}
