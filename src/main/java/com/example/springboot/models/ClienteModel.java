package com.example.springboot.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;


import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable{
	private static final long serialVersionUID = 1L; 
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private UUID idCliente;
		private String nome;
		private String endereco;
		private String fone;
		private String estadoCivil;
		
		public UUID getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(UUID idCliente) {
			this.idCliente = idCliente;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getFone() {
			return fone;
		}
		public void setFone(String fone) {
			this.fone = fone;
		}
		public String getEstadoCivil() {
			return estadoCivil;
		}
		public void setEstadoCivil(String estadoCivil) {
			this.estadoCivil = estadoCivil;
		}		
	}