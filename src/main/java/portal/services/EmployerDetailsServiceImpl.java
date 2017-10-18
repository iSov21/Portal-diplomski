package portal.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.model.EmployerDetails;
import portal.repositories.EmployerDetailsRepository;

@Service
public class EmployerDetailsServiceImpl implements EmployerDetailsService {

	@Autowired
	EmployerDetailsRepository employerDetailsRepository;

	@Override
	public EmployerDetails findById(Long id) {
		return employerDetailsRepository.findByUserId(id);
	}

	@Override
	public void saveDetails(EmployerDetails employerDetails) {
		employerDetailsRepository.save(employerDetails);
		
	}

	@Override
	public boolean findByUserId(Long UserId) {
		if(employerDetailsRepository.findByUserId(UserId) != null)
			return true;
		return false;
	}
}
