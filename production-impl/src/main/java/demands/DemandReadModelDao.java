package demands;

import java.time.LocalDateTime;
import java.util.List;

public interface DemandReadModelDao {
    List<DemandReadModelEntity> findFrom(LocalDateTime localDateTime, String productRefNo);

    void saveOrUpdate(DemandReadModelEntity demandReadModelEntity);
}
