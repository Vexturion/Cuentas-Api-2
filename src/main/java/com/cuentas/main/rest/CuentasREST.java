package com.cuentas.main.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuentas.main.DAO.CuentaDAO;
import com.cuentas.main.entities.entity.Cuenta;
;

@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
@RestController
@RequestMapping("/cuentas")
public class CuentasREST {

	
	@Autowired
	private CuentaDAO cuentaDAO;
	
	// Consultar todas las cuentas
	@GetMapping
	public ResponseEntity<List<Cuenta>> getCuenta(){
		List<Cuenta> cuentas = cuentaDAO.findAll();
		return ResponseEntity.ok(cuentas);
	}
	
	// Consultar por Id
	@GetMapping(value="{cuentaId}")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable("cuentaId") Integer cuentaId){
		Optional<Cuenta> optionalCuenta= cuentaDAO.findById(cuentaId);
		if (optionalCuenta.isPresent()) {
			return ResponseEntity.ok(optionalCuenta.get());
		} else {
		       return ResponseEntity.noContent().build();
	}
	}
	
	// insertar nueva cuenta
	@PostMapping
	public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta){
		Cuenta newCuenta = cuentaDAO.save(cuenta);
		return ResponseEntity.ok(newCuenta);
	}
	
	//borrar cuenta
	@DeleteMapping(value = "{cuentaId}")
	public ResponseEntity<Void> deleteCuenta(@PathVariable("cuentaId") Integer cuentaId){
		cuentaDAO.deleteById(cuentaId);
		return ResponseEntity.ok(null);
	}
	
	// actualizar producto
	@PutMapping(value = "{cuentaId}")
	public ResponseEntity<Cuenta> updateCuenta(@PathVariable("cuentaId")Integer cuentaId, @RequestBody Cuenta cuenta){
		Optional<Cuenta> optionalCuenta = cuentaDAO.findById(cuentaId);
		if (optionalCuenta.isPresent()) {
			Cuenta updateCuenta = optionalCuenta.get();
			updateCuenta.setNombre(cuenta.getNombre());
			updateCuenta.setApellido(cuenta.getApellido());
			updateCuenta.setCorreo(cuenta.getCorreo());
			cuentaDAO.save(updateCuenta);
			
			return ResponseEntity.ok(updateCuenta);
		} else {
		       return ResponseEntity.notFound().build();
	}
	}
	
	// actualizar nombre
	@PatchMapping(value = "{cuentaId}")
	public ResponseEntity<Cuenta> updateCuentaNombre(@PathVariable("cuentaId")Integer cuentaId, @RequestBody Cuenta cuenta){
		Optional<Cuenta> optionalCuenta = cuentaDAO.findById(cuentaId);
		if (optionalCuenta.isPresent()) {
			Cuenta updateCuenta = optionalCuenta.get();
			updateCuenta.setNombre(cuenta.getNombre());
			cuentaDAO.save(updateCuenta);
			
			return ResponseEntity.ok(updateCuenta);
		} else {
		       return ResponseEntity.notFound().build();
	}
	}
	
	
	
	@RequestMapping(value = "hello" , method =RequestMethod.GET)
	public String hello() {
		return "Hola mundo";
	}
	
}
