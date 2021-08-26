package com.luchoauth.springbootauth.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luchoauth.springbootauth.controller.ITipoDocumentoController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/tipodocumento")
public class RestTipoDocumentoService extends RestValidatorHeader{

	private static Logger logger = LoggerFactory.getLogger(RestTipoDocumentoService.class);

	@Autowired
	private ITipoDocumentoController tipoDocumentoController;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarTipoDocumentos(HttpServletRequest request){
		try {
			if (validarAutenticacion(request)) {
				return ResponseEntity.ok(tipoDocumentoController.consultarTiposDocumento());
			} else {
				return new ResponseEntity<>("No tiene los permisos suficientes ", HttpStatus.UNAUTHORIZED);
			}
		} catch(Exception excepcion) {
			logger.error(excepcion.getMessage());
			return new ResponseEntity<>(excepcion.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
