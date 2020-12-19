package www.gift_vouchers.marasel.MainScreen.ui.Product.Model;//
//  ProductsByCat.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 17, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ProductsByCat{

	@SerializedName("data")
	private Datum[] data;
	@SerializedName("is_paginate")
	private boolean isPaginate;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(Datum[] data){
		this.data = data;
	}
	public Datum[] getData(){
		return this.data;
	}
	public void setIsPaginate(boolean isPaginate){
		this.isPaginate = isPaginate;
	}
	public boolean isIsPaginate(){
		return this.isPaginate;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ProductsByCat(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		isPaginate = jsonObject.optBoolean("is_paginate");
		status = jsonObject.optInt("status");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			ArrayList<Datum> dataArrayList = new ArrayList<>();
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				dataArrayList.add(new Datum(dataObject));
			}
			data = (Datum[]) dataArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("is_paginate", isPaginate);
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			if(data != null && data.length > 0){
				JSONArray dataJsonArray = new JSONArray();
				for(Datum dataElement : data){
					dataJsonArray.put(dataElement.toJsonObject());
				}
				jsonObject.put("data", dataJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}