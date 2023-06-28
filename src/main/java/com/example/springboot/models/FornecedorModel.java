package com.example.springboot.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "TB_FORNECEDOR")
public class FornecedorModel extends RepresentationModel<FornecedorModel> implements Serializable{
	private static final long serialVersionUID = 1L; 
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private UUID idFornecedor;
		private String razaoSocial; 
		private String nomeFantasia; 
		private String cnpj;
		private String endereco; 
		private String fone; 
		private String email; 
		private int inscricaoEstadual;

		public UUID getIdFornecedor() {
			return idFornecedor;
		}
		public void setIdFornecedor(UUID idFornecedor) {
			this.idFornecedor = idFornecedor;
		}
		public String getRazaoSocial() {
			return razaoSocial;
		}
		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}
		public String getNomeFantasia() {
			return nomeFantasia;
		}
		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getInscricaoEstadual() {
			return inscricaoEstadual;
		}
		public void setInscricaoEstadual(int inscricaoEstadual) {
			this.inscricaoEstadual = inscricaoEstadual;
		}	
	}