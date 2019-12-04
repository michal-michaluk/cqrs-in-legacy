package demands;

import entities.DemandEntity;
import tools.Util;

// Component
public class DemandReadModelUpdater {
    private DemandReadModelDao dao;

    public void updateReadModel(DemandEntity entity) {
        dao.saveOrUpdate(new DemandReadModelEntity(
                entity.getDay(),
                entity.getProductRefNo(),
                Util.getDeliverySchema(entity),
                Util.getLevel(entity)
        ));
    }
}
