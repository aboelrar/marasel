package www.gift_vouchers.marasel.MainScreen.ui.Cart.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("products")
	private Product[] products;
	@SerializedName("store")
	private Store store;
	@SerializedName("total_price")
	private int totalPrice;

	public void setProducts(Product[] products){
		this.products = products;
	}
	public Product[] getProducts(){
		return this.products;
	}
	public void setStore(Store store){
		this.store = store;
	}
	public Store getStore(){
		return this.store;
	}
	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}
	public int getTotalPrice(){
		return this.totalPrice;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		totalPrice = jsonObject.optInt("total_price");
		store = new Store(jsonObject.optJSONObject("store"));
		JSONArray productsJsonArray = jsonObject.optJSONArray("products");
		if(productsJsonArray != null){
			ArrayList<Product> productsArrayList = new ArrayList<>();
			for (int i = 0; i < productsJsonArray.length(); i++) {
				JSONObject productsObject = productsJsonArray.optJSONObject(i);
				productsArrayList.add(new Product(productsObject));
			}
			products = (Product[]) productsArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("total_price", totalPrice);
			jsonObject.put("store", store.toJsonObject());
			if(products != null && products.length > 0){
				JSONArray productsJsonArray = new JSONArray();
				for(Product productsElement : products){
					productsJsonArray.put(productsElement.toJsonObject());
				}
				jsonObject.put("products", productsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}