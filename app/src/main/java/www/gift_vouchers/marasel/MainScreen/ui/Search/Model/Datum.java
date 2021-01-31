package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("googleArray")
	private GoogleArray[] googleArray;
	@SerializedName("marasellArray")
	private MarasellArray[] marasellArray;

	public void setGoogleArray(GoogleArray[] googleArray){
		this.googleArray = googleArray;
	}
	public GoogleArray[] getGoogleArray(){
		return this.googleArray;
	}
	public void setMarasellArray(MarasellArray[] marasellArray){
		this.marasellArray = marasellArray;
	}
	public MarasellArray[] getMarasellArray(){
		return this.marasellArray;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		JSONArray googleArrayJsonArray = jsonObject.optJSONArray("googleArray");
		if(googleArrayJsonArray != null){
			ArrayList<GoogleArray> googleArrayArrayList = new ArrayList<>();
			for (int i = 0; i < googleArrayJsonArray.length(); i++) {
				JSONObject googleArrayObject = googleArrayJsonArray.optJSONObject(i);
				googleArrayArrayList.add(new GoogleArray(googleArrayObject));
			}
			googleArray = (GoogleArray[]) googleArrayArrayList.toArray();
		}
		JSONArray marasellArrayJsonArray = jsonObject.optJSONArray("marasellArray");
		if(marasellArrayJsonArray != null){
			ArrayList<MarasellArray> marasellArrayArrayList = new ArrayList<>();
			for (int i = 0; i < marasellArrayJsonArray.length(); i++) {
				JSONObject marasellArrayObject = marasellArrayJsonArray.optJSONObject(i);
				marasellArrayArrayList.add(new MarasellArray(marasellArrayObject));
			}
			marasellArray = (MarasellArray[]) marasellArrayArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			if(googleArray != null && googleArray.length > 0){
				JSONArray googleArrayJsonArray = new JSONArray();
				for(GoogleArray googleArrayElement : googleArray){
					googleArrayJsonArray.put(googleArrayElement.toJsonObject());
				}
				jsonObject.put("googleArray", googleArrayJsonArray);
			}
			if(marasellArray != null && marasellArray.length > 0){
				JSONArray marasellArrayJsonArray = new JSONArray();
				for(MarasellArray marasellArrayElement : marasellArray){
					marasellArrayJsonArray.put(marasellArrayElement.toJsonObject());
				}
				jsonObject.put("marasellArray", marasellArrayJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}