package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

	@Service
public class RelatoriosService {
		
	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação de Cargo deseja executar?");
			System.out.println("0-Sair");
			System.out.println("1-Buscar funcionário pelo nome");
			System.out.println("2-Buscar funcionário pelo nome,data contratacao e salario");
			System.out.println("3-Buscar funcionário data contratação");
			System.out.println("4-Pesquisa funcionário salário");

			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioPeloNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioPorDataContratacao(scanner);
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

	private void buscaFuncionarioPeloNome(Scanner scanner) {
		System.out.println("Qual o nome que deseja pesquisar: ");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual o nome que deseja pesquisar: ");
		String nome = scanner.next();
		
		System.out.println("Qual data contratacao de seja pesquisar: ");
		String data = scanner.next();
		LocalDate lodalDate = LocalDate.parse(data,formatter);
		
		System.out.println("Qual salario");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoSalarioMaior(nome, salario, lodalDate);
		list.forEach(System.out::println);
		
	}
	
	private void buscaFuncionarioPorDataContratacao(Scanner scanner) {
		System.out.println("Qual data contratacao de seja pesquisar: ");
		String data = scanner.next();
		LocalDate lodalDate = LocalDate.parse(data,formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(lodalDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list= funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: Id: " + f.getId()
		+ " | nome:" + f.getNome() + " | salario: " + f.getSalario()));
	}

}
