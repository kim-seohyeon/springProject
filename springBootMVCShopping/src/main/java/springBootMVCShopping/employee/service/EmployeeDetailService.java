package springBootMVCShopping.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.EmployeeDTO;
import springBootMVCShopping.repository.EmployeeRepository;

@Service
public class EmployeeDetailService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	public void execute(Model model, String empNum) {
		
		EmployeeDTO dto = employeeRepository.empSelectOne(empNum);
		model.addAttribute("employeeCommand", dto);
		
	}

}
