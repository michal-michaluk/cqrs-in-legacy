package demands;

import enums.DeliverySchema;

import java.time.LocalDate;

public class DemandChanged {
    private final long id;
    private final String productRefNo;
    private final LocalDate day;
    private final DeliverySchema deliverySchema;
    private final long level;

    public DemandChanged(long id, String productRefNo, LocalDate day, DeliverySchema deliverySchema, long level) {
        this.id = id;
        this.productRefNo = productRefNo;
        this.day = day;
        this.deliverySchema = deliverySchema;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public String getProductRefNo() {
        return productRefNo;
    }

    public LocalDate getDay() {
        return day;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }

    public long getLevel() {
        return level;
    }
}
