package main.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.coffee;


@Transactional
public interface coffeeRepository extends JpaRepository<coffee,Integer>{
	@Query("SELECT a FROM coffee a ")
	List<coffee> findallcoffee();	
	@Query("SELECT  a FROM coffee a "
			+ "JOIN coffee_ordered co ON a.id_coffee = co.id_coffee "
			+ "WHERE co.date >= :startDate AND co.date <= :endDate")
	List<coffee> findAllByDate(Date startDate,Date endDate);
	@Query("SELECT DISTINCT e FROM coffee e "
			+ "WHERE e.id_coffee= :id_coffee")
	coffee findbyid_coffee(int id_coffee);
	@Modifying
	@Query("UPDATE coffee e "
	       + "SET e.namecoffee = :namecoffee, e.price = :price, e.describe = :describe "
	       + "WHERE e.id_coffee = :id_coffee")
	
	void updateCoffee(  @Param("id_coffee") int id_coffee,
						@Param("namecoffee") String namecoffee,
	                    @Param("price") int price,
	                    @Param("describe") String describe);
    @Modifying
    @Query("DELETE FROM coffee e WHERE e.id_coffee = :id")
    void deleteCoffeeById(int id);
	@Query("SELECT DISTINCT c FROM coffee c "
			+ "JOIN coffee_ordered co ON co.id_coffee=c.id_coffee "
			+ "WHERE co.date >= :startDate AND co.date <= :endDate")
	List<coffee> findByDate( Date startDate, Date endDate);
	@Query("SELECT DISTINCT COUNT(co.id_coffee_ordered) FROM coffee_ordered co "
		    + "JOIN coffee c ON c.id_coffee = co.id_coffee "
		    + "WHERE co.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate")
	int totalturn(int id_coffee, Date startDate, Date endDate);
	@Query("SELECT DISTINCT SUM(co.id_coffee_ordered) * CAST(:price AS long) FROM coffee_ordered co "
		    + "JOIN coffee c ON c.id_coffee = co.id_coffee "
		    + "WHERE co.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate")
		long totalRevenue(int id_coffee, int price, Date startDate, Date endDate);
	@Query("SELECT DISTINCT SUM(c.price * co.quantity) FROM coffee c "
			+ "JOIN coffee_ordered co ON c.id_coffee = co.id_coffee "
			+ "WHERE c.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate")
	long totalRevenue(int id_coffee, Date startDate, Date endDate);
	@Query("SELECT DISTINCT SUM(co.quantity) FROM coffee_ordered co "
		    + "JOIN coffee c ON c.id_coffee = co.id_coffee "
		    + "WHERE c.id_coffee = :id_coffee "
		    + "AND co.date >= :startDate "
		    + "AND co.date <= :endDate")
	int quantity(int id_coffee, Date startDate, Date endDate);
}
