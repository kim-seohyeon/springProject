package springBootMVCShopping.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.command.EmployeeCommand;
import springBootMVCShopping.repository.EmployeeRepository;

@Service
public class EmployeeAutoNumService {

	@Autowired
	EmployeeRepository employeeRepository;
	public void execute(Model model) {
		
		String empNum = employeeRepository.empNumAutoSelect();
		
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(empNum);
		
		model.addAttribute("employeeCommand", employeeCommand);
	}
}
