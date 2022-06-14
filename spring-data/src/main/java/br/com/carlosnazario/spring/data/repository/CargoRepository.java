package br.com.carlosnazario.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.carlosnazario.spring.data.orm.Cargo;

/*
 *  Classes Repositories permitem a criacao de objetos que se 
 *  comunicam com o banco de dados
 */

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
	
}
