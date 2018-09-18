package org.develop.app.batch.step.components.interfaces;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemWriter;

public interface WritersInterface<Entita> {

	public ItemWriter<Entita> build(JSONObject json);
	
}
