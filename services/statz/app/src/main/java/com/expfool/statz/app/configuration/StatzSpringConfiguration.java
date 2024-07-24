package com.expfool.statz.app.configuration;

import com.expfool.bookkeeper.api.BookkeeperClientSpringConfiguration;
import com.expfool.bookkeeper.api.Services.BookkeeperService;
import com.expfool.statz.app.managers.StatisticManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
    BookkeeperClientSpringConfiguration.class
)
public class StatzSpringConfiguration {

    @Bean
    public StatisticManager statisticManager(
            BookkeeperService bookkeeperService
    ) {
        return new StatisticManager(bookkeeperService);
    }
}
