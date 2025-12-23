package com.tartarugacometa.controller;

import java.util.List;
import java.util.Scanner;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.BO.EntregaBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Entrega;

public class EntregaController {

	Scanner sc = new Scanner(System.in);
	
    private EntregaBO entregaBo = new EntregaBO();
    private ClienteBO clienteBo = new ClienteBO();
	
	public void cadastrarEntregaController() {
		
		try {
			
			Entrega entrega = new Entrega();
			
	        System.out.println("ID do cliente que recebera a entrega: ");
	        int idCliente = sc.nextInt();
	        sc.nextLine();
	        
	        Cliente cliente = clienteBo.buscarClientePorIdBO(idCliente);
	        
	        System.out.println("Status: ");
	        entrega.setStatus(sc.nextLine());
	        System.out.println("Valor do frete: ");
	        entrega.setFrete(sc.nextDouble());
	        
	        entregaBo.cadastrarEntregaBO(entrega);
	        System.out.println("Entrega cadastrada cim sucesso!");
			
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	public void atualizarEntregaController() {
		
		try {
			
			Entrega entrega = new Entrega();
			
			System.out.println("ID da entrega a atualizar: ");
			entrega.setId(sc.nextInt());
			sc.nextLine();
			
			System.out.println("Novo status: ");
			entrega.setStatus(sc.nextLine());
			System.out.println("Novo valor do frete: ");
			entrega.setFrete(sc.nextDouble());
			
			entregaBo.atualizarEntregaBO(entrega);
			System.out.println("Entrega atualizada com sucesso!");
			
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	public void excluirEntregaController() {
		
		try {
			
			System.out.println("ID da entrega para excluir: ");
			int id = sc.nextInt();
			
			entregaBo.deletarEntregaBO(id);
			System.out.println("Entrega excluida com sucesso!");
			
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	public void listarEntregaPorClienteController(int id) {
		
		try {
			
			System.out.println("Listando Entregas: ");
			
			List<Entrega> entregas = entregaBo.listarEntregasPorClienteBO(id);
			
			entregas.forEach(entrega -> 
						System.out.println(entrega.getId() + " - " + entrega.getStatus()));
			
		} catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
}