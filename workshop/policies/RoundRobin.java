/* RoundRobin.java */

/**
 ** Hecho por: Walther Solis, Victor Amado;
 ** Carnet: 19007097, 19003648
 ** Sección: AN
**/
package workshop.policies;

import workshop.orders.*;
import java.util.*;

/** Representa la politica de atencion RoundRobin */
public class RoundRobin extends Policy{

	/** representa la cola de vehiculos */
	private LinkedList<PaintOrder> queue;

	/**
	* Constructor de la clase de la politica RoundRobin;
	*/
	public RoundRobin(){
		super();
        queue = new LinkedList<PaintOrder>();
	}

	 /**
     * Agrega una nueva orden a la cola de atención.
     *
     * @see PaintOrder
     * @param order nueva orden a agregar
     */
    public void add(PaintOrder order){
    	queue.add(order);
    	queuedOrders++;
    };

    /**
     * Atiende la orden pintando una pieza y luego encolando de nuevo el resto de la orden, 
     * si es la ultima pieza la elimina de la cola.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la orden que ya fue atendida.
     */
    public PaintOrder remove(){
    	if(!queue.isEmpty()){
    		PaintOrder currentOrder = queue.remove();
    		if(currentOrder.getPainted()==0) currentOrders++;
            try{
    		  currentOrder.paint(1);
            } catch (Exception e){
            }
    		if(!(currentOrder.getPainted() >= currentOrder.getTotal())){
    			queue.add(currentOrder);
    		} else {
    			currentOrders--;
    			queuedOrders--;
    			processedOrders++;
                currentOrder.end();
    		}
    		return currentOrder;
    	} else{
    		return null;
    	}
    }

    /**
     * Retorna la siguiente orden a atender de la cola, segun la politica Round Robin.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la siguiente orden a atender.
     */
    public PaintOrder next(){
    	if(!queue.isEmpty()){
    		return queue.getFirst();
    	} else {
    		return null;
    	}
    }

    /**
     * Retorna un string que representa la cola de autos a atender.
     *
     * @return String representando la cola segun politica Round Robin.
     */
    public String toString(){
        String resp = "";
        int i = 1;
        int n = queuedOrders;
        for(PaintOrder order : queue){
            resp += order.getType();
            resp += " ";
            resp += order.getPainted();
            resp += " de ";
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
