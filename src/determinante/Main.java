/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determinante;

import java.io.File;
import java.util.Scanner;
/**
 *
 * @author logra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static public Integer EOF = -1;
    
    public static int[][] leer_matriz(File archivo){
        int matriz[][] = new int[0][0];
        
        try{
            Scanner sr = new Scanner(archivo);
            int filas = sr.nextInt();
            int columnas = sr.nextInt();
            matriz = new int[filas][columnas];
            
            while(sr.hasNext()){
                for(int i = 0; i < filas; i++){
                    for(int j = 0; j < columnas; j++){
                        matriz[i][j] = sr.nextInt();
                    }
                }
            }
            
            sr.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return matriz;
    }
    
    public static int determinante(int[][] matriz){
        int resultado = 0;
        int submatriz[][] = new int[0][0];
        if(matriz.length == 1){
            return matriz[0][0];
        }else{
            for(int i = 0; i < matriz.length; i++){
                submatriz = new int[matriz.length - 1][matriz.length - 1];
                for(int j = 1; j < matriz.length; j++){
                    for(int p = 0; p < matriz.length; p++){
                        if(p > i){
                            submatriz[j - 1][p - 1] = matriz[j][p];
                        }else if(p < i){
                            submatriz[j - 1][p] = matriz[j][p];
                        }
                    }
                }
                if(i % 2 == 0){
                    resultado += matriz[0][i] * determinante(submatriz);
                }else{
                    matriz[0][i] *= -1;
                    resultado += matriz[0][i] * determinante(submatriz);
                }
            }
        }
        
        
        return resultado;
    }
    
    
    public static void imprimir_matriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz.length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Scanner sc = new Scanner(System.in);
            
            System.out.println("= Bienvenido a la calculadora de determinantes =\n");
            System.out.println("Su archivo debe contener en la primera linea las dimensiones");
            System.out.print("y despues los elementos de la matriz separados con un espacio.\n");
            System.out.print("\nEscriba la direccion del archivo: ");
            String direccion = sc.nextLine();
            File archivo = new File(direccion, "INPUT.txt");
                       
            int matriz[][] = leer_matriz(archivo);
            //imprimir_matriz(matriz);
            int res_final = determinante(matriz);
            System.out.println("El determinante de la matriz es: " + res_final);
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
