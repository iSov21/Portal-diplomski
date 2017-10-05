package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.UploadFile;

@Repository
public interface FileRepository extends JpaRepository<UploadFile, Long> {
}
