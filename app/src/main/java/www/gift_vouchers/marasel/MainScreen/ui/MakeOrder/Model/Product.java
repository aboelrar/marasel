package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model;//
//  Product.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 30, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Product{

	@SerializedName("cat")
	private String cat;
	@SerializedName("desc")
	private String desc;
	@SerializedName("icon")
	private String icon;
	@SerializedName("id")
	private int id;
	@SerializedName("images")
	private Image[] images;
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private String price;
	@SerializedName("quantity")
	private int quantity;
	@SerializedName("shore_desc")
	private String shoreDesc;

	public void setCat(String cat){
		this.cat = cat;
	}
	public String getCat(){
		return this.cat;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
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
	public void setImages(Image[] images){
		this.images = images;
	}
	public Image[] getImages(){
		return this.images;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public int getQuantity(){
		return this.quantity;
	}
	public void setShoreDesc(String shoreDesc){
		this.shoreDesc = shoreDesc;
	}
	public String getShoreDesc(){
		return this.shoreDesc;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Product(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		cat = jsonObject.optString("cat");
		desc = jsonObject.optString("desc");
		icon = jsonObject.optString("icon");
		name = jsonObject.optString("name");
		price = jsonObject.optString("price");
		shoreDesc = jsonObject.optString("shore_desc");
		id = jsonObject.optInt("id");
		quantity = jsonObject.optInt("quantity");
		JSONArray imagesJsonArray = jsonObject.optJSONArray("images");
		if(imagesJsonArray != null){
			ArrayList<Image> imagesArrayList = new ArrayList<>();
			for (int i = 0; i < imagesJsonArray.length(); i++) {
				JSONObject imagesObject = imagesJsonArray.optJSONObject(i);
				imagesArrayList.add(new Image(imagesObject));
			}
			images = (Image[]) imagesArrayList.toArray();
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
			jsonObject.put("desc", desc);
			jsonObject.put("icon", icon);
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			jsonObject.put("price", price);
			jsonObject.put("quantity", quantity);
			jsonObject.put("shore_desc", shoreDesc);
			if(images != null && images.length > 0){
				JSONArray imagesJsonArray = new JSONArray();
				for(Image imagesElement : images){
					imagesJsonArray.put(imagesElement.toJsonObject());
				}
				jsonObject.put("images", imagesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}