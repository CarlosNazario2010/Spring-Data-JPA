package br.com.carlosnazario.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.carlosnazario.spring.data.service.CrudCargoService;
import br.com.carlosnazario.spring.data.service.CrudFuncionarioService;
import br.com.carlosnazario.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.carlosnazario.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.carlosnazario.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico funcionarioDinamico;
	
	public SpringDataApplication(CrudCargoService cargoService,
			CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeService,
			RelatoriosService relatoriosService,
			RelatorioFuncionarioDinamico funcionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatoriosService = relatoriosService;
		this.funcionarioDinamico = funcionarioDinamico;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual acao executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio Dinamico");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1: 
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeService.inicial(scanner);
				break;
			case 4:
				relatoriosService.inicial(scanner);
				break;
			case 5:
				funcionarioDinamico.inicial(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
}
