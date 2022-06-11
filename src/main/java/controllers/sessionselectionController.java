package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.sessione.*;
import models.utenti.UtentiHolder;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.factory.DAOFactory;
import dao.sessione.SessioneIDAO;
import dao.utenti.IDAOUtenti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.utenti.*;

public class sessionselectionController extends Controller{

	
	Utente u;
    @FXML
    private Button button;
    @FXML
    private Button Btngoback;

    @FXML
    private TableColumn<SessioneDiVoto, String> contenuto;

    @FXML
    private TableColumn<SessioneDiVoto, Integer> sessioni;

    @FXML
    private TableView<SessioneDiVoto> tabellasessioni;

    @FXML
    private TableColumn<SessioneDiVoto, TipoSessione> tipo;
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();

    @FXML
    private Label utentelbl;


    
    
    /**
     * Referendum("Referendum"),
	OrdinaleCandidati("Ordinale con candidati"),
	OrdinalePartiti("Ordinale con partiti"),
	CategoricoCandidati("Categorico con candidati"),
	CategoricoPartiti("Categorico con partiti"),
	CategoricoPreferenze("Categorico con preferenze");
     * @param event
     * @throws IOException
     */
    @FXML
    void handlebutton(ActionEvent event) throws IOException {
    	SessioneDiVoto s = tabellasessioni.getSelectionModel().getSelectedItem();
    	if(u.isScrutatore()) {    		
    		SendId(s.getNumeroSessione());
    		changeView("views/sessionformProperties.fxml",event);
    	}

		IdHolder holder = IdHolder.getInstance();
		holder.setId(s.getNumeroSessione());
		
		

		
    	switch (s.getTipoSessione()) {
    		case Referendum:
    			changeView("views/referendumform.fxml",event);
    			break;
    		case Ordinale:
    	        changeView("views/voteOrdinaryform.fxml",event);
    			break;
    		case Categorico:
    			changeView("views/voteCategoricform.fxml",event);
    			break;
    	}
    			
    	
    	
    	
    }
    @FXML
  	void goback(ActionEvent event) {
  		changeView("views/operationform.fxml",event);
  	}
    public void initialize() {
    	u=utenteDAO.UtentebyId(receiveUtente());
    	utentelbl.setText("Utente loggato: "+u.getfirstname()+" "+u.getlastname() );
    	sessioni.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,Integer>("numeroSessione"));
    	tipo.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,TipoSessione>("tipoSessione"));
    	contenuto.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,String>("contenuto"));   	
    	tabellasessioni.setItems(getSessioneDiVoto());
    	
    	
    }
    ObservableList<SessioneDiVoto> getSessioneDiVoto(){
    		List<SessioneDiVoto> listasessioni=new ArrayList<>();
    		List<SessioneDiVoto> sessioniaperte=new ArrayList<>();
    		ObservableList<SessioneDiVoto> lista;
    		try {
				listasessioni=sessioneDAO.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		for(SessioneDiVoto sdv:listasessioni) {
    			if(sdv.getIsOpen()) {
    				sessioniaperte.add(sdv);
    			}
    		}
    	
    		lista=FXCollections.observableArrayList(sessioniaperte);
			return lista;
		
    }

}
