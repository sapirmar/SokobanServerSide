package viewModel;

import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.Binder;

import javafx.collections.ObservableList;
import model.ClientDetails;
import model.Model;
/**
 * view model in MVVM pattern
 * @author Sapir Markel and Roee Sisso
 *
 */
public class MyViewModel extends Observable implements ViewModel,Observer {
	private Model model;
	private ObservableList<ClientDetails> data;
	
	public MyViewModel(Model model) throws Exception {
		this.model = model;
		model.start();
	}
	
	@Override
	public void close() {
		try {
			model.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public ObservableList<ClientDetails> getData(ObservableList<ClientDetails> data) {
		return data;

	}

	@Override
	public void setData(ObservableList<ClientDetails> data) {
		this.data=data;

	}

	@Override
	public void update(Observable o, Object arg) {
		if(o==model){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					data.clear();//clear the list
					data.addAll(model.getClientsList());
				}
			}).start();
		}
		
	}

}
