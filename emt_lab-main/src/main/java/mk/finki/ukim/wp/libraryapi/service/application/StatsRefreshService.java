package mk.finki.ukim.wp.libraryapi.service.application;

import mk.finki.ukim.wp.libraryapi.repository.LibraryCategoryStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StatsRefreshService {

    private static final Logger logger = LoggerFactory.getLogger(StatsRefreshService.class);
    private final LibraryCategoryStatsRepository repository;

    public StatsRefreshService(LibraryCategoryStatsRepository repository) {
        this.repository = repository;
    }


    @Scheduled(fixedRateString = "${library.stats.refresh-rate:300000}")
    public void refreshMaterializedView() {
        logger.info("Starting scheduled refresh of Library Category Stats materialized view...");

        try {
            repository.refreshStats();
            logger.info("Successfully finished refreshing the materialized view.");
        } catch (Exception e) {
            logger.error("Error occurred while refreshing materialized view: {}", e.getMessage());
        }
    }
}