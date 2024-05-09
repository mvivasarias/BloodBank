package bloodBankIfaces;

import java.util.List;

import bloodBankPOJOs.Stock;

public interface StockManager {
	public List<Stock> getListOfStock();
	// we could get the stock regarding the type of blood needed

}
