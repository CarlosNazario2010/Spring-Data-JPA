package br.com.carlosnazario.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.carlosnazario.spring.data.orm.Funcionario;
import br.com.carlosnazario.spring.data.orm.FuncionarioProjecao;
import br.com.carlosnazario.spring.data.repository.FuncionarioRepository;

/*
 *  Classes com a anotacao @Service executam as acoes entre o banco de 
 *  dados e os controllers. Sao semelhantes as actions nos Servlets
 */

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de relatorios deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario por nome");
			System.out.println("2 - Busca funcionario por nome, data de contratacao e salario maior que: ");
			System.out.println("3 - Busca funcionario por data de contratacao maior que: ");
			System.out.println("4 - Pesquisa funcionario por salario");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacaoMaior(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}	
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome do funcionario");
		String nome = scanner.next();
		
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite o nome do funcionario");
		String nome = scanner.next();

		System.out.println("Digite a data de contratacao do funcionario");
		String dataContratacao = scanner.next();
		
		System.out.println("Digite o salario do funcionario");
		Double salario = scanner.nextDouble();
		
		LocalDate localDate = LocalDate.parse(dataContratacao, formatter);
		
		List<Funcionario> lista = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		lista.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacaoMaior(Scanner scanner) {
		System.out.println("Digite a data para buscar as datas de contratacao posteriores");
		String dataContratacao = scanner.next();
		LocalDate localDate = LocalDate.parse(dataContratacao, formatter);
		
		List<Funcionario> lista = funcionarioRepository
				.findDataContratacaoMaior(localDate);
		lista.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
		lista.forEach(f -> System.out.println("Id do funcionario: " + f.getId() 
			+ " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
}
