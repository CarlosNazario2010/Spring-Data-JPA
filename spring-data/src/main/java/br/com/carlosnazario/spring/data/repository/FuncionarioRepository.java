package br.com.carlosnazario.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.carlosnazario.spring.data.orm.Funcionario;
import br.com.carlosnazario.spring.data.orm.FuncionarioProjecao;

/*
 * Derived Queries - queries criadas através de comandos Java 
 * JPQL - queries criadas através de uma estrutura SQL, porém com os nomes das entidades Java
 * Native Query - queries padrões SQL que conseguimos executar no nosso Client SQL
 * CrudRepository - permite a criacao dos metodos CRUD
 * PagingAndSortingRepository permite a paginacao na aplicacao
 * JpaSpecificationExecutor permite consultas dinamicas com especificacoes
 */

@Repository
public interface FuncionarioRepository extends 
		PagingAndSortingRepository<Funcionario, Integer>,
		JpaSpecificationExecutor<Funcionario>{

	// usando derivated query
	List<Funcionario>findByNome(String nome);
	
	// usando derivated query
	List<Funcionario>findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	// usando derivated query
	List<Funcionario> findByCargoDescricao(String descricao);
	
	// usando JPQL	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario "
			+ "AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	// usando JPQL
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c "
			+ "WHERE c.descricao = :descricao")
	List<Funcionario> BuscaCargoPelaDescricao(String descricao);
	
	// usando natived query
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :dataContratacao",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate dataContratacao);
	
	// usando paginacao
    List<Funcionario> findByNome(String nome, Pageable pageable);
    
    // usando projecao
    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", 
    		nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}
