package www.gift_vouchers.marasel.MainScreen.ui.Offers.AcceptOrRejectModel;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 23, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("offers")
	private Object[] offers;
	@SerializedName("order")
	private Order order;

	public void setOffers(Object[] offers){
		this.offers = offers;
	}
	public Object[] getOffers(){
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
//		offers = jsonObject.optObject[]("offers");
		order = new Order(jsonObject.optJSONObject("order"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("offers", offers);
			jsonObject.put("order", order.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}