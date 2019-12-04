package demands;

import enums.DeliverySchema;

import java.time.LocalDate;

// Entity
public class DemandReadModelEntity {
    private LocalDate atDay;
    private String productRefNo;
    private DeliverySchema deliverySchema;
    private long level;

    public DemandReadModelEntity(LocalDate atDay, String productRefNo, DeliverySchema deliverySchema, long level) {
        this.atDay = atDay;
        this.productRefNo = productRefNo;
        this.deliverySchema = deliverySchema;
        this.level = level;
    }

    public LocalDate getDay() {
        return atDay;
    }

    public String getProductRefNo() {
        return productRefNo;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }

    public long getLevel() {
        return level;
    }
}
