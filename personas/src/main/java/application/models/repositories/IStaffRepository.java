package application.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.models.Staff;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long>{

}
