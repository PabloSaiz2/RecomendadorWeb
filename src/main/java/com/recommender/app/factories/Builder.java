package com.recommender.app.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	
	private String _type;

	Builder(String type) {
		if (type == null)
			throw new IllegalArgumentException("Invalid type: " + type);
		else
			_type = type;
	}
	
	public T createInstance(JSONObject jo,int n) {
		T b = null;

		if (_type != null &&jo.getString("type").equals(_type)) {
			JSONArray array = jo.getJSONArray("data");
			if(n<array.length())
				b = createTheInstance(array.getJSONObject(n));
		}
		
		return b;
	}

	protected abstract T createTheInstance(JSONObject product);
}
