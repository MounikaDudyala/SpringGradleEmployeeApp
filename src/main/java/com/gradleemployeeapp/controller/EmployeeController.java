package com.gradleemployeeapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gradleemployeeapp.modal.Employee;
import com.gradleemployeeapp.modal.Error;
import com.gradleemployeeapp.service.EmployeeService;



@Controller
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private ModelAndView fetchEmployees() throws IOException {
		ModelAndView model = new ModelAndView("list");
		List<Employee> empList = employeeService.fetchEmployees();
		model.addObject("employeeList", empList);
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	private ModelAndView newForm() {
		ModelAndView model = new ModelAndView("create");
		String requestedOption = "create";
		model.addObject("request", requestedOption);
		model.addObject("employee", new Employee());
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	private ModelAndView createEmployee(@Validated @ModelAttribute("employee")Employee employee,BindingResult result, ModelMap modal)throws IOException{
		 ModelAndView model;
		if(result.hasErrors())
		{    
			 return new ModelAndView("error");
		}
		if(employee.getFirstName()==null&&employee.getLastName()==null&&employee.getManagerId()==0) {
			model=new ModelAndView("create");
			model.addObject("request", "create");
			return model;
			
		}
		if(employee.getFirstName()==null||employee.getLastName()==null||employee.getManagerId()==0) {
			String requestedOption = "create";
			model=new ModelAndView("create");
			model.addObject("request", requestedOption);
			model.addObject("employee", employee);
			return model;
		} else {
			boolean isEmployeeCreated = employeeService.createEmployee(employee);
			if (isEmployeeCreated) {
				return new ModelAndView("redirect:/list");
			} else {
				return new ModelAndView("create");
			}
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@ResponseBody
	private ModelAndView editEmployee(@RequestParam("empId") int empId) throws IOException {
		Employee employee = employeeService.fetchEmployee(empId);
		if (employee.getEmpId() == 0) {
			ModelAndView model = new ModelAndView("create");
			model.addObject("request", "create");
			return model;
		} else {
			ModelAndView model = new ModelAndView("create");
			model.addObject("employee", employee);
			String requestedOption = "update";
			model.addObject("request", requestedOption);
			return model;
		}
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ModelAndView updateEmployee(@Validated @ModelAttribute("employee")Employee employee,BindingResult result,ModelMap modal)throws IOException{
		ModelAndView model;
		if(result.hasErrors())
		{    
			 return  new ModelAndView("error");
		}
		if(employee.getFirstName()==null&&employee.getLastName()==null&&employee.getManagerId()==0) {
			model=new ModelAndView("create");
			model.addObject("request", "create");
			return model;
			
		}
		if(employee.getFirstName()==null||employee.getLastName()==null||employee.getManagerId()==0) {
			String requestedOption = "create";
			model=new ModelAndView("create");
			model.addObject("request", requestedOption);
			model.addObject("employee", employee);
			return new ModelAndView("create");
		} else {
			boolean isEmployeeUpdated = employeeService.updateEmployee(employee);
			if (isEmployeeUpdated) {
				model=new ModelAndView("redirect:/list");
				return model;
			} else {
				Error error = new Error();
				model=new ModelAndView("errorMessage");
				error.set_message("Employee not Updated");
				model.addObject("error", error.get_message());
				return model;
			}
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	private ModelAndView deleteEmployee(@RequestParam("empId") int empId) throws IOException {
		if (empId == 0) {
			Error error = new Error();
			error.set_message("Wrong Request with EmployeeId as Null");
			ModelAndView model = new ModelAndView("error");
			model.addObject("error", error.get_message());
			return model;
		} else {
			boolean isEmployeeDeleted = employeeService.deleteEmployee(empId);
			if (isEmployeeDeleted) {
				ModelAndView model = new ModelAndView("redirect:/list");
				return model;
			} else {
				Error error = new Error();
				error.set_message("Employee not existed");
				ModelAndView model = new ModelAndView("error");
				model.addObject("error", error.get_message());
				return model;
			}
		}
	}

}
