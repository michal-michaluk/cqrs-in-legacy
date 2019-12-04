package demands;

import shortages.ShortageService;

// Component
public class DemandEventsPropagation implements DemandEvents {

    private DemandReadModelProjection projection;
    private ShortageService shortageService;

    @Override
    public void emit(DemandChanged event) {
        projection.apply(event);
        shortageService.processShortages(event);
    }
}
