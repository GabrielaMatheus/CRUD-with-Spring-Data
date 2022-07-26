package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação de Cargo deseja executar?");
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
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo com sucesso! ");
		
	}
	
	public void alterar(Scanner scanner) {
		System.out.println("Id do cargo a ser alterado: ");
		Integer id = scanner.nextInt();
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo); //o save também updata 
		
		System.out.println("Atualizado com sucesso! ");
	}
	
	private void visualizar(){
		Iterable<Cargo> cargos =cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id do cargo a ser excluído: ");
		Integer id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Excluído com sucesso! ");
	}

}
