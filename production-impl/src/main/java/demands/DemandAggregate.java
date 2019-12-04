package demands;

import api.AdjustDemandDto;
import entities.DemandEntity;
import entities.ManualAdjustmentEntity;

public class DemandAggregate {
    private DemandEntity demand;
    private DemandEvents events;

    public DemandAggregate(DemandEntity demand, DemandEvents events) {
        this.demand = demand;
        this.events = events;
    }

    public void adjustDemand(AdjustDemandDto adjustment) {
        ManualAdjustmentEntity manualAdjustment = new ManualAdjustmentEntity();
        manualAdjustment.setLevel(adjustment.getLevel());
        manualAdjustment.setNote(adjustment.getNote());
        manualAdjustment.setDeliverySchema(adjustment.getDeliverySchema());

        demand.getAdjustment().add(manualAdjustment);

        DemandChanged event = new DemandChanged(
                demand.getId(),
                demand.getProductRefNo(),
                demand.getDay(),
                demand.getDeliverySchema(DefaultDeliverySchemaPolicy::defaultFor),
                demand.getLevel()
        );
        events.emit(event);
    }
}
