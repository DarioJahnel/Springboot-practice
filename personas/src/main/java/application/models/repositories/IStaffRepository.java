package application.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import application.models.Staff;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long> {

	Staff findByDocumentNumber(String documentNumber);

	@Query("SELECT s FROM Staff s WHERE s.deleted <> 1")
	List<Staff> findAllNonDeletedStaff();

	@Query("SELECT s FROM Staff s WHERE s.deleted <> 1 AND s.documentNumber = :documentNumber")
	Staff findNonDeletedStaffByDocumentNumber(String documentNumber);
}
