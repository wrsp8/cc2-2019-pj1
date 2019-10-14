/* PullOrders.java */

/**
 ** Hecho por: Walther Solis, Victor Amado;
 ** Carnet: 19007097, 19003648
 ** Sección: AN
**/

package workshop.threads;

import workshop.*;
import workshop.orders.*;
import workshop.policies.*;

/** Representa la clase que remueve ordenes de la cola */
public class PullOrders extends Thread {

	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo sedan en milisegundos*/
	private int sedanTime;
	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo microbus en milisegundos*/
	private int microbusTime;
	/** Repressenta el tiempo que toma atender una pieza de una orden del tipo coupe en milisegundos*/
	private int coupeTime;
	/**  precio por pieza */
	private double price;
	/** tipo de politica escogida */
	private String policyType;
	/** politica de atencion escogida */
	private Policy policy;
	/** La politica seleccionada escrita de manera larga en un String */
	private String policyString;
	/** Representa el tiempo maximo para ingresa una orden */
	private double maxEntryTime;

	/**
	* Constructor de la clase PullOrders
	*
	* @param policy {@link Policy} Recibe la politica de atencion selecionada por el usuario
	*/
	public PullOrders(Policy policy, double price, String policyType, double maxEntryTime){
		this.policy = policy;
		if(policyType.equals("-fcfs")){
			policyString = "First Come First Served";
		} else if (policyType.equals("-lcfs")){
			policyString = "Last Come First Served";
		} else if (policyType.equals("-rr")){
			policyString = "Round Robin";
		}
		this.price = price;
		this.policyType = policyType;
		this.maxEntryTime = maxEntryTime;
	}




	/**
	* El metodo que se ejecuta en otro thread, este metodo saca de la cola las ordenes de pintura
	* segun la politica seleccionada.
	*
	*/
	public void run(){
		try{
			Thread.sleep((int)(maxEntryTime*1000));
		} catch(Exception e){}
		
		while(true){
			String message = "";
			//System.out.println("SE REALIZO CICLO");
			if(policyType.equals("-fcfs")||policyType.equals("-lcfs")){
				PaintOrder order = policy.next();
								
				if(order!=null){
					try{
						int time = (int) (order.getTime()*order.getTotal()*1000);
						Thread.sleep(time);
					} catch (Exception e){}
					message = message + "---------------\n" + "Orden Completa! se complet\u00f3 " + order.getType() +  " que necesitaba " + order.getTotal();
					if(order.getTotal() == 1){
						message = message + " pieza (correlativo #" + order.getOrder() + ")\n";
					} else{
						message = message + " piezas (correlativo #" + order.getOrder() + ")\n";
					}
					if(!order.getType().equals("SEDAN")){
						message = message + "Precio original: " + order.getSubPrice();
						message = message + "\nDescuento: " + order.getDiscount() + "%";
						order.end();
						message = message + "\nTotal a pagar: " + order.getPrice() + "\n";
					} else {
						message = message + "Precio original: " + String.format("%.2f", price) + " por pieza";
						message = message + "\nCantidad de piezas: " + order.getTotal();
						order.end();
						message = message + "\nTotal a pagar: " + order.getPrice() + "\n";
					}
					message = message + "\nPol\u00edtica seleccionada: " + policyString + "\n \n";
					order = policy.remove();
					if(policy.next()!=null){
						message = message + "Actualmente se atiende a: " + policy.next().getType() + " " + policy.next().getTotal();

						if(policy.next().getTotal()== 1){
							message = message + " pieza (correlativo #" + policy.next().getOrder() + ")\n";
						} else{
							message = message + " piezas (correlativo #" + policy.next().getOrder() + ")\n";
						}
						message = message + "Ordenes pendientes: " + policy.toString();
					} else {
						message = message + "No hay mas vehiculos para atender.";
					}
					message = message + "\n---------------\n\n"; 
					System.out.println(message);
					System.out.flush();
				}
			} else {
				PaintOrder order = policy.next();
								
				if(order!=null){
					try{
						int time = (int) (order.getTime()*1000);
						Thread.sleep(time);
					} catch (Exception e){}
					order = policy.remove();
					if(order.getTotal()==order.getPainted()){
						message = message + "---------------\n" + "Orden Completa! se complet\u00f3 pieza " + order.getTotal() + " de " + order.getTotal() + " de " + order.getType() + " (correlativo #" + order.getOrder() + ")\n";
						if(!order.getType().equals("SEDAN")){
							message = message + "Precio original: " + order.getSubPrice();
							message = message + "\nDescuento: " + order.getDiscount() + "%";
							order.end();
							message = message + "\nTotal a pagar: " + order.getPrice() + "\n";
						} else {
							message = message + "Precio original: " + String.format("%.2f", price) + " por pieza";
							message = message + "\nCantidad de piezas: " + order.getTotal();
							order.end();
							message = message + "\nTotal a pagar: " + order.getPrice() + "\n";
						}
					} else{
						message = message + "---------------\n" + "Se termino de trabajar pieza! Se complet\u00f3 pieza ";
						message = message + order.getPainted()+ " de " + order.getTotal() + " de " + order.getType();
						message = message + " (correlativo #" + order.getOrder()+")\n";
					}
					message = message + "\nPol\u00edtica seleccionada: " + policyString + "\n \n";
					
					if(policy.next()!=null){
						message = message + "Actualmente se atiende a: " + policy.next().getType() + " " + policy.next().getTotal();
						if(policy.next().getTotal()== 1){
							message = message + " pieza (correlativo #" + policy.next().getOrder() + ")\n";
						} else{
							message = message + " piezas (correlativo #" + policy.next().getOrder() + ")\n";
						}
						message = message + "Ordenes pendientes: " + policy.toString();
					} else {
						message = message + "No hay mas vehiculos para atender.";
					}
					message = message + "\n---------------\n\n";
					System.out.println(message);
					System.out.flush();
				}
			}

		}
	}


}