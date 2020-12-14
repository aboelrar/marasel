package www.gift_vouchers.marasel.MainScreen.ui.home.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 12, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("cats")
	private Cat[] cats;
	@SerializedName("stores")
	private Store[] stores;

	public void setCats(Cat[] cats){
		this.cats = cats;
	}
	public Cat[] getCats(){
		return this.cats;
	}
	public void setStores(Store[] stores){
		this.stores = stores;
	}
	public Store[] getStores(){
		return this.stores;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		JSONArray catsJsonArray = jsonObject.optJSONArray("cats");
		if(catsJsonArray != null){
			ArrayList<Cat> catsArrayList = new ArrayList<>();
			for (int i = 0; i < catsJsonArray.length(); i++) {
				JSONObject catsObject = catsJsonArray.optJSONObject(i);
				catsArrayList.add(new Cat(catsObject));
			}
			cats = (Cat[]) catsArrayList.toArray();
		}
		JSONArray storesJsonArray = jsonObject.optJSONArray("stores");
		if(storesJsonArray != null){
			ArrayList<Store> storesArrayList = new ArrayList<>();
			for (int i = 0; i < storesJsonArray.length(); i++) {
				JSONObject storesObject = storesJsonArray.optJSONObject(i);
				storesArrayList.add(new Store(storesObject));
			}
			stores = (Store[]) storesArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			if(cats != null && cats.length > 0){
				JSONArray catsJsonArray = new JSONArray();
				for(Cat catsElement : cats){
					catsJsonArray.put(catsElement.toJsonObject());
				}
				jsonObject.put("cats", catsJsonArray);
			}
			if(stores != null && stores.length > 0){
				JSONArray storesJsonArray = new JSONArray();
				for(Store storesElement : stores){
					storesJsonArray.put(storesElement.toJsonObject());
				}
				jsonObject.put("stores", storesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}