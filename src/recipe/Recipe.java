package recipe;

/**
 * The class Recipe represents the Model of Project 4
 * the objects will represent recipes from the JSON link in the Controller
 * @author Daniel Jordan
 * @version 1.0
 */
public class Recipe {
	
	private String title;
	private String link;
	private String ingredients;
	
	/**
	 * Constructor for the Recipe objects
	 */
	public Recipe(String _title, String _link, String _ingredients){
		
		this.title = _title;
		this.link = _link;
		this.ingredients = _ingredients;
	}
	
	/**
	 * The toString method for Recipe
	 */
	public String toString(){
		
		String result = "";

		result = "\n" +
				 "Recipe Title: " + this.title + "\n" +
				 "WebLink: " + this.link + "\n" +
				 "Ingredients: " + this.ingredients + "\n";
		
		return result;
	}
	
	/**
	 * The getter for title
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * The setter for title
	 */
	public void setTitle(String _title) {
		this.title = _title;
	}

	/**
	 * The getter for link
	 * @return String link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * The setter for link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * The getter for ingredients
	 * @return String ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}

	/**
	 * The setter for ingredients
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

}
