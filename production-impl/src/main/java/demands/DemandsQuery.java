package demands;

import shortages.Demands;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemandsQuery {
    private DemandReadModelDao demandDao;

    public Demands readDemands(String productRefNo, LocalDate today) {
        List<DemandReadModelEntity> entities = demandDao.findFrom(today.atStartOfDay(), productRefNo);
        Map<LocalDate, Demands.Demand> demands = entities.stream()
                .collect(Collectors.toMap(
                        DemandReadModelEntity::getDay,
                        demand -> new Demands.Demand(demand.getDeliverySchema(), demand.getLevel())
                ));
        return new Demands(demands);
    }
}
