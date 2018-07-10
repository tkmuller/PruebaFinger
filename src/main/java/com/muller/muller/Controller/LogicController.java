package com.muller.muller.Controller;

import com.muller.muller.Bo.EvaluarHuella;
import com.muller.muller.entity.Finger;
import com.muller.muller.entity.Logic;
import com.muller.muller.repository.LogicRepository;
import com.muller.muller.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogicController {

    @Autowired
    private LogicRepository logicRepository;

    @Autowired
    private LogicService logicService;
    
    @Autowired
    private EvaluarHuella evaluarHuella;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Logic> logic(){
        return logicRepository.findAll();
    }
    
	@RequestMapping(value = "/finger", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<Logic> persitFinger(@RequestBody Finger finger) {

		Boolean resultado = evaluarHuella.isFingerPrint(finger.getMatrix());

		if (resultado) {
			Logic logic = new Logic();
			String matriz = formatearMatriz(finger.getMatrix());
			if (validarMatrizBD(matriz, logicRepository.findAll())) {
				logic.setHuellaOk(matriz);
				logicService.save(logic);
				return ResponseEntity.status(HttpStatus.OK).build();

			}

		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	}
	
	private String formatearMatriz(String[] matriz) {
		String resultado = "";
		
		for(int i=0; i<matriz.length;i++) {
			resultado = resultado.concat(",").concat(matriz[i]);
		}
		
		return resultado.substring(1);
		
	}

	private boolean validarMatrizBD(String MatrizActual, List<Logic> MaticesGuardadas) {
		if (!MaticesGuardadas.isEmpty()) {
			for (Logic logic : MaticesGuardadas) {
				if (logic.getHuellaOk().equalsIgnoreCase(MatrizActual)) {
					System.out.println("La matriz ya existe en la base de datos");
					return false;
				}
			}
		}
		return true;
	}
	
}
