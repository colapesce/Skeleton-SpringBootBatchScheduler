package org.develop.app.persistence.services.interfaces;

import java.util.List;

import org.develop.app.persistence.entities.business.Ruota;

public interface IRuotaService {

	public Ruota loadRuota(String chiave);
	
	public List<Ruota> loadAll();
	
	public void salvaRuota(Ruota ruota);
	
}
