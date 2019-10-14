/* SedanOrder.java */
/**
 ** Hecho por: Victor Amado / Walther Solis
 ** Carnet: 19003648 / 19007097
 ** Sección: AN
**/

package workshop.orders;


/** Representa una orden de pintura para los tipo Sedan. */
public class SedanOrder extends PaintOrder {

    /** Precio sin descuento. */
    double totalPrice;
    /** Descuento. */
    int discount;
    /** Precio con descuento. */
    double finalPrice;

    /**
     * SedanOrder
     *
     * @param number número de orden
     * @param plate número de placa
     * @param total número total de piezas a pintar
     * @param time cantidad de tiempo por pieza
     * 
     * @return nada
     * 
     * Este es el constructor de ordenes tipo Sedan 
     */
    public SedanOrder(int number, String plate, int total, double time, double price) {
        super(number, plate, total, time);
        this.totalPrice = total * price;
        this.discount = 0;
        this.finalPrice = (this.totalPrice * (1 - (discount / 100)));
    }

    /**
     * end
     * 
     * @param niguno
     * 
     * @return nada
     *
     * Este es el metodo que cambia el estado de una orden a DONE
     */
    public void end() {
        this.state = State.DONE;
    }

    /**
     * getType
     * 
     * @param niguno
     * 
     * @return tipo de orden
     *
     * Este es el metodo que nos dice que tipo de orden estamos consultando
     */
    public String getType() {
        return "SEDAN";
    }

    /**
     * getPrice
     * 
     * @param niguno
     * 
     * @return precio a pagar
     *
     * Este es el metodo que nos dice el precio a pagar ya con el descuento aplicado
     */
    public String getPrice() {
        if (this.state == State.DONE) {
            return String.format("%.2f", finalPrice);
        }
        else {
            return "?";
        }
    }

    /**
     * getDiscount
     * 
     * @param niguno
     * 
     * @return porcentaje de descuento
     *
     * Este es el metodo que nos dice el decuento correspondiente al tipo de orden
     */
    public String getDiscount() {
        return String.valueOf(discount);
    }

    /**
     * getSubPrice
     * 
     * @param niguno
     * 
     * @return precio total
     *
     * Este es el metodo que nos dice el precio sin aplicar el descuento
     */
    public String getSubPrice() {
        return String.format("%.2f", totalPrice);
    }

}