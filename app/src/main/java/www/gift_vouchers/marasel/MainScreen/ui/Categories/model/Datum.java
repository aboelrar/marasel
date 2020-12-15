package www.gift_vouchers.marasel.MainScreen.ui.Categories.model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 14, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("cat")
	private String cat;
	@SerializedName("categories")
	private Category[] categories;
	@SerializedName("distance")
	private String distance;
	@SerializedName("free_delivery")
	private int freeDelivery;
	@SerializedName("from")
	private Object from;
	@SerializedName("icon")
	private String icon;
	@SerializedName("id")
	private int id;
	@SerializedName("images")
	private Object[] images;
	@SerializedName("is_24")
	private int is24;
	@SerializedName("lat")
	private String lat;
	@SerializedName("lng")
	private String lng;
	@SerializedName("name")
	private String name;
	@SerializedName("rate")
	private int rate;
	@SerializedName("to")
	private Object to;

	public void setCat(String cat){
		this.cat = cat;
	}
	public String getCat(){
		return this.cat;
	}
	public void setCategories(Category[] categories){
		this.categories = categories;
	}
	public Category[] getCategories(){
		return this.categories;
	}
	public void setDistance(String distance){
		this.distance = distance;
	}
	public String getDistance(){
		return this.distance;
	}
	public void setFreeDelivery(int freeDelivery){
		this.freeDelivery = freeDelivery;
	}
	public int getFreeDelivery(){
		return this.freeDelivery;
	}
	public void setFrom(Object from){
		this.from = from;
	}
	public Object getFrom(){
		return this.from;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return this.icon;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImages(Object[] images){
		this.images = images;
	}
	public Object[] getImages(){
		return this.images;
	}
	public void setIs24(int is24){
		this.is24 = is24;
	}
	public int getIs24(){
		return this.is24;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLat(){
		return this.lat;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getLng(){
		return this.lng;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}
	public void setTo(Object to){
		this.to = to;
	}
	public Object getTo(){
		return this.to;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		cat = jsonObject.optString("cat");
		distance = jsonObject.optString("distance");
		icon = jsonObject.optString("icon");
		lat = jsonObject.optString("lat");
		lng = jsonObject.optString("lng");
		name = jsonObject.optString("name");
		freeDelivery = jsonObject.optInt("free_delivery");
		from = jsonObject.optString("from");
		id = jsonObject.optInt("id");
		is24 = jsonObject.optInt("is_24");
		rate = jsonObject.optInt("rate");
		to = jsonObject.optString("to");
//		images = jsonObject.optObject[]("images");
		JSONArray categoriesJsonArray = jsonObject.optJSONArray("categories");
		if(categoriesJsonArray != null){
			ArrayList<Category> categoriesArrayList = new ArrayList<>();
			for (int i = 0; i < categoriesJsonArray.length(); i++) {
				JSONObject categoriesObject = categoriesJsonArray.optJSONObject(i);
				categoriesArrayList.add(new Category(categoriesObject));
			}
			categories = (Category[]) categoriesArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("cat", cat);
			jsonObject.put("distance", distance);
			jsonObject.put("free_delivery", freeDelivery);
			jsonObject.put("from", from);
			jsonObject.put("icon", icon);
			jsonObject.put("id", id);
			jsonObject.put("images", images);
			jsonObject.put("is_24", is24);
			jsonObject.put("lat", lat);
			jsonObject.put("lng", lng);
			jsonObject.put("name", name);
			jsonObject.put("rate", rate);
			jsonObject.put("to", to);
			if(categories != null && categories.length > 0){
				JSONArray categoriesJsonArray = new JSONArray();
				for(Category categoriesElement : categories){
					categoriesJsonArray.put(categoriesElement.toJsonObject());
				}
				jsonObject.put("categories", categoriesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}