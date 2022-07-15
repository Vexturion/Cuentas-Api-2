package com.cuentas.main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentas.main.entities.entity.Cuenta;

public interface CuentaDAO extends JpaRepository<Cuenta, Integer> {

	
	
}
