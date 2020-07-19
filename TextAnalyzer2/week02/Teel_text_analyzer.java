//Author name: Charles Teel Jr.
//Date: 6/20/2020
//Program Name: Teel_text_analyzer
//Purpose reads file submitted by user and prints out most frequently used words and Top 20 words.
//added GUI using JAVAFX for file input and display box for results; made word counter more efficient by using tree map instead of hash map.

package week02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



/**
 * <h1>Text Analyzer program </h1>
 * Reads file submitted by user and prints out most frequently used words and Top 20 words.
 *
 * Current version (5.0) Adds JavaDocs to text_analyzer program.
 * @author Charles Teel Jr. 
 *@date 7/16/2020
 *@version 5.0
 *@program Teel_text_analyzer
 *
 */



public class Teel_text_analyzer extends Application {
	
	

	private TextField fileNameTextField;
	private TextArea displayResultTextArea;
	Button btnStart, btClear, btExit;

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane border = new BorderPane();

		border.setCenter(addCenter());// Add the Gridpane to Borderpane object Center

		border.setBottom(addBottom());

		Scene scene = new Scene(border, 400, 250);
		stage.setScene(scene);
		stage.setTitle("Word Occurrences");
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}

	/**
	 * Buttons for GUI located on the bottom located in this pane 
	 * @EventHandler 
	 * Start
	 * Clear 
	 * Exit
	 * @return FlowPane
	 */
	
	public FlowPane addBottom() {
		this.btnStart = new Button("Start Counter");
		this.btnStart.setOnAction(new StartButtonEventHandler());
		this.btClear = new Button("Clear");
		this.btClear.setOnAction(new ClearButtonEventHandler());
		this.btExit = new Button("Exit");
		this.btExit.setOnAction(new ExitButtonEventHandler());

		FlowPane wordOccurenceMenuPane = new FlowPane();
		wordOccurenceMenuPane.setPadding(new Insets(10, 10, 10, 10));
		wordOccurenceMenuPane.setAlignment(Pos.CENTER);
		wordOccurenceMenuPane.setHgap(10);
		wordOccurenceMenuPane.setVgap(10);
		wordOccurenceMenuPane.getChildren().add(btnStart);
		wordOccurenceMenuPane.getChildren().add(btClear);
		wordOccurenceMenuPane.getChildren().addAll(btExit);

		return wordOccurenceMenuPane;
	}

	/**
	 * 
	 * @return filename box for inputting file , box for displaying text from file
	 *@return GridPane
	 *
	 *	 */
	public GridPane addCenter() {

		// Create Gridpane object

		GridPane gPane = new GridPane();

		gPane.setPadding(new Insets(11, 12, 13, 14));
		gPane.setHgap(10);
		gPane.setVgap(10);

		// create Label objects

		Label fileName = new Label("Please enter your filename: ");
		Label displayText = new Label("Most Counted words in this program are: ");

		// Text Field box for entering file name
		this.fileNameTextField = new TextField();

		// Text field for displaying results
		this.displayResultTextArea = new TextArea();
		this.displayResultTextArea.setPrefWidth(150);
		this.displayResultTextArea.setPrefHeight(300);
		this.displayResultTextArea.setFont(Font.font("Courier", FontWeight.NORMAL, 12));
		this.displayResultTextArea.setEditable(false);

		gPane.setAlignment(Pos.CENTER);

		// Add label object Enter filename

		gPane.add(fileName, 0, 0);

		// Add Text Field Object (File Name Box)
		gPane.add(fileNameTextField, 1, 0);

		// Add display
		gPane.add(displayText, 0, 1);

		// Add Text Field Object
		gPane.add(displayResultTextArea, 1, 1);

		return gPane;

	}

	public static void main(String[] args) throws FileNotFoundException {

		launch(args);
	}

	/**
	 * @Button-Exit
	 * Action event tied to Exit button that automatically closes program when clicked.
	 * @ActionEvent
	 * 
	 */
	
	class ExitButtonEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Platform.exit();

		}

	}
	
	/**
	 * @Button-Clear
	 * Action event tied to Clear button that automatically clears all text fields when clear button is clicked.
	 * @ActionEvent
	 *
	 */

	class ClearButtonEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			fileNameTextField.clear();
			displayResultTextArea.clear();
		}
	}
	
	/**
	 * @Button-Start
	 * Action event tied to Start button that automatically starts scan of all text within uploaded filename when start button is clicked.
	 * @ActionEvent
	 *
	 */

	class StartButtonEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			try {
				//read input text
				String fileName = fileNameTextField.getText();
				displayResultTextArea.setText(getWordUsageFromText(fileName));

			} catch (FileNotFoundException e) {
				System.out.print("File not found");
			}

		}

	}
	
	/**
	 * 
	 * @param filename name of the text file uploaded into the text analyzer program
	 * @return word occurrences within the text file
	 * @throws FileNotFoundException
	 */

	public String getWordUsageFromText(String filename) throws FileNotFoundException {
		// create a treemap and a scanner for the file
		Map<String, Integer> count = new HashMap<String, Integer>();

		Scanner input = new Scanner(new File(filename));

		// continue to read the file until lines run out
		while (input.hasNextLine()) {
			// get the line
			String line = input.nextLine();

			String[] words = line.split("[^\\w']+");
			// System.out.println(Arrays.toString(words));
			for (String word : words) {
				if (word.isEmpty()) {
					continue;
				}
				if (count.containsKey(word.toLowerCase())) {
					count.put(word.toLowerCase(), count.get(word.toLowerCase()) + 1);
				} else {
					count.put(word.toLowerCase(), 1);
				}
			}
		}

		// create treemap (sorted map)
		Map<String, Integer> mostUsed = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String word1, String word2) {
				return count.get(word2) < count.get(word1) ? -1 : 1;  //don't return 0 or treemap won't add it
			}
		});

		//add all counts/words to treemap
		for (String word : count.keySet()) {
			//add to treemap
			mostUsed.put(word, count.get(word));
		}

		// log all word use frequencies
		// System.out.println("All Word Frequencies from the file:");
		// System.out.println(mostUsed);

		// build string to display in GUI output
		StringBuilder output = new StringBuilder();
		
		//must prepare values separately as treemap has issues with getting value when there are duplicate values
		List<Integer> values = new ArrayList<>(mostUsed.values());
		int idx = 0;

		//get longest length of top words for display purposes
		int longestLength = 0;
		for (String word : mostUsed.keySet()) {
			if (idx >= 20) {
				break;
			}
			//record longest word length
			if (word.length() > longestLength) {
				longestLength = word.length();
			}

			//increment counter
			idx++;
		}

		//reset counter
		idx = 0;

		//build output string
		for (String word : mostUsed.keySet()) {
			if (idx >= 20) {
				break;
			}
			//output with word left-justified with uniform length based on longest word length (i.e. %-7s)
			output.append(String.format("%-" + longestLength + "s = %d uses \n", word, values.get(idx)));  //get value by list index not by map


			//increment counter
			idx++;
		}

		//output
		return output.toString();
	}
}
