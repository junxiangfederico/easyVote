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

public class sessionselectionbyAdminController extends Controller{

	
	Utente u;
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
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();

    @FXML
    private Label utentelbl;
    
    @FXML
	void goback(ActionEvent event) {
		changeView("views/operationform.fxml",event);
	}
    
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
		SendId(s.getNumeroSessione());
		if(u.isScrutatore()) {
			s.setIsOpen(false);
			sessioneDAO.update(s);
			changeView("views/operationform.fxml",event);
		}else {
			switch (s.getTipoSessione()) {
    		case Referendum:
    			changeView("views/resultsReferendumForm.fxml",event);
    			break;    	        
    		case Ordinale:
    			changeView("views/resultsOrdinaryForm.fxml",event);
    			break;
    		case Categorico:
    			changeView("views/resultsCategoricForm.fxml",event);
    	}
		}		  			    	

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
    		List<SessioneDiVoto> sessionichiuse=new ArrayList<>();

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
    		for(SessioneDiVoto sdv:listasessioni) {
    			if(!(sdv.getIsOpen())) {
    				sessionichiuse.add(sdv);
    			}
    		}

    		
    		if(u.isScrutatore()) {
    			lista=FXCollections.observableArrayList(sessioniaperte);
    		}else {
    			lista=FXCollections.observableArrayList(sessionichiuse);
    		}
			return lista;

		
    }

}
