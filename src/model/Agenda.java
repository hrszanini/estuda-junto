package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	
	private List<Evento> eventos;
	
	public Agenda(){
		eventos = new ArrayList<Evento>();
	}
	
	public void addEvento(Evento evento){
		eventos.add(evento);
	}
	
	public void delEvento(Evento evento){
		for(Evento e: eventos)
			if(e.getId() == evento.getId())
				eventos.remove(e);
	}
	
	public void altEvento(Evento evento){
		for(Evento e: eventos)
			if(e.getId() == evento.getId()){
				eventos.remove(e);
				eventos.add(evento);
			}
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
