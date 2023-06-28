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

import com.example.springboot.dtos.ClienteRecordDto;
import com.example.springboot.models.ClienteModel;
import com.example.springboot.models.FornecedorModel;
import com.example.springboot.repositories.ClienteRepository;

import jakarta.validation.Valid;

@RestController
public class ClienteController {
	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteModel>> getAllClientes() {
		List<ClienteModel> clientesList = clienteRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientesList);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Object> getOneCliente(@PathVariable(value = "id") UUID id) {
		Optional<ClienteModel> clienteO = clienteRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(clienteO.get());
	}

	@PostMapping("/clientes")
	public ResponseEntity<ClienteModel> saveCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
		var clienteModel = new ClienteModel();
		BeanUtils.copyProperties(clienteRecordDto, clienteModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id) {
		Optional<ClienteModel> clienteO = clienteRepository.findById(id);
		if (clienteO.isPresent()) {
			clienteRepository.delete(clienteO.get());
			return ResponseEntity.status(HttpStatus.OK).body("Cliente deleted successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
		Optional<ClienteModel> clienteO = clienteRepository.findById(id);
		if (clienteO.isPresent()) {
			ClienteModel clienteModel = clienteO.get();
			BeanUtils.copyProperties(clienteRecordDto, clienteModel);
			clienteModel.setIdCliente(id); // Garante que o ID seja mantido durante a atualização

			return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteModel));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
		}
	}

}