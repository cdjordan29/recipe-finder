package recipe;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class Controller represents the Controller for Project 4,
 * its also where the JSON link is scanned to make the List of Recipes
 * @author Daniel Jordan
 * @version 1.0
 */
public class Controller {

	private Recipe r;
	private List<Recipe> myRecipeList;
	
	/**
	 * Constructor for the Controller class 
	 */
	public Controller(){
		
		myRecipeList = new ArrayList<>();
	}
		
	/**
	 * The method getList returns the List of Recipes
	 */
	public List<Recipe> getList(){
		
		return myRecipeList;
	}
	
	/**
	 * The method makeRecipe brings together the input from the View
	 * and scans the weblink to get the Recipe Objects
	 * @throws Exception 
	 */
	public void makeRecipe(String inputIngredients, String inputRecipe) throws JSONException{
		
		String JSonString = "";
		myRecipeList.clear();
		 try {
			inputIngredients = URLEncoder.encode(inputIngredients, "UTF-8");
			inputRecipe = URLEncoder.encode(inputRecipe, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			JSonString = readURL(
						 	"http://www.recipepuppy.com/api/?i=" + 
				inputIngredients + "&q=" + inputRecipe + "&p=3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 JSONObject x = new JSONObject(JSonString);
		 JSONArray recipeInfo = x.getJSONArray("results");
		 
		 if(recipeInfo != null){
			 
			 for(int i = 0; i < recipeInfo.length(); i++){
				 
				 r = new Recipe(recipeInfo.getJSONObject(i).getString("title").replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", ""),
					 			recipeInfo.getJSONObject(i).getString("href").replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", ""),
					 			recipeInfo.getJSONObject(i).getString("ingredients").replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", ""));
				 myRecipeList.add(r);
			 }
		 }
		 else if (x.getJSONArray("results").length() == 0){
			 
			 String title = "No Title found";
			 String href = "No Link found";
			 String ingredients = "No Ingredients found";
			 r = new Recipe(title, href, ingredients);
			 
			 myRecipeList.add(r);
		 }
	}

	/**
	 * The method readURL reads the URL in and builds the information as a String
	 * @param webservice
	 * @return String result
	 * @throws Exception
	 */
	public static String readURL(String webservice) throws Exception {

		URL oracle = new URL(webservice);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		String result = "";

		while ((inputLine = in.readLine()) != null)
			result = result + inputLine;

		in.close();
		return result;
	}
}
