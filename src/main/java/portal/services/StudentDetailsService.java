package portal.services;

import portal.model.StudentDetails;


public interface StudentDetailsService {

	StudentDetails findById(Long id);

	void saveDetails(StudentDetails studentDetails);

	boolean findByUserId(Long id);

}
