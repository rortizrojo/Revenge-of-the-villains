package Memento;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Clase para manejar fechas con horas (DD/MM/AAAA/HO:MI)
 * @author Salvador Oton
 */
public class FechaHora {
    //atributos
    private int año;
    private int mes;
    private int dia;
    private int hora;
    private int min;

    /**
     * Constructor.
     * @param dia
     * @param mes
     * @param año
     * @param hora
     * @param min 
     */
    public FechaHora(int dia, int mes, int año, int hora, int min) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.hora = hora;
        this.min = min;
    }

    /**
     * Constructor.
     * @param cadenaFecha Fecha en formato DD/MM/AAAA/HO:MI
     */
    public FechaHora(String cadenaFecha) {
        StringTokenizer st = new StringTokenizer(cadenaFecha, "/:");
        String dd = st.nextToken();
        String mm = st.nextToken();
        String aa = st.nextToken();
        String ho = st.nextToken();
        String mi = st.nextToken();

        this.dia = Integer.parseInt(dd);
        this.mes = Integer.parseInt(mm);
        this.año = Integer.parseInt(aa);
        this.hora = Integer.parseInt(ho);
        this.min = Integer.parseInt(mi);
    }

    /**
     * Constructor. Establece como fecha la actual.
     */
    public FechaHora() {
        Calendar hoy = Calendar.getInstance();
        año = hoy.get(Calendar.YEAR);
        mes = hoy.get(Calendar.MONTH) + 1;
        dia = hoy.get(Calendar.DAY_OF_MONTH);
        hora = hoy.get(Calendar.HOUR_OF_DAY);
        min = hoy.get(Calendar.MINUTE);
    }

    /**
     * Devuelve la fecha en formato en formato DD/MM/AAAA/HO:MI
     * @return Fecha en formato en formato DD/MM/AAAA/HO:MI
     */
    @Override
    public String toString() {
        if (min < 10) {
            return dia + "/" + mes + "/" + año + "/" + hora + ":0" + min;
        } else {
            return dia + "/" + mes + "/" + año + "/" + hora + ":" + min;
        }
    }

    /**
     * Devuelve el año.
     * @return Entero que representa el año.
     */
    public int getAño() {
        return this.año;
    }

    /**
     * Establece el año.
     * @param año Entero que representa el año.
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * Devuelve el día.
     * @return Entero que representa el día.
     */
    public int getDia() {
        return this.dia;
    }

    /**
     * Establece el día.
     * @param dia Entero que representa el día.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Devuelve el mes.
     * @return Entero que representa el mes.
     */
    public int getMes() {
        return this.mes;
    }

    /**
     * Establece el mes.
     * @param mes Entero que representa el mes.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Devuelve la hora.
     * @return Entero que representa la hora.
     */
    public int getHora() {
        return this.hora;
    }

    /**
     * Establece la hora.
     * @param hora Entero que representa la hora.
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     * Deuelve los minutos.
     * @return Entero que representa los minutos.
     */
    public int getMin() {
        return this.min;
    }

    /**
     * Establece los minutos.
     * @param min Entero que representa los minutos.
     */
    public void setMin(int min) {
        this.min = min;
    }
}
