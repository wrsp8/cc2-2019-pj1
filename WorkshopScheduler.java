/* WorkshopScheduler.java */

/**
 ** Hecho por: Walther Solis, Victor Amado;
 ** Carnet: 19007097, 19003648
 ** Sección: AN
**/

import java.util.*;
import workshop.policies.*;
import java.io.*;
import workshop.threads.*;

public class WorkshopScheduler{
	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo sedan en milisegundos*/
	public static double sedanTime;
	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo microbus en milisegundos*/
	public static double microbusTime;
	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo coupe en milisegundos*/
	public static double coupeTime;
	/**  precio por pieza */
	public static double price;
	/** Tiempo minimo de entrada de un nuevo vehiculo */
	public static double minEntryTime;
	/** Tiempo maximo de entrada de un nuevo vehiculo */
	public static double maxEntryTime;
	/** codigo que representa la politica seleccionada */
	public static String policyType;
	/** clase que representa la politica seleccionada */
	public static Policy policy;

	public static void main(String[] args) throws Exception{

		//verifica que se manden 6 parametros
		if(args.length == 6){
			try{

				policyType = args[0];
				//Comprueba que la politica de atencion sea una de las definidas en el proyecto
				if(!(policyType.equals("-fcfs")||policyType.equals("-lcfs")||policyType.equals("-rr"))){
					throw new Exception();
				}

				//Transforma el rango a dos valores separados
				Scanner range = new Scanner(args[1]);
				range.useDelimiter("-");
				minEntryTime =Double.parseDouble(range.next());
				maxEntryTime =Double.parseDouble(range.next());
				
				//Comprueba que el rango sea valido
				if(minEntryTime>maxEntryTime||range.hasNext()){
					throw new Exception();
				}

				//Define los tiempos de atencion en milisegundos
				sedanTime = Double.parseDouble(args[2]);
				microbusTime =Double.parseDouble(args[3]);
				coupeTime = Double.parseDouble(args[4]);
				price = Double.parseDouble(args[5]);
 
			} catch(Exception e){
				System.out.println("Los paremetros son invalidos el programa se cerrara.");
				e.printStackTrace(new java.io.PrintStream(System.out));
				 System.exit(1);
			}
		} else {
			System.out.println("Los paremetros son invalidos el programa se cerrara.");
			 System.exit(1);
		}
		if(policyType.equals("-fcfs")){
			policy = new FirstComeFirstServed();
		} else if (policyType.equals("-lcfs")){
			policy = new LastComeFirstServed();
		} else if (policyType.equals("-rr")){
			policy = new RoundRobin();
		}

		PushOrders inOrders = new PushOrders(policy, policyType, minEntryTime, maxEntryTime, sedanTime,microbusTime,coupeTime, price);
		inOrders.start();
		PullOrders outOrders = new PullOrders(policy, price, policyType, maxEntryTime);
		outOrders.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String keyboard = in.readLine();
			if(keyboard.equals("q")||keyboard.equals("q ")||keyboard.equals("Q")||keyboard.equals("Q ")){
				System.exit(1);
			}
		}
	}

}