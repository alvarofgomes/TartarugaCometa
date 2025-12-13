package com.tartarugacometa.controller;

import java.util.Scanner;

import com.tartarugacometa.BO.ClienteBO;
import com.tartarugacometa.BO.EnderecoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Cliente;
import com.tartarugacometa.model.Endereco;

public class EnderecoController {

    Scanner sc = new Scanner(System.in);

    private EnderecoBO endercoBo = new EnderecoBO();
    private ClienteBO clienteBo = new ClienteBO();

    public void cadastrarEnderecoController() {
    	
    	try {
			
            Endereco endereco = new Endereco();

            System.out.println("ID do cliente que receber� o endere�o: ");
            int idCliente = sc.nextInt();
            sc.nextLine();

            Cliente cliente = clienteBo.buscarClientePorIdBO(idCliente);
            endereco.setCliente(cliente);

            System.out.println("Rua: ");
            endereco.setRua(sc.nextLine());
            System.out.println("N�mero: ");
            endereco.setNumero(sc.nextInt());
            sc.nextLine();
            System.out.println("Bairro: ");
            endereco.setBairro(sc.nextLine());
            System.out.println("Cidade: ");
            endereco.setCidade(sc.nextLine());
            System.out.println("Estado: ");
            endereco.setEstado(sc.nextLine());
            System.out.println("CEP: ");
            endereco.setCep(sc.nextLine());

            endercoBo.cadastrarEnderecoBO(endereco);
            System.out.println("Endere�o cadastrado com sucesso!");
    		
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    	
    }

    public void atualizarEnderecoController() {
    	
    	try {
			
            Endereco endereco = new Endereco();

            System.out.println("ID do endere�o a atualizar: ");
            endereco.setId(sc.nextInt());
            sc.nextLine();

            System.out.println("Nova Rua: ");
            endereco.setRua(sc.nextLine());
            System.out.println("Novo N�mero: ");
            endereco.setNumero(sc.nextInt());
            sc.nextLine();
            System.out.println("Novo Bairro: ");
            endereco.setBairro(sc.nextLine());
            System.out.println("Nova Cidade: ");
            endereco.setCidade(sc.nextLine());
            System.out.println("Novo Estado: ");
            endereco.setEstado(sc.nextLine());
            System.out.println("Novo CEP: ");
            endereco.setCep(sc.nextLine());

            endercoBo.atualizarEnderecoBO(endereco);
            System.out.println("Endere�o atualizado com sucesso!");
            
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void excluirEnderecoController() {

    	try {
			
            System.out.println("ID do endere�o para excluir: ");
            int id = sc.nextInt();

            endercoBo.deletarEnderecoBO(id);
            System.out.println("Endere�o exclu�do com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
    
    public void listarEnderecosController() {
    	
    	try {

    		System.out.println("Listando endereços cadastrados: ");
        	endercoBo.listarEnderecoBO()
                .forEach(endereco ->
                    System.out.println(endereco.getId() + " - " + endereco.getRua() + " - " + endereco.getCidade() + " - " + endereco.getEstado()));
    		
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    	
    }
}
