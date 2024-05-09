package bloodBankJDBC;

import java.util.List;

import bloodBankIfaces.StockManager;
import bloodBankPOJOs.Stock;

public class JDBCStockManager implements StockManager {

	private JDBCManager manager;

	public JDBCStockManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Stock> getListOfStock() {
		// TODO Auto-generated method stub
		return null;
	}

}
