package portal.services;

import portal.model.EmployerDetails;

public interface EmployerDetailsService {

	EmployerDetails findById(Long id);

	void saveDetails(EmployerDetails employerDetails);

	boolean findByUserId(Long UserId);

}
