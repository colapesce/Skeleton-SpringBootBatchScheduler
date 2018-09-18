package org.develop.app.batch.step.components.processors;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.RuotaDto;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.exceptions.SkipException;
import org.develop.app.batch.step.components.interfaces.ProcessorsInterface;
import org.develop.app.persistence.entities.business.Ruota;
import org.develop.app.persistence.entities.interfaces.Entita;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class RuoteItemProcessorBuilder implements ProcessorsInterface<Dto, Entita> {

	private static final Logger log = LoggerFactory.getLogger(RuoteItemProcessorBuilder.class);
	
	@Override
	public ItemProcessor<Dto, Entita> build(JSONObject json) {		
		return new RuoteItemProcessor();
	}
	


	private class RuoteItemProcessor implements ItemProcessor<Dto, Entita> {
	
		private HashMap<String,String> mapRuotaDesc = new HashMap<>();
		private HashMap<Long,String> mapIdRuota = new HashMap<>();
		
		{
	        mapRuotaDesc.put("BA","Bari");
	        mapRuotaDesc.put("FI","Firenze");
	        mapRuotaDesc.put("MI","Milano");
	        mapRuotaDesc.put("NA","Napoli");
	        mapRuotaDesc.put("PA","Palermo");
	        mapRuotaDesc.put("RM","Roma");
	        mapRuotaDesc.put("TO","Torino");
	        mapRuotaDesc.put("VE","Venezia");
	        mapRuotaDesc.put("CA","Cagliari");
	        mapRuotaDesc.put("GE","Genova");
	        mapRuotaDesc.put("RN","Ruota nazionale");
	
	        mapIdRuota.put(1L,"BA");
	        mapIdRuota.put(2L,"FI");
	        mapIdRuota.put(3L,"MI");
	        mapIdRuota.put(4L,"NA");
	        mapIdRuota.put(5L,"PA");
	        mapIdRuota.put(6L,"RM");
	        mapIdRuota.put(7L,"TO");
	        mapIdRuota.put(8L,"VE");
	        mapIdRuota.put(9L,"CA");
	        mapIdRuota.put(10L,"GE");
	        mapIdRuota.put(11L,"RN");
			
		}
		
		
		private List<Ruota> ruoteList = new ArrayList<>();
		
		@Override
		public Ruota process(Dto in) throws Exception {
	
			RuotaDto item = (RuotaDto) in;
			
			log.info("Processing..." + item);
			
			Ruota ruota = new Ruota();
			ruota.setId(item.getId());
			
			
			if(!ruoteList.contains(ruota)) {
				ruota.setFirstUse(item.getFirstUse());
				ruota.setShortDescription(mapIdRuota.get(ruota.getId()));
				ruota.setDescription(mapRuotaDesc.get(ruota.getShortDescription()));
				
				ruoteList.add(ruota);
			}
			else {
				throw new SkipException();
			}
			
			
			return ruota;
		}
		
	}
}