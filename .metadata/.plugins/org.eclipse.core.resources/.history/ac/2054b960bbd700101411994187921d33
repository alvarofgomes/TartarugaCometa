package com.tartarugacometa.controller;

import java.util.Scanner;

import com.tartarugacometa.BO.ProdutoBO;
import com.tartarugacometa.exceptions.ValidacaoException;
import com.tartarugacometa.model.Produto;

public class ProdutoController {

	Scanner sc = new Scanner(System.in);
	private ProdutoBO produtoBo = new ProdutoBO();
	
	public void cadastrarDadosDoProduto() {
		
		try {
			
			System.out.println("Digite o nome do do produto: ");
			String nomeDoProduto = sc.nextLine();
			System.out.println("Digite o peso do produto: ");
			double peso = sc.nextDouble();
			System.out.println("Digite o volume do produto: ");
			double volumeDoProduto = sc.nextDouble();
			System.out.println("Digite o valor do produto: ");
			double valorDoProduto = sc.nextDouble();
			Produto produto = new Produto(nomeDoProduto,peso,volumeDoProduto,valorDoProduto);
			produtoBo.cadastrarProdutoBO(produto);
			System.out.println("Produto cadastrado!");
			
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	public void atualizarDadosDoProduto() {
		
		try {
			
			Produto produto = new Produto();
			
			System.out.println("ID: ");
			produto.setId(sc.nextInt());
			sc.nextLine();
			System.out.println("Novo nome: ");
			produto.setNomeDoProduto(sc.nextLine());
			System.out.println("Novo peso: ");
			produto.setPeso(sc.nextDouble());
			System.out.println("Novo volume: ");
			produto.setVolume(sc.nextDouble());
			System.out.println("Novo valor: ");
			produto.setValor(sc.nextDouble());
			produtoBo.atualizarProdutoBO(produto);
			System.out.println("Produto atualizado!");
			
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
	}
	
	
	public void excluirDadosDoProduto() {

		try {
			
			System.out.println("Informe o ID:");
			int id = sc.nextInt();
			produtoBo.deletarProdutoBO(id);
			System.out.println("Produto excluído!");
			
		} catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	public void listarProdutosController() {
		
		try {
			
			System.out.println("Listando produtos cadastrados: ");
			produtoBo.listarProdutoBO().forEach(produto -> {
				System.out.println(produto.getNomeDoProduto());
			});
			
		} catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
		
	}
	
	/*
	public void exibirInfo() {
		System.out.println("PRODUTOS: ");
		System.out.printf("Nome: %s\n", this.produto.getNomeDoProduto());
		System.out.printf("Peso: %.2f\n", this.produto.getPeso());
		System.out.printf("Valor: %.2f\n",this.produto.getValor());
		System.out.printf("Volume: %.2f\n", this.produto.getVolume());
	}
	

	 
	 Calcular Frete, por enquanto não  utilizar solicitar para ser setado
	 
	 	//calculo para peso cubico para descobrir valor do frete, primeiro pega o volume e depois 
	//multiplica pelo valor do transporte aqui to supondo um transporte rodoviario.

	
	//peso real do produto com peso cubico do volume frete tem que ser calculado com base no peso do volume
	// e não peso real do produto mas o peso de quanto espaço ele ocupa
	
	public double calcularFrete(Produto produto) {
	
		double pesoCubico = produto.getVolume() * 300.0;
		//0.05 reprensenta a tarifa por kg real ou m³. de novo apenas supondo um número
		return pesoCubico * 0.05;
		
	}
	 
	*/
	
}