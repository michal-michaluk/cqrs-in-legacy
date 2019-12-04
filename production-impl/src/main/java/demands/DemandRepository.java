package demands;

import dao.DemandDao;
import entities.DemandEntity;

import java.time.LocalDate;

public class DemandRepository {

    private DemandDao demandDao;
    private DemandEvents events;

    public DemandAggregate get(String productRefNo, LocalDate atDay) {
        DemandEntity demand = demandDao.getCurrent(productRefNo, atDay);
        return new DemandAggregate(demand, events);
    }

    public void save(DemandAggregate object) {

    }
}
