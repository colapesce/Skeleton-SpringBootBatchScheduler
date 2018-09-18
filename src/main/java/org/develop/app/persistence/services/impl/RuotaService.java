package org.develop.app.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.develop.app.persistence.dao.interfaces.IRuotaDao;
import org.develop.app.persistence.entities.business.Ruota;
import org.develop.app.persistence.services.interfaces.IRuotaService;
import org.develop.app.persistence.utils.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuotaService implements IRuotaService {

	@Autowired
	IRuotaDao ruotaDao;
	
	@Override
	public Ruota loadRuota(String chiave) {
		
		QueryParameter param = new QueryParameter("shortDescription", "'"+chiave+"'");
		List<QueryParameter> params = new ArrayList<>();
		
		params.add(param);
		
		List<Ruota> ruote = ruotaDao.findByParams(Ruota.class, params);
		return !ruote.isEmpty() ? ruote.get(0) : null;
	}

	@Override
	@Transactional
	public void salvaRuota(Ruota ruota) {
		ruotaDao.create(ruota);
	}

	@Override
	public List<Ruota> loadAll() {
		return ruotaDao.findAll(Ruota.class);
	}
	
	

}
