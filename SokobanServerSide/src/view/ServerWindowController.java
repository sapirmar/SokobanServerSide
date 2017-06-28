package view;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClientDetails;
import viewModel.ViewModel;

public class ServerWindowController implements Initializable, View, Observer {
	private ViewModel viewModel;
	private ObservableList<ClientDetails> clientData;

	@FXML
	TableView<ClientDetails> clientTable;
	@FXML
	TableColumn<ClientDetails, String> status;
	
	@FXML
	TableColumn<ClientDetails,String> ipColumn;
	@FXML
	TableColumn<ClientDetails,Integer> portColumn;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clientData = FXCollections.observableArrayList();
		clientTable.setEditable(true);
		ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
		clientTable.setItems(clientData);
		
		
	}

	//getter and setters
	
	public ViewModel getViewModel() {
		return viewModel;
	}

	public ObservableList<ClientDetails> getClientData() {
		return clientData;
	}

	public void setClientData(ObservableList<ClientDetails> clientData) {
		this.clientData = clientData;
	}

	public void setViewModel(ViewModel viewModel) {
		this.viewModel = viewModel;
		if(viewModel!=null){
			viewModel.setData(clientData);
		}
	}

	public TableView<ClientDetails> getClientTable() {
		return clientTable;
	}

	public void setClientTable(TableView<ClientDetails> clientTable) {
		this.clientTable = clientTable;
	}
////////////////////////////
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		viewModel.close();
		Platform.exit();

	}

	
}
