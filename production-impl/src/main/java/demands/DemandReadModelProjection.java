package demands;

// Component
public class DemandReadModelProjection {
    private DemandReadModelDao dao;

    // Listener (DemandChanged)
    public void apply(DemandChanged event) {
        dao.saveOrUpdate(new DemandReadModelEntity(
                event.getDay(),
                event.getProductRefNo(),
                event.getDeliverySchema(),
                event.getLevel()
        ));
    }
}
