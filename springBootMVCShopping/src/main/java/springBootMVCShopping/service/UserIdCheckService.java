package springBootMVCShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.repository.DulicationRepository;


@Service
public class UserIdCheckService {
	@Autowired
	DulicationRepository dulicationRepository; 
	public String execute(String userId) {
		return dulicationRepository.idCheckSelectOne(userId);
	}
}
