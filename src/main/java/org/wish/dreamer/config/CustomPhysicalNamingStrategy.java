package org.wish.dreamer.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return convertToSnakeCase(logicalName);
    }

    private Identifier convertToSnakeCase(final Identifier identifier) {
        if (identifier != null) {
            final String regex = "([a-z])([A-Z])";
            final String replacement = "$1_$2";
            final String orgName = identifier.getText()
                    .replaceAll(regex, replacement)
                    .toLowerCase();
            return Identifier.toIdentifier(orgName);
        } else {
            return null;
        }
    }
}
