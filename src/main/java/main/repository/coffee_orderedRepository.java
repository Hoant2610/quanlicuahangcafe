package main.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.coffee_ordered;
@Transactional
public interface coffee_orderedRepository extends JpaRepository<coffee_ordered,Integer>{

}
