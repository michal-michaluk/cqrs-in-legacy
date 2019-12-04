package demands;

import dao.DemandDao;
import entities.DemandEntity;
import shortages.Demands;

import java.time.LocalDate;
import java.util.List;

public class DemandsQuery {
    private DemandDao demandDao;

    public Demands readDemands(String productRefNo, LocalDate today) {
        List<DemandEntity> entities = demandDao.findFrom(today.atStartOfDay(), productRefNo);
        return new Demands(entities);
    }
}
