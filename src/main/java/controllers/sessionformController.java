
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dao.sessione.*;
import dao.factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.sessione.*;

public class sessionformController extends Controller {
	
	
	
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Text lblOutput;
    
    @FXML
    private TextArea textArea;
    
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    @FXML
    void confirm(ActionEvent event) throws IOException{
    	
    }
    
    
    @FXML
    void goBack(ActionEvent event)  throws IOException{
    	changeView("views/login.fxml",event);
    }

    @FXML
    void submit(ActionEvent event) throws IOException{
    	int id=Query();
    	IdHolder holder = IdHolder.getInstance();
	    holder.setId(id);
	    changeView("views/sessionformProperties.fxml",event);
    }
    
    
    private int Query() {
    	int ris=sessioneDAO.aggiungi(textArea.getText(), choiceBox.getSelectionModel().getSelectedItem());
		return ris;
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
	public void initialize() {
		//choiceBox.setValue("Referendum");

		choiceBox.setValue("Referendum");

		choiceBox.getItems().add("Referendum");
		choiceBox.getItems().add("Singolo con candidati");
		choiceBox.getItems().add("Singolo con partiti");
		choiceBox.getItems().add("Ordinale con preferenze su candidati");
		choiceBox.getItems().add("Ordinale con preferenze su partiti");
		choiceBox.getItems().add("Categorico con candidati");
		choiceBox.getItems().add("Categorico con partiti");
		choiceBox.getItems().add("Categorico con preferenze");
	}
}
