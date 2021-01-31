package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  GoogleArray.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class GoogleArray{

	@SerializedName("business_status")
	private String businessStatus;
	@SerializedName("formatted_address")
	private String formattedAddress;
	@SerializedName("geometry")
	private Geometry geometry;
	@SerializedName("icon")
	private String icon;
	@SerializedName("name")
	private String name;
	@SerializedName("opening_hours")
	private OpeningHour openingHours;
	@SerializedName("photos")
	private Photo[] photos;
	@SerializedName("place_id")
	private String placeId;
	@SerializedName("plus_code")
	private PlusCode plusCode;
	@SerializedName("price_level")
	private int priceLevel;
	@SerializedName("rating")
	private Double rating;
	@SerializedName("reference")
	private String reference;
	@SerializedName("types")
	private String[] types;
	@SerializedName("user_ratings_total")
	private int userRatingsTotal;

	public void setBusinessStatus(String businessStatus){
		this.businessStatus = businessStatus;
	}
	public String getBusinessStatus(){
		return this.businessStatus;
	}
	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}
	public String getFormattedAddress(){
		return this.formattedAddress;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return this.icon;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setOpeningHours(OpeningHour openingHours){
		this.openingHours = openingHours;
	}
	public OpeningHour getOpeningHours(){
		return this.openingHours;
	}
	public void setPhotos(Photo[] photos){
		this.photos = photos;
	}
	public Photo[] getPhotos(){
		return this.photos;
	}
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}
	public String getPlaceId(){
		return this.placeId;
	}
	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}
	public PlusCode getPlusCode(){
		return this.plusCode;
	}
	public void setPriceLevel(int priceLevel){
		this.priceLevel = priceLevel;
	}
	public int getPriceLevel(){
		return this.priceLevel;
	}
	public void setRating(double rating){
		this.rating = rating;
	}
	public Double getRating(){
		return this.rating;
	}
	public void setReference(String reference){
		this.reference = reference;
	}
	public String getReference(){
		return this.reference;
	}
	public void setTypes(String[] types){
		this.types = types;
	}
	public String[] getTypes(){
		return this.types;
	}
	public void setUserRatingsTotal(int userRatingsTotal){
		this.userRatingsTotal = userRatingsTotal;
	}
	public int getUserRatingsTotal(){
		return this.userRatingsTotal;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public GoogleArray(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		businessStatus = jsonObject.optString("business_status");
		formattedAddress = jsonObject.optString("formatted_address");
		icon = jsonObject.optString("icon");
		name = jsonObject.optString("name");
		placeId = jsonObject.optString("place_id");
		reference = jsonObject.optString("reference");
		priceLevel = jsonObject.optInt("price_level");
		rating = jsonObject.optDouble("rating");
		userRatingsTotal = jsonObject.optInt("user_ratings_total");
//		types = jsonObject.optString[]("types");
		geometry = new Geometry(jsonObject.optJSONObject("geometry"));
		openingHours = new OpeningHour(jsonObject.optJSONObject("opening_hours"));
		plusCode = new PlusCode(jsonObject.optJSONObject("plus_code"));
		JSONArray photosJsonArray = jsonObject.optJSONArray("photos");
		if(photosJsonArray != null){
			ArrayList<Photo> photosArrayList = new ArrayList<>();
			for (int i = 0; i < photosJsonArray.length(); i++) {
				JSONObject photosObject = photosJsonArray.optJSONObject(i);
				photosArrayList.add(new Photo(photosObject));
			}
			photos = (Photo[]) photosArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("business_status", businessStatus);
			jsonObject.put("formatted_address", formattedAddress);
			jsonObject.put("icon", icon);
			jsonObject.put("name", name);
			jsonObject.put("place_id", placeId);
			jsonObject.put("price_level", priceLevel);
			jsonObject.put("rating", rating);
			jsonObject.put("reference", reference);
			jsonObject.put("types", types);
			jsonObject.put("user_ratings_total", userRatingsTotal);
			jsonObject.put("geometry", geometry.toJsonObject());
			jsonObject.put("openingHours", openingHours.toJsonObject());
			jsonObject.put("plusCode", plusCode.toJsonObject());
			if(photos != null && photos.length > 0){
				JSONArray photosJsonArray = new JSONArray();
				for(Photo photosElement : photos){
					photosJsonArray.put(photosElement.toJsonObject());
				}
				jsonObject.put("photos", photosJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}