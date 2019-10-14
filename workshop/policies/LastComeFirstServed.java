/* RoundRobin.java */

/**
 ** Hecho por: Walther Solis, Victor Amado;
 ** Carnet: 19007097, 19003648
 ** Sección: AN
**/
package workshop.policies;

import workshop.orders.*;
import java.util.*;

/** Representa la politica de atencion Last Come First Served */
public class LastComeFirstServed extends Policy{

	/** representa la cola de vehiculos */
	private Stack<PaintOrder> queue;

    /** cantidad de elementos en el stack */

	/**
	* Constructor de la clase de la politica Last Come First Served;
	*/
	public LastComeFirstServed(){
		super();
        queue = new Stack<PaintOrder>();
	}

	 /**
     * Agrega una nueva orden a la cola de atención.
     *
     * @see PaintOrder
     * @param order nueva orden a agregar
     */
    public void add(PaintOrder order){
    	queue.push(order);
    	queuedOrders++;
    };

    /**
     * Atiende la orden pintando todas las piezas de la orden, 
     * se atiende la pieza que lleva menos tiempo en la cola.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la orden que ya fue atendida.
     */
    public PaintOrder remove(){
    	if(!queue.isEmpty()){
    		PaintOrder currentOrder = queue.pop();
    		currentOrders++;
    		try{
    			currentOrder.paint(currentOrder.getTotal());
    		} catch(Exception e){
    		}
    		currentOrders--;
    		queuedOrders--;
    		processedOrders++;
    		currentOrder.end();
    		return currentOrder;
    	} else{
    		return null;
    	}
    }

    /**
     * Retorna la siguiente orden a atender de la cola, segun la politica Last Come First Served.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la siguiente orden a atender.
     */
    public PaintOrder next(){
    	if(!queue.isEmpty()){
    		return queue.peek();
    	} else {
    		return null;
    	}
    }
	
    /**
     * Retorna un string que representa la cola de autos a atender.
     *
     * @return String representando la cola segun politica Last Come First Served.
     */
    public String toString(){
        String resp = "";
        int i = 1;
        int n = queuedOrders;
        for(PaintOrder order : queue){
            resp += order.getType();
            resp += " ";
            resp += order.getTotal();
            if(order.getTotal()==1){
                resp+= " pieza ";
            } else {
                resp += " piezas ";
            }
            resp += "(#";
            resp += order.getOrder();
            resp += ") ";
            if (n!=i){
                resp += "| ";
            }
            i++;
        }
        return resp;
    }
}
