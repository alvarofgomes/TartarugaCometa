package com.tartarugacometa.model;

public class Entrega {

	private int id;
	private String status;
	private double frete;
    private Cliente remetente;
    private Cliente destinatario;
    private Endereco enderecoOrigem;
    private Endereco enderecoDestino;
	
	public Entrega(double frete) {
		this.frete = frete;
	}

	public Entrega() {
	}
	
	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getRemetente() {
		return remetente;
	}

	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}

	public Endereco getEnderecoOrigem() {
		return enderecoOrigem;
	}

	public void setEnderecoOrigem(Endereco enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	public Endereco getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(Endereco enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

	
}