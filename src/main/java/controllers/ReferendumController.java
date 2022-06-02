package controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.factory.DAOFactory;
import dao.sessione.SessioneIDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import models.utenti.*;
import models.sessione.*;
import models.voto.VotoReferendum;
import dao.voto.*;

public class ReferendumController extends Controller{
	
	Utente u;
	SessioneDiVoto sdv;
    @FXML
    private RadioButton cont;

    @FXML
    private RadioButton fav;

    @FXML
    private Button subm;
    
    @FXML
    private Label sessionelabel;
    Boolean selection=null;
    private IDAOVoto VotoDAO = DAOFactory.getFactory().getVotoDAOInstance();
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    @FXML
    void contro(ActionEvent event) {
    	fav.setSelected(false);
    	selection=true;
    }

    @FXML
    void favorevole(ActionEvent event) {
    	cont.setSelected(false);
    	selection=false;
    }

    @FXML
    void submit(ActionEvent event) {
    	int idutente=receiveUtente();
    	int idsessione=receiveId();
    	VotoReferendum vr=new VotoReferendum(idsessione,idutente,selection);
    	VotoDAO.castReferendum(vr);
    	
    }
    public void initialize() {
    	
    	
    	try {
			sdv=sessioneDAO.getById(receiveId());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sessionelabel.setText(sdv.getContenuto());
    	System.out.println(sdv.getContenuto());
    }

}






































