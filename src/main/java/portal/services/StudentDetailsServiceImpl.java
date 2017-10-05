package portal.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.model.StudentDetails;
import portal.repositories.StudentDetailsRepository;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

	@Autowired
	StudentDetailsRepository studentDetailsRepository;
	
	@Override
	public StudentDetails findById(Long id) {
		return studentDetailsRepository.findByUserId(id);
	}

	@Override
	public void saveDetails(StudentDetails studentDetails) {
		studentDetailsRepository.save(studentDetails);
	}

	@Override
	public boolean findByUserId(Long UserId) {
		if(studentDetailsRepository.findByUserId(UserId) != null)
			return true;
		return false;
	}


}
