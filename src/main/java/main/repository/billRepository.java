package main.repository;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.bill;
import main.coffee;


@Transactional
public interface billRepository extends JpaRepository<bill,Integer>{
	@Query("SELECT DISTINCT b FROM bill b "
			+ "JOIN coffeeorder o ON o.id_order=b.id_order "
			+ "JOIN coffee_ordered co ON co.id_order=o.id_order "
			+ "JOIN coffee c ON c.id_coffee = co.id_coffee "
			+ "WHERE co.date >= :startDate AND co.date <= :endDate "
			+ "AND c.id_coffee = :id_coffee")
	List<bill> findByDate( int id_coffee,Date startDate, Date endDate);
	@Query("SELECT DISTINCT co.date FROM coffee_ordered co "
			+ "JOIN coffeeorder o ON o.id_order=co.id_order "
			+ "JOIN bill b ON b.id_order=o.id_order "
			+ "JOIN coffee c ON c.id_coffee=co.id_coffee "
			+ "WHERE co.date >= :startDate "
			+ "AND co.date <= :endDate "
			+ "AND c.id_coffee = :id_coffee "
			+ "AND b.id_bill = :id_bill")
	Date findDateById(int id_coffee,int id_bill, Date startDate, Date endDate);
	@Query("SELECT DISTINCT cl.id_client FROM client cl "
			+ "JOIN coffeeorder cforder ON cforder.id_client = cl.id_client "
			+ "JOIN coffee_ordered cfed ON cfed.id_order = cforder.id_order "
			+ "JOIN coffee c ON c.id_coffee = cfed.id_coffee "
			+ "JOIN bill b ON b.id_order = cforder.id_order "
			+ "WHERE cfed.date >= :startDate "
			+ "AND cfed.date <= :endDate "
			+ "AND c.id_coffee = :id_coffee "
			+ "AND b.id_bill = :id_bill"
			)
	int findIdKH(int id_coffee,int id_bill,Date startDate,Date endDate);
	@Query("SELECT DISTINCT cl.nameclient FROM client cl "
			+ "JOIN coffeeorder cforder ON cforder.id_client = cl.id_client "
			+ "JOIN coffee_ordered cfed ON cfed.id_order = cforder.id_order "
			+ "JOIN coffee c ON c.id_coffee = cfed.id_coffee "
			+ "JOIN bill b ON b.id_order = cforder.id_order "
			+ "WHERE cfed.date >= :startDate "
			+ "AND cfed.date <= :endDate "
			+ "AND c.id_coffee = :id_coffee "
			+ "AND b.id_bill = :id_bill"
			)
	String findNameclient(int id_coffee,int id_bill,Date startDate,Date endDate);
	@Query("SELECT DISTINCT cl.telephone FROM client cl "
			+ "JOIN coffeeorder cforder ON cforder.id_client = cl.id_client "
			+ "JOIN coffee_ordered cfed ON cfed.id_order = cforder.id_order "
			+ "JOIN coffee c ON c.id_coffee = cfed.id_coffee "
			+ "JOIN bill b ON b.id_order = cforder.id_order "
			+ "WHERE cfed.date >= :startDate "
			+ "AND cfed.date <= :endDate "
			+ "AND c.id_coffee = :id_coffee "
			+ "AND b.id_bill = :id_bill"
			)
	String findTelephoneclient(int id_coffee,int id_bill,Date startDate,Date endDate);
	@Query("SELECT DISTINCT co.quantity FROM coffee_ordered co "
		    + "JOIN coffee c ON c.id_coffee = co.id_coffee "
		    + "JOIN coffeeorder cforder ON cforder.id_order = co.id_order "
		    + "JOIN client cl ON cl.id_client = cforder.id_client "
		    + "WHERE co.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate "
		    + "AND cl.id_client = :id_client")
	int quantity(int id_coffee,int id_client,Date startDate, Date endDate);
	@Query("SELECT DISTINCT co.quantity * CAST(c.price AS long) FROM coffee_ordered co "
		    + "JOIN coffee c ON c.id_coffee = co.id_coffee "
		    + "JOIN coffeeorder cforder ON cforder.id_order = co.id_order "
		    + "JOIN client cl ON cl.id_client = cforder.id_client "
		    + "WHERE co.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate "
		    + "AND cl.id_client = :id_client")
	int totalrevenue(int id_coffee,int id_client,Date startDate, Date endDate);
}
