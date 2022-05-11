package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.sessione.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class sessionselectionController extends Controller{

    @FXML
    private Button button;

    @FXML
    private TableColumn<SessioneDiVoto, String> contenuto;

    @FXML
    private TableColumn<SessioneDiVoto, Integer> sessioni;

    @FXML
    private TableView<SessioneDiVoto> tabellasessioni;

    @FXML
    private TableColumn<SessioneDiVoto, TipoSessione> tipo;
    private Parent root;
    @FXML
    void handlebutton(ActionEvent event) throws IOException {
    	SessioneDiVoto s = tabellasessioni.getSelectionModel().getSelectedItem();
    	FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("views/login.fxml"));
    	root=loader.load();
    	LoginController logincontroller=loader.getController();
    	logincontroller.getinfo(s.getContenuto());
    	changeView("views/login.fxml",event);
    }
   
    public void initialize() {

    	sessioni.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,Integer>("numerosessione"));
    	tipo.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,TipoSessione>("tiposessione"));
    	contenuto.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,String>("contenuto"));   	
    	tabellasessioni.setItems(getSessioneDiVoto());
    	
    	
    }
    ObservableList<SessioneDiVoto> getSessioneDiVoto(){
    		ObservableList<SessioneDiVoto> lista=FXCollections.observableArrayList();
			lista.add( new SessioneDiVoto(1,TipoSessione.Referendum,"prova"));
			lista.add(new SessioneDiVoto(2,TipoSessione.Referendum,"prova1"));
			return lista;
		
    }
}
