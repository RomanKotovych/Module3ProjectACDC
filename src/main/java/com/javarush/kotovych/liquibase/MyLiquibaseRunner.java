package com.javarush.kotovych.liquibase;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MyLiquibaseRunner {
    public void runLiquibase() throws Exception {
        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");
            update.addArgumentValue("url", "jdbc:postgresql://localhost:5432/game");
            update.addArgumentValue("username", "postgres");
            update.addArgumentValue("password", "postgres");
            update.execute();
        });
    }
}
