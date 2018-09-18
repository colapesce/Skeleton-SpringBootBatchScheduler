package org.develop.app.batch.step.components.interfaces;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemReader;

public interface ReadersInterface <Dto> {

	public ItemReader<Dto> build(JSONObject json) throws Exception;
	
}
