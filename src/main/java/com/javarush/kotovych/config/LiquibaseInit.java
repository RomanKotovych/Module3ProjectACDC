package com.javarush.kotovych.config;

import com.javarush.kotovych.constants.Constants;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class LiquibaseInit {
    private final ApplicationProperties properties;

    @SneakyThrows
    public void init() {
        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changelogFile", "db/changelog.xml");
            update.addArgumentValue("url", properties.getProperty(ApplicationProperties.CONNECTION_URL));
            update.addArgumentValue(Constants.USERNAME, properties.getProperty(ApplicationProperties.CONNECTION_USERNAME));
            update.addArgumentValue(Constants.PASSWORD, properties.getProperty(ApplicationProperties.CONNECTION_PASSWORD));
            update.execute();
        });

    }
}
