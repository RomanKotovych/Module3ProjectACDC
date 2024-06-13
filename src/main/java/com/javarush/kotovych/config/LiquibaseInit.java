package com.javarush.kotovych.config;

import com.javarush.kotovych.constants.ConnectionConstants;
import com.javarush.kotovych.constants.Constants;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;


public class LiquibaseInit {

    public static void main(String[] args) throws Exception {
        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue(ConnectionConstants.CHANGELOG_FILE, ConnectionConstants.CHANGELOG_FILE_LOCATION);
            update.addArgumentValue(ConnectionConstants.URL, ConnectionConstants.POSTGRES_URL);
            update.addArgumentValue(Constants.USERNAME, ConnectionConstants.POSTGRES_USERNAME);
            update.addArgumentValue(Constants.PASSWORD, ConnectionConstants.POSTGRES_PASSWORD);
            update.execute();
        });

    }
}
