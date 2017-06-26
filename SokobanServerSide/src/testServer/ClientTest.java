package testServer;

import java.io.IOException;
import java.net.UnknownHostException;

import model.MyModel;
import model.data.Level2D;
import solver.SokobanSolver;

public class ClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		SokobanSolver solver=new SokobanSolver();
		Level2D level=solver.readLevel("./level1.txt");
		MyModel model=new MyModel();
		model.setLevel(level);
		model.solveFromServer("solution", 500);
		

	}

}
