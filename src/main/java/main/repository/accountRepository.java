package main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.account;
@Transactional
public interface accountRepository extends JpaRepository<account,Integer>{
	@Query("SELECT COUNT(a) FROM account a "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password"
			)
	int findaccount(String username,String password);
	@Query("SELECT DISTINCT e.fullname FROM employee e "
			+ "JOIN account a on a.id_employee=e.id_employee "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password")
	String findfullname(String username,String password);
	@Query("SELECT DISTINCT a.role FROM account a "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password")
	String findrole(String username,String password);
	@Query("SELECT DISTINCT e.id_employee FROM employee e "
			+ "JOIN account a on a.id_employee=e.id_employee "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password")
	String findid(String username,String password);
	@Query("SELECT a FROM account a ")
	List<account> findallaccount();	
	@Query("SELECT DISTINCT e.address FROM employee e "
			+ "JOIN account a on a.id_employee=e.id_employee "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password")
	String findaddress(String username,String password);
	@Query("SELECT DISTINCT e.telephone FROM employee e "
			+ "JOIN account a on a.id_employee=e.id_employee "
			+ "WHERE a.username = :username "
			+ "AND a.password = :password")
	String findtelephone(String username,String password);
	@Query("SELECT DISTINCT acc FROM account acc WHERE acc.id_account = :id_account")
	account findaccbyid(int id_account);
    @Modifying
    @Query("DELETE FROM account a WHERE a.id_account = :id_account ")
    void deleteaccountById(int id_account);
    @Modifying
	@Query("UPDATE account e "
	       + "SET e.username = :username, e.password = :password, "
	       + "e.role = :role, e.id_employee = :id_employee "
	       + "WHERE e.id_account = :id_account")
	
	void updateAccount(@Param("id_account") int id_account,
	                    @Param("username") String username,
	                    @Param("password") String password,
	                    @Param("role") String role,
	                    @Param("id_employee") int id_employee);
	@Query("SELECT COUNT(a) FROM account a "
			+ "WHERE a.username = :username ")
	int findusername(String username);
}
