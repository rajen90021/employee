package Employe_wise.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employe_wise.com.entity.Employee;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, String> {

}
