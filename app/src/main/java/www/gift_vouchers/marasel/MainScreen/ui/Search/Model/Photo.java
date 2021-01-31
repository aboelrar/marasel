package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  Photo.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Photo{

	@SerializedName("height")
	private int height;
	@SerializedName("html_attributions")
	private String[] htmlAttributions;
	@SerializedName("photo_reference")
	private String photoReference;
	@SerializedName("width")
	private int width;

	public void setHeight(int height){
		this.height = height;
	}
	public int getHeight(){
		return this.height;
	}
	public void setHtmlAttributions(String[] htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}
	public String[] getHtmlAttributions(){
		return this.htmlAttributions;
	}
	public void setPhotoReference(String photoReference){
		this.photoReference = photoReference;
	}
	public String getPhotoReference(){
		return this.photoReference;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getWidth(){
		return this.width;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Photo(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		photoReference = jsonObject.optString("photo_reference");
		height = jsonObject.optInt("height");
		width = jsonObject.optInt("width");
//		htmlAttributions = jsonObject.optString[]("html_attributions");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("height", height);
			jsonObject.put("html_attributions", htmlAttributions);
			jsonObject.put("photo_reference", photoReference);
			jsonObject.put("width", width);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}