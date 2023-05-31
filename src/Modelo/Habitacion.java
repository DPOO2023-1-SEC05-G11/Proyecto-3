package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Habitacion {
	
	private int id;
	private String tipo;
	private boolean balcon;
	private boolean vista;
	private boolean cocina;
	private ArrayList<Cama> camas;
	private Double tarifa;
	private boolean ocupado;
	private int espacioAdultos;
	private int espacioNinos;

	private double tamanhoM2;
	private boolean aire;
	private boolean calefaccion;
	private boolean tv;
	private boolean cafetera;
	private boolean ropaCama;
	private boolean tapetesHipo;
	private boolean plancha;
	private boolean secador;
	private int voltajeAC;
	private boolean usbA;
	private boolean usbB;
	private boolean desayuno;
	
	
	private ReservaEstadia reservaActual = null;
	
	private ArrayList<LocalDate> fechasOcupadas = new ArrayList<LocalDate>();
	
	/**
	 * @param id
	 * @param tipo
	 * @param balcon
	 * @param vista
	 * @param cocina
	 * @param camas
	 * @param tarifa
	 * @param ocupada
	 * @param espacioAdultos
	 * @param espacioNinos
	 * @param tamanhoM2
	 * @param aire
	 * @param calefaccion
	 * @param tv
	 * @param cafetera
	 * @param tapetesHipo
	 * @param plancha
	 * @param secador
	 * @param voltajeAC
	 * @param usbA
	 * @param usbB
	 * @param desayuno
	 * 
	 */
	public Habitacion(int id, String tipo, Boolean balcon, Boolean vista, Boolean cocina, ArrayList<Cama> camas, Double tarifa, Boolean ocupada,
			Double tamanhoM2, Boolean aire, Boolean calefaccion, Boolean tv, Boolean cafetera, Boolean ropaCama, Boolean tapetesHipo, Boolean plancha,
			Boolean secador, int voltajeAC, Boolean usbA, Boolean usbB, Boolean desayuno) {

		this.id = id;
		this.tipo = tipo;
		this.balcon = balcon;
		this.vista = vista;
		this.cocina = cocina;
		this.camas = camas;
		this.tarifa = tarifa;
		this.ocupado = ocupada;
		
		this.espacioAdultos = espacioAdultos();
		this.espacioNinos = espacioNinos();
		
		this.tamanhoM2 = tamanhoM2;
		this.aire = aire;
		this.calefaccion = calefaccion;
		this.tv = tv;
		this.cafetera = cafetera;
		this.ropaCama = ropaCama;
		this.tapetesHipo = tapetesHipo;
		this.plancha = plancha;
		this.secador = secador;
		this.voltajeAC = voltajeAC;
		this.usbA = usbA;
		this.usbB = usbB;
		this.desayuno = desayuno;
	}

	public Boolean tieneAire(){
		return this.aire;
	}
	
	public Boolean tieneCalefaccion(){
		return this.calefaccion;
	}
	
	public Boolean tieneTv(){
		return this.tv;
	}
	
	public Boolean tieneCafetera(){
		return this.cafetera;
	}
	
	public Boolean tieneRopaCama(){
		return this.ropaCama;
	}
	
	public Boolean tieneTapetesHipo(){
		return this.tapetesHipo;
	}
	
	public Boolean tienePlancha(){
		return this.plancha;
	}
	
	public Boolean tieneSecador(){
		return this.secador;
	}
	
	public Boolean tieneUsbA(){
		return this.usbA;
	}
	
	public Boolean tieneUsbB(){
		return this.usbB;
	}
	
	public Boolean tieneDesayuno(){
		return this.desayuno;
	}

	public double getTamanhoM2()
	{
		return this.tamanhoM2;
	}
	
	public int getVoltajeAC()
	{
		return this.voltajeAC;
	}

	public int getId(){
		return this.id;
	}

	public String getTipo()
	{
		return this.tipo;
	}

	public Boolean tieneBalcon(){
		return this.balcon;
	}
	
	public Boolean tieneVista(){
		return this.vista;
	}
	
	public Boolean tieneCocina(){
		return this.cocina;
	}
	
	public ArrayList<Cama> getCamas(){
		return this.camas;
	}
	
	public Double getTarifa()
	{
		return this.tarifa;
	}

	public Boolean isOcupado()
	{
		return this.ocupado;
	}
	public void updateOcupado(boolean b) {
		this.ocupado = b;
	}

	public void updateTarifa(Double nuevaTarifa) 
	{
		this.tarifa = nuevaTarifa;
	}
	
	public ReservaEstadia getReservaActual()
	{
		return this.reservaActual;
	}
	
	public void setReservaActual(ReservaEstadia reserva)
	{
		this.reservaActual = reserva;
	}
	
	public int getEspacioAdultos()
	{
		return this.espacioAdultos;
	}
	
	public int getEspacioNinos()
	{
		return this.espacioNinos;
	}

	public ArrayList<LocalDate> getFechasOcupadas(){
		return this.fechasOcupadas;
	}
	
	public Boolean libreEntre(LocalDate fechaInicial, int duracion) {
		LocalDate fechaFinal = fechaInicial.plusDays(duracion-1);
	    for (LocalDate fechaOcupada : fechasOcupadas) {
	        if (fechaOcupada.compareTo(fechaInicial) >= 0 && fechaOcupada.compareTo(fechaFinal) <= 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public void addFechaOcupada(LocalDate fecha)
	{
		this.fechasOcupadas.add(fecha);
	}
	
	public void removeFechaOcupada(LocalDate fecha)
	{
		this.fechasOcupadas.remove(fecha);
	}
	
	public int espacioTotal()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			total += cama.getTamano();
		}
		return total;
	}
	
	public int espacioAdultos()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			if (!cama.isParaNinos()) {
				total += cama.getTamano();
			}
		}
		return total;
	}
	
	public int espacioNinos()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			if (cama.isParaNinos()) {
				total += cama.getTamano();
			}
		}
		return total;
	}
	
	//Metodos para sacar infos de las camas de la habitacion, si lo hacemos con la clase cama
	
	
	public String toString() {
	    String camaString = "";

	    for (int i = 0; i < 3; i++) {
	        if (i < camas.size()) {
	            camaString += "Cama " + (i + 1) + ": " + camas.get(i) + ", ";
	        }
	    }
	    return "Habitacion [id=" + id + ", tipo=" + tipo + ", balcon=" + balcon + 
	    		", vista=" + vista + ", cocina=" + cocina + ", espacio adultos="+espacioAdultos+", espacio ninos="+espacioNinos
	    		+", camas=" + camaString + "tarifa=" + tarifa + ", ocupado=" + ocupado + "]";
	}
	
}