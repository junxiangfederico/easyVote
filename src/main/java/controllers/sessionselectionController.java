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
import java.util.ArrayList;
import java.util.List;

import dao.factory.DAOFactory;
import dao.sessione.SessioneIDAO;
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
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private Parent root;
    
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

		IdHolder holder = IdHolder.getInstance();
		holder.setId(s.getNumeroSessione());
    	switch (s.getTipoSessione()) {
    		case Referendum:
    			break;
    		case OrdinaleCandidati:

    	        changeView("views/voteOrdinaryform.fxml",event);
    			break;
    		case OrdinalePartiti:

    	        changeView("views/voteOrdinaryform.fxml",event);
    			break;
    		case CategoricoCandidati:
    			break;
    		case CategoricoPartiti:
    			break;
    		case CategoricoPreferenze:

    	        changeView("views/votePreferentialform.fxml",event);
    			break;
    	}
    			
    	
    	
    	
        //changeView("views/voteOrdinaryform.fxml",event);
    }
   
    public void initialize() {

    	sessioni.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,Integer>("numeroSessione"));
    	tipo.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,TipoSessione>("tipoSessione"));
    	contenuto.setCellValueFactory(new PropertyValueFactory<SessioneDiVoto,String>("contenuto"));   	
    	tabellasessioni.setItems(getSessioneDiVoto());
    	
    	
    }
    ObservableList<SessioneDiVoto> getSessioneDiVoto(){
    		List<SessioneDiVoto> listasessioni=new ArrayList<>();
    		try {
				listasessioni=sessioneDAO.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		ObservableList<SessioneDiVoto> lista=FXCollections.observableArrayList(listasessioni);
			return lista;
		
    }
}
