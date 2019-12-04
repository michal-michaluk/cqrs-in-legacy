package shortages;

import dao.ShortageDao;
import demands.DemandChanged;
import demands.DemandsQuery;
import entities.ShortageEntity;
import external.CurrentStock;
import external.JiraService;
import external.NotificationsService;
import external.StockService;
import production.ProductionOutputsQuery;
import tools.ShortageFinder;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class ShortageService {

    //Inject all
    private ShortageDao shortageDao;
    private StockService stockService;
    private ProductionOutputsQuery productionOutputsQuery;
    private DemandsQuery demandsQuery;

    private NotificationsService notificationService;
    private JiraService jiraService;
    private Clock clock;

    private int confShortagePredictionDaysAhead;
    private long confIncreaseQATaskPriorityInDays;

    // Listener (DemandChanged)
    public void processShortages(DemandChanged event) {
        String productRefNo = event.getProductRefNo();
        LocalDate today = LocalDate.now(clock);
        CurrentStock stock = stockService.getCurrentStock(productRefNo);
        List<ShortageEntity> shortages = ShortageFinder.findShortages(
                today, confShortagePredictionDaysAhead,
                stock,
                productionOutputsQuery.readOutputs(productRefNo, today),
                demandsQuery.readDemands(productRefNo, today)
        );

        List<ShortageEntity> previous = shortageDao.getForProduct(productRefNo);
        if (!shortages.isEmpty() && !shortages.equals(previous)) {
            notificationService.alertPlanner(shortages);
            if (stock.getLocked() > 0 &&
                    shortages.get(0).getAtDay()
                            .isBefore(today.plusDays(confIncreaseQATaskPriorityInDays))) {
                jiraService.increasePriorityFor(productRefNo);
            }
            shortageDao.save(shortages);
        }
        if (shortages.isEmpty() && !previous.isEmpty()) {
            shortageDao.delete(productRefNo);
        }
    }
}
