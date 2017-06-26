package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.server.MyServer;
import viewModel.MyViewModel;


public class Main extends Application{
	

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader mainWindowloader = new FXMLLoader(getClass().getResource("ServerViewWindow.fxml"));
		BorderPane root = (BorderPane) mainWindowloader.load();
		
		ServerWindowController view = (ServerWindowController) mainWindowloader.getController();
		MyServer model =new MyServer(8787);
		MyViewModel viewModel = new MyViewModel(model);	
		view.setViewModel(viewModel);
		model.addObserver(viewModel);
		viewModel.addObserver(view);
		
		Scene mainWindowScene = new Scene(root); 
		
		//mainWindowScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(mainWindowScene);
		primaryStage.show();
		
	}

}
