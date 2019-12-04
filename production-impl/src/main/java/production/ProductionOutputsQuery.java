package production;

import dao.ProductionDao;
import entities.ProductionEntity;
import shortages.ProductionOutputs;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductionOutputsQuery {
    private ProductionDao productionDao;

    public ProductionOutputs readOutputs(String productRefNo, LocalDate today) {
        Map<LocalDate, Long> outputs = productionDao.findFromTime(productRefNo, today.atStartOfDay()).stream()
                .collect(Collectors.groupingBy(
                        production -> production.getStart().toLocalDate(),
                        Collectors.summingLong(ProductionEntity::getOutput)
                ));
        return new ProductionOutputs(productRefNo, outputs);
    }
}
