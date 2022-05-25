package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import models.utenti.*;
import models.sessione.*;

public class ReferendumController extends Controller{
	
	Utente u;
	SessioneDiVoto sdv;
    @FXML
    private RadioButton cont;

    @FXML
    private RadioButton fav;

    @FXML
    private Label lbl;

    @FXML
    private Button subm;

    @FXML
    void contro(ActionEvent event) {
    	fav.setSelected(false);
    }

    @FXML
    void favorevole(ActionEvent event) {
    	cont.setSelected(false);
    }

    @FXML
    void submit(ActionEvent event) {

    }

}
