package production;

import dao.ProductionDao;
import entities.ProductionEntity;
import shortages.ProductionOutputs;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductionOutputsQuery {
    private ProductionDao productionDao;

    public ProductionOutputs readOutputs(String productRefNo, LocalDate today) {
        List<ProductionEntity> entities = productionDao.findFromTime(productRefNo, today.atStartOfDay());
        Map<LocalDate, Long> outputs = entities.stream()
                .collect(Collectors.groupingBy(
                        production -> production.getStart().toLocalDate(),
                        Collectors.summingLong(ProductionEntity::getOutput)
                ));
        return new ProductionOutputs(productRefNo, outputs);
    }
}
