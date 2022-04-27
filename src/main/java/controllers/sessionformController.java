
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class sessionformController extends Controller implements Initializable {
	
	

	private final String url = "jdbc:mysql://localhost/easyvote";
	
	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
	
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Text lblOutput;
    
    @FXML
    private TextArea textArea;
    
    
    @FXML
    void confirm(ActionEvent event) throws IOException{
    	
    }
    
    
    @FXML
    void goBack(ActionEvent event)  throws IOException{
    	changeView("easyVoteproject/resources/pageform.fxml",event);
    }

    @FXML
    void submit(ActionEvent event) throws IOException{
    	Query();
    	changeView("easyVoteproject/resources/sessionformProperties.fxml",event);
    }
    
    
    private int Query() {
    	try {
		       Connection conn = DriverManager.getConnection(url, "prova", "");
		       
		       
		       
		       
		       String Query = "INSERT INTO `easyVote`.`session` (`text`, `type`) "
				   		+ "VALUES (?, ?)";

		       PreparedStatement preparedStatement = conn.prepareStatement(Query);
		       preparedStatement.setString(1, choiceBox.getSelectionModel().getSelectedItem());
		       preparedStatement.setString(2, textArea.getText());
			   preparedStatement.executeUpdate();
			   
			   int value = getValue(conn);
			   
			   
			   lblOutput.setText("Sessione con numero " + value + " registrata con successo");	
			   lblOutput.setVisible(true);

		    	return value;
		 	
			   
		    } catch (SQLException ex) {
		    	System.out.println("SQLExeption: "+ex.getMessage());
				System.out.println("SQLState: "+ex.getSQLState());
				System.out.println("VendorError: "+ ex.getErrorCode());
		    }
    	return 0;
	}


	private int getValue(Connection conn) throws SQLException {

   	 	String Query = "SELECT * FROM session ORDER BY id DESC LIMIT 1;";
   	 	PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(Query);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}


	/**
     * 
     * 	
	Referendum{},
	OrdinaleCandidati{},
	OrdinalePartiti{},
	CategoricoCandidati{},
	CategoricoPartiti{},
	CategoricoPreferenze{
     * 
     * 
     * 
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.setValue("Referendum");
		choiceBox.getItems().add("Ordinale con candidati");
		choiceBox.getItems().add("Ordinali con partiti");
		choiceBox.getItems().add("Categorico con candidati");
		choiceBox.getItems().add("Categorico con partiti");
		choiceBox.getItems().add("Categorico con preferenze");
	}
}
