package main.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.coffeeorder;
@Transactional
public interface coffeeorderRepository extends JpaRepository<coffeeorder,Integer>{

}
