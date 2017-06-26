package viewModel;

import javafx.collections.ObservableList;
import model.ClientDetails;

public interface ViewModel {
	public void close();
	//public void removeClient(int ID);
	public void setData(ObservableList<ClientDetails> data);
	ObservableList<ClientDetails> getData(ObservableList<ClientDetails> data);

}
