package com.expfool.statz.app;

import com.expfool.bookkeeper.api.BookkeeperClientSpringConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
    BookkeeperClientSpringConfiguration.class
)
public class StatzSpringConfiguration {
}
