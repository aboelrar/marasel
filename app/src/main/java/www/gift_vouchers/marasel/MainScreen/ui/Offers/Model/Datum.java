package www.gift_vouchers.marasel.MainScreen.ui.Offers.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 20, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("offers")
	private Offer[] offers;
	@SerializedName("order")
	private Order order;

	public void setOffers(Offer[] offers){
		this.offers = offers;
	}
	public Offer[] getOffers(){
		return this.offers;
	}
	public void setOrder(Order order){
		this.order = order;
	}
	public Order getOrder(){
		return this.order;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		order = new Order(jsonObject.optJSONObject("order"));
		JSONArray offersJsonArray = jsonObject.optJSONArray("offers");
		if(offersJsonArray != null){
			ArrayList<Offer> offersArrayList = new ArrayList<>();
			for (int i = 0; i < offersJsonArray.length(); i++) {
				JSONObject offersObject = offersJsonArray.optJSONObject(i);
				offersArrayList.add(new Offer(offersObject));
			}
			offers = (Offer[]) offersArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("order", order.toJsonObject());
			if(offers != null && offers.length > 0){
				JSONArray offersJsonArray = new JSONArray();
				for(Offer offersElement : offers){
					offersJsonArray.put(offersElement.toJsonObject());
				}
				jsonObject.put("offers", offersJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}