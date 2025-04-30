package springBootMVCShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.repository.DulicationRepository;

@Service
public class UserEmailCheckService {
	@Autowired
	DulicationRepository dulicationRepository; 
	public String execute(String userEmail) {
		return dulicationRepository.emailCheckSelectOne(userEmail);
	}
}
