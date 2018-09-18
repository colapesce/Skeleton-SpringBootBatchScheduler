package org.develop.app.batch.step;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utils {

	synchronized
	public static Object searchInJson(JSONObject json, Enums.StepEnums stepEnum) throws JSONException {
		return json.get(stepEnum.getValue());
	}
	
}
