package recipe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.*;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

/**
 * The class Project4View represents the GUI for Project 4
 * 
 * @author Daniel Jordan
 * @version 1.0
 */
public class Project4View {

	private JFrame frmRecipeFinder;
	private JTextField ingredientsTextField;
	private JTextField recipeTextField;
	private String ingredientsInput, recipeInput;
	private List<Recipe> completeRecipeList;
	private Controller c;

	/**
	 * Constructor for Project4View.
	 */
	public Project4View() {
		ingredientsInput = "";
		recipeInput = "";
		initialize();
		c = new Controller();
	}

	/**
	 * The method initialize gets the contents for the JFrame.
	 */
	private void initialize() {
		frmRecipeFinder = new JFrame();
		frmRecipeFinder.setTitle("RECIPE FINDER");
		frmRecipeFinder.setBounds(100, 100, 1199, 928);
		frmRecipeFinder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecipeFinder.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frmRecipeFinder.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel titleLabel = new JLabel("RECIPE FINDER");
		titleLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		panel.add(titleLabel, gbc_titleLabel);

		JLabel ingredientsLabel = new JLabel("Input ingredients you'd like to use below");
		ingredientsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_ingredientsLabel = new GridBagConstraints();
		gbc_ingredientsLabel.anchor = GridBagConstraints.WEST;
		gbc_ingredientsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_ingredientsLabel.gridx = 1;
		gbc_ingredientsLabel.gridy = 2;
		panel.add(ingredientsLabel, gbc_ingredientsLabel);

		ingredientsTextField = new JTextField();
		ingredientsTextField.setText("ex. onions, garlic");
		ingredientsTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_ingredientsTextField = new GridBagConstraints();
		gbc_ingredientsTextField.anchor = GridBagConstraints.WEST;
		gbc_ingredientsTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ingredientsTextField.gridx = 1;
		gbc_ingredientsTextField.gridy = 3;
		panel.add(ingredientsTextField, gbc_ingredientsTextField);
		ingredientsTextField.setColumns(15);

		JLabel recipeLabel = new JLabel("Input the recipe you'd like to try below.");
		recipeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_recipeLabel = new GridBagConstraints();
		gbc_recipeLabel.anchor = GridBagConstraints.WEST;
		gbc_recipeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_recipeLabel.gridx = 1;
		gbc_recipeLabel.gridy = 4;
		panel.add(recipeLabel, gbc_recipeLabel);

		recipeTextField = new JTextField();
		recipeTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		recipeTextField.setText("ex. pizza");
		GridBagConstraints gbc_recipeTextField = new GridBagConstraints();
		gbc_recipeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_recipeTextField.anchor = GridBagConstraints.WEST;
		gbc_recipeTextField.gridx = 1;
		gbc_recipeTextField.gridy = 5;
		panel.add(recipeTextField, gbc_recipeTextField);
		recipeTextField.setColumns(15);

		JTextPane outputPane = new JTextPane();
		JScrollPane scroll = new JScrollPane(outputPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputPane.setText("Recipe results will display here.");
		outputPane.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_outputPane = new GridBagConstraints();
		gbc_outputPane.insets = new Insets(0, 0, 5, 0);
		gbc_outputPane.fill = GridBagConstraints.BOTH;
		gbc_outputPane.gridx = 1;
		gbc_outputPane.gridy = 6;
		panel.add(scroll, gbc_outputPane);

		/**
		 * The ActionListener for the runButton
		 */
		JButton runButton = new JButton("Find Recipes");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// clears the outputPane's text so the previous searches will
				// not appear
				outputPane.setText("");
				// getting the input from the user
				setIngredients(ingredientsTextField.getText());
				setRecipe(recipeTextField.getText());
				// making the recipe List in the Controller
				try {
					c.makeRecipe(getIngredients(), getRecipe());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// getting the recipe List from the Controller
				completeRecipeList = c.getList();

				// iteration over the complete List of Recipes to display the
				// contents in the View
				for (Recipe r : completeRecipeList) {

					outputPane.setText(outputPane.getText() + r.toString());
				}
			}
		});
		runButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		GridBagConstraints gbc_runButton = new GridBagConstraints();
		gbc_runButton.insets = new Insets(0, 0, 5, 0);
		gbc_runButton.gridx = 1;
		gbc_runButton.gridy = 7;
		panel.add(runButton, gbc_runButton);

		/**
		 * The ActionListener for the clearButton
		 */
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// clears all the View contents when the button is clicked
				setIngredients("");
				setRecipe("");
				ingredientsTextField.setText("");
				recipeTextField.setText("");
				outputPane.setText("");
			}
		});
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.insets = new Insets(0, 0, 5, 0);
		gbc_clearButton.gridx = 1;
		gbc_clearButton.gridy = 8;
		panel.add(clearButton, gbc_clearButton);
	}

	/**
	 * The method run launches the View.
	 */
	public void run() {
		try {
			Project4View window = new Project4View();
			window.frmRecipeFinder.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method getIngredients returns the ingredients the user inputs into
	 * the View.
	 * 
	 * @return String result
	 */
	public String getIngredients() {

		String result = "";
		result = this.ingredientsInput;
		return result;
	}

	/**
	 * The method setIngredients sets ingredientsInput to the user's input.
	 * 
	 * @param String
	 *            _ingredients
	 */
	public void setIngredients(String _ingredients) {
		this.ingredientsInput = _ingredients;
	}

	/**
	 * The method getRecipe returns the recipe the user input into the View.
	 * 
	 * @return String result
	 */
	public String getRecipe() {

		String result = "";
		result = this.recipeInput;
		return result;
	}

	/**
	 * The method setRecipe sets recipeInput to the user's input.
	 * 
	 * @param _recipe
	 */
	public void setRecipe(String _recipe) {
		this.recipeInput = _recipe;
	}

}
