package org.develop.app.batch.step.components.interfaces;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;

public interface ProcessorsInterface<Dto,Entita> {

	public ItemProcessor<Dto,Entita> build(JSONObject json);
	
}
