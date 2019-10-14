/* PushOrder.java */

/**
 ** Hecho por: Victor Amado / Walther Solis
 ** Carnet: 19003648 / 19007097
 ** Sección: AN
**/

package workshop.threads;

import workshop.policies.*;
import workshop.orders.*;
import java.util.*;

/** Representa la clase que ingresa ordenes nuevas a la cola */
public class PushOrders extends Thread {
    
    
    /** Repressenta el tiempo que toma atender una pieza de una orden del tipo sedan en milisegundos*/
    private double sedanTime;
    /** Repressenta el tiempo que toma atender una pieza de una orden del tipo microbus en milisegundos*/
    private double microbusTime;
    /** Repressenta el tiempo que toma atender una pieza de una orden del tipo coupe en milisegundos*/
    private double coupeTime;
    /** Representa el tiempo aletorio utilizado para calcular el tiempo de sleep*/
    Random rnd = new Random();
    /** Representa el tipo de politica que se escogio*/
    private Policy policy;
    /** Representa numero de orden asignado*/
    private int number;
    /** tipo de politica escogida */
    private String policyType;
    /** Representa el tiempo minimo de entrada de una nueva orden */
    private double minEntryTime;
    /** Representa el tiempo maximo de entrada de una nueva orden */
    private double maxEntryTime;
    /**  precio por pieza */
    private double price;

    /**
	* Constructor de la clase PushOrders
	*
	* @param policy {@link Policy} Recibe la politica de atencion selecionada por el usuario
	*/
    public PushOrders(Policy policy, String policyType, double minEntryTime, double maxEntryTime, double sedanTime, double microbusTime, double coupeTime, double price){
        super();
        this.policy = policy;
        this.number = 1;
        this.policyType = policyType;
        this.minEntryTime = minEntryTime;
        this.maxEntryTime = maxEntryTime;
        this.sedanTime = sedanTime;
        this.microbusTime = microbusTime;
        this.coupeTime = coupeTime;
        this.price = price;
    }

    /**
	* El metodo que se ejecuta otro thread, este metodo ingresa ordenes de pintura a la cola 
	* en tiempos aleatorios.
    * 
	*/
    public void run(){
        while(true) {
            
            String message = "";
			int sleepTime;
            int pieces = rnd.nextInt(5) + 1;
            int type = rnd.nextInt(3);
            try{
                int time = rnd.nextInt(((int) maxEntryTime*1000 - (int) minEntryTime*1000));
                sleepTime = time + (int) minEntryTime*1000;
            Thread.sleep(sleepTime);
            } catch(Exception e) {}
            if (type==0) {
                BusOrder order = new BusOrder(number++, randomPlate(), pieces, microbusTime, price);
                policy.add(order);
                message = message + "---------------\n" + "Nuevo ingreso! Se ingres\u00f3 un " + order.getType();
            } else {
                if (type==1) {
                    SedanOrder order = new SedanOrder(number++, randomPlate(), pieces, sedanTime, price);
                    policy.add(order);
                    message = message + "---------------\n" + "Nuevo ingreso! Se ingres\u00f3 un " + order.getType();
                } else {
                    CoupeOrder order = new CoupeOrder(number++, randomPlate(), pieces, coupeTime, price);
                    policy.add(order);
                    message = message + "---------------\n" + "Nuevo ingreso! Se ingres\u00f3 un " + order.getType();
                }
            }
            
            message = message + " que necesita " + pieces;
            if (pieces > 1) {
                message = message + " piezas (correlativo #" + Integer.toString(number-1) + ")\n \n";
                
            } else {
                message = message + " pieza (correlativo #" + Integer.toString(number-1) + ")\n \n";
            }
            
            
            
            if (policyType.equals("-fcfs")) {
                message = message + "Pol\u00edtica seleccionada: First Come First Served\n \n";
            } else {
                if (policyType.equals("-lcfs")) {
                    message = message + "Pol\u00edtica seleccionada: Last Come First Served\n \n";
                } else {
                    message = message + "Pol\u00edtica seleccionada: Round Robin\n \n";
                }
            }
            if(policy.next()!=null){
                message = message + "Actualmente se atiende a: " + policy.next().getType() + " " + policy.next().getTotal();
                if(policy.next().getTotal() == 1){
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

    /**
	* Metodo randomPlate
	* Genera una placa aleatoria para crear una orden nueva.
    *
    * @return un String que contine una placa de la forma DDDLLL donde D es un digito y L es una letra.
	*/
    public String randomPlate(){
        String output = "";
        output+= Integer.toString(rnd.nextInt(10));
        output+= Integer.toString(rnd.nextInt(10));
        output+= Integer.toString(rnd.nextInt(10));
        output+= Character.toString((char)(rnd.nextInt(26) + 65));
        output+= Character.toString((char)(rnd.nextInt(26) + 65));
        output+= Character.toString((char)(rnd.nextInt(26) + 65));
        return output;
    }

    

}