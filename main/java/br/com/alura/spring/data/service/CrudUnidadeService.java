package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
	
	private boolean system = true;
	private final UnidadeRepository unidadeRepos;
	
	public CrudUnidadeService(UnidadeRepository unidadeRepos) {
		this.unidadeRepos = unidadeRepos;
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação de Unidade deseja executar?");
			System.out.println("0-Sair");
			System.out.println("1-Salvar");
			System.out.println("2-Atualizar");
			System.out.println("3-Visualizar");
			System.out.println("4-Excluir");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				alterar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
		
	}
	
	public void salvar(Scanner scanner) {
		
		System.out.println("Digite o nome da unidade: ");
		String descricao = scanner.next();
		
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		Unidade unidade = new Unidade();
	
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		unidadeRepos.save(unidade);
		System.out.println("Salvo com sucesso! ");
		
	}
	
	public void alterar(Scanner scanner) {
		System.out.println("Id do cargo a ser alterado: ");
		Integer id = scanner.nextInt();
		
		
		System.out.println("Digite o nome da unidade: ");
		String descricao = scanner.next();
		
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		Unidade unidade = new Unidade();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeRepos.save(unidade); 
		
		System.out.println("Atualizado com sucesso! ");
	}
	
	private void visualizar(){
		Iterable<Unidade> unidades =unidadeRepos.findAll();
		unidades.forEach(unidade -> System.out.	println(unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id da unidade a ser excluído: ");
		Integer id = scanner.nextInt();
		unidadeRepos.deleteById(id);
		System.out.println("Excluído com sucesso! ");
	}

}
