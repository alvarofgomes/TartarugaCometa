package com.tartarugacometa.controller;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;

import java.util.Scanner;

public class ClienteController {

    private Scanner sc = new Scanner(System.in);
    private ClienteBO clienteBo = new ClienteBO();

    public void cadastrarClienteController() {
    	
    	try {
			
            System.out.println("Digite o nome: ");
            String nome = sc.nextLine();
            System.out.println("Digite CPF/CNPJ: ");
            String cpfcnpj = sc.nextLine();
            Cliente cliente = new Cliente(nome, cpfcnpj);
            clienteBo.cadastrarClienteBO(cliente);
            System.out.println("Cliente cadastrado!");
    		
		} catch (ValidacaoException  e) {
			System.out.println("Erro de validação: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
        
    }

    public void atualizarClienteController() {
    	
    	try {
    		
            Cliente cliente = new Cliente();
            System.out.println("ID: ");
            cliente.setId(sc.nextInt());
            sc.nextLine();
            System.out.println("Novo nome: ");
            cliente.setNome(sc.nextLine());
            System.out.println("Novo CPF/CNPJ: ");
            cliente.setCpfCnpj(sc.nextLine());
            clienteBo.atualizarClienteBO(cliente);
            System.out.println("Cliente atualizado!");
            
		} catch (ValidacaoException  e) {
			System.out.println("Erro de validação: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
        
    }
  
    public void excluirClienteController() {
    	
        try {
            System.out.println("Informe o ID:");
            int id = sc.nextInt();
            sc.nextLine(); 
            clienteBo.deletarClienteBO(id);
            System.out.println("Cliente excluído!");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        
    }
    
    public void listarClientesController() {
    	
        try {
        	
	       System.out.println("Listando clientes cadastrados: ");
	       clienteBo.listarClientesBO().forEach(cliente -> {
	           System.out.println(cliente.getNome());
	       });

        } catch(Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        
    }

    public void buscarClientePorIdController() {
        try {
            System.out.println("Informe o ID do cliente:");
            int id = sc.nextInt();
            sc.nextLine();

            Cliente cliente = clienteBo.buscarClientePorIdBO(id);

            if (cliente != null) {
                System.out.println("Cliente encontrado:");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF/CNPJ: " + cliente.getCpfCnpj());
            } else {
                System.out.println("Nenhum cliente encontrado com esse ID.");
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
    
}