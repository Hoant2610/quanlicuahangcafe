package main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.account;
import main.employee;
@Transactional
public interface employeeRepository extends JpaRepository<employee,Integer>{
	@Query("SELECT a FROM employee a ")
	List<employee> findallemployee();	
	@Query("SELECT DISTINCT e FROM employee e "
			+ "WHERE e.id_employee= :id_employee")
	employee findbyid_employee(int id_employee);
	@Modifying
	@Query("UPDATE employee e "
	       + "SET e.fullname = :fullname, e.telephone = :telephone, e.address = :address, e.role = :role "
	       + "WHERE e.id_employee = :id_employee")
	
	void updateEmployee(@Param("id_employee") int id_employee,
	                    @Param("fullname") String fullname,
	                    @Param("telephone") String telephone,
	                    @Param("address") String address,
	                    @Param("role") String role);
    @Modifying
    @Query("DELETE FROM employee a WHERE a.id_employee = :id_employee ")
    void deleteEmployeeById(int id_employee);
    @Modifying
    @Query("DELETE FROM account a WHERE a.id_employee = :id_employee ")
    void deleteaccountById(int id_employee);
    
}
