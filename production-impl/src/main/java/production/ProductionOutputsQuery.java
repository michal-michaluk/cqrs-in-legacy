package production;

import dao.ProductionDao;
import shortages.ProductionOutputs;

import java.time.LocalDate;

public class ProductionOutputsQuery {
    private ProductionDao productionDao;

    public ProductionOutputs readOutputs(String productRefNo, LocalDate today) {
        return new ProductionOutputs(productionDao.findFromTime(productRefNo, today.atStartOfDay()));
    }
}
