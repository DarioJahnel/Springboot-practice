package application.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.models.Staff;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long>{
	Staff findByDocumentNumber(String documentNumber);
}
