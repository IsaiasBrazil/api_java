package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dtos.FornecedorRecordDto;
import com.example.springboot.models.FornecedorModel;
import com.example.springboot.repositories.FornecedorRepository;

import jakarta.validation.Valid;

@RestController
public class FornecedorController {
	@Autowired
	FornecedorRepository fornecedorRepository;

	@GetMapping("/fornecedores")
	public ResponseEntity<List<FornecedorModel>> getAllFornecedores() {
		List<FornecedorModel> fornecedoresList = fornecedorRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(fornecedoresList);
	}

	@GetMapping("/fornecedores/{id}")
	public ResponseEntity<Object> getOneFornecedor(@PathVariable(value = "id") UUID id) {
		Optional<FornecedorModel> fornecedorO = fornecedorRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(fornecedorO.get());
	}

	@PostMapping("/fornecedores")
	public ResponseEntity<FornecedorModel> saveFornecedor(@RequestBody @Valid FornecedorRecordDto FornecedorRecordDto) {
		var FornecedorModel = new FornecedorModel();
		BeanUtils.copyProperties(FornecedorRecordDto, FornecedorModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorRepository.save(FornecedorModel));
	}

	@DeleteMapping("/fornecedores/{id}")
	public ResponseEntity<Object> deleteFornecedor(@PathVariable(value = "id") UUID id) {
		Optional<FornecedorModel> fornecedorO = fornecedorRepository.findById(id);
		if (fornecedorO.isPresent()) {
			fornecedorRepository.delete(fornecedorO.get());
			return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deleted successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor not found.");
		}
	}

	@PutMapping("/fornecedores/{id}")
	public ResponseEntity<Object> updateFornecedor(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid FornecedorRecordDto FornecedorRecordDto) {
		Optional<FornecedorModel> fornecedorO = fornecedorRepository.findById(id);

		if (fornecedorO.isPresent()) {
			FornecedorModel fornecedorModel = fornecedorO.get();
			BeanUtils.copyProperties(FornecedorRecordDto, fornecedorModel);
			fornecedorModel.setIdFornecedor(id); // Garante que o ID seja mantido durante a atualização

			return ResponseEntity.status(HttpStatus.OK).body(fornecedorRepository.save(fornecedorModel));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor not found.");
		}
	}
}