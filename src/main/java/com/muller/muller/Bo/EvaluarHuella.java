package com.muller.muller.Bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluarHuella {

	public static final Pattern VALID_CARACTERES_MATRIZ = 
		    Pattern.compile("[ACGT]");
	
	public boolean isFingerPrint(String[] matrix) {
		int size = matrix.length;
		

		if(evaluarMatriz(matrix,size)) {
			
			if(evaluacionHorizontal(matrix, size)) {
				return true;
			}
			if(evaluacionVertical(matrix,size)) {
				return true;
			}
			if(evaluacionDiagonal(matrix,size)) {
				return true;
			}
			return false;
			
		}
		return false;
	}
	
	private boolean evaluarMatriz(String[] matrix, int items) {

		for (int b = 0; b < items; b++) {
			if (b + 1 != items) {
				if (matrix[b].length() != matrix[b + 1].length()) {
					System.out.println("tamano incorrecto horizontalmente");
					return false;
				}else if(matrix[b].length() != items) {
					System.out.println("tamano incorrecto verticalmente");
					return false;
				}
			}
			
			if(!validate(matrix[b])){
				System.out.println("Caracteres no permitidos");
				return false;
			}
		}
		
		
		System.out.println("matriz ok");
		return true;
	}
	
	public boolean validate(String item) {
        Matcher matcher = VALID_CARACTERES_MATRIZ .matcher(item);
        return matcher.find();
}

	
	private boolean evaluacionHorizontal(String[] matrix, int items) {

		/*
		 * For para recorrer cada item de la matriz
		 */
		for (int i = 0; i < items; i++) {
			String item = matrix[i];
			int itemSize = item.length();
			/*
			 * for para selecionar el punto de partida
			 */
			for (int a = 0; a < itemSize; a++) {
				int contador = 0;
				/*
				 * for para recorrer cara caracter de la lista desde el pueto del partida selecionado
				 */
				for (int b = a; b < itemSize; b++) {
					if(b + 2 <= itemSize) {
					if (item.substring(b, b + 1).equalsIgnoreCase(item.substring(b + 1, b + 2))) {
						contador++;
						if (contador == 4) {
							System.out.println("La huella coincide horizontalmente");
							return true;
						} 
						}else {
							break;
					}
					}
				}

			}
		}
		
		return false;
	}
	
	private boolean evaluacionVertical(String[] matrix, int items) {
		
		String[][] matriz = crearMatriz(matrix);
		
		for(int j= 0;j<items;j++) {
			int contador = 0;
			for(int i= 0;i<items ; i++) {
				if(i+1 != items) {
				if(matriz[i][j].equals(matriz[i+1][j])) {
					contador++;
					if(contador==4) {
						System.out.println("La huella coincide Verticalmente");
						return true;
					}
				}else {
					break;
				}
				}
				
			}
		}

		return false;
	}
	
	private boolean evaluacionDiagonal(String[] matrix,int items) {
		String[][] matriz = crearMatriz(matrix);
		
		int contador = 0;
		for(int i= 0;i<items;i++) {
			for(int j= 0;j<items ; j++) {
				if(i == j) {
				if(matriz[i][j].equals(matriz[i][j])) {
					contador++;
					if(contador==4) {
						System.out.println("La huella coincide diagonalmete");
						return true;
					}
				}else {
					break;
				}
				}
				
			}
		}

		return false;
	}
	
	private String[][] crearMatriz(String[] matrix){
		/*
		 * Convierte el array[] a un array[][]
		 */
		int size = matrix.length;
		String matriz[][] = new String[size][size];
		for(int i = 0 ; i<size ; i++){
			String item = matrix[i];
			for(int j = 0 ; j<size ; j++) {
				if(j+1 != size+1) {
				matriz[i][j] = item.substring(j, j+1);
			}
				}
		}
		return matriz;
	}
}
