package com.savstanis.exhibitionservice.model;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Properties;


public class ConnectionPoolSupplier {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                FileReader reader = new FileReader(
                        Paths.get(ConnectionPoolSupplier.class.getResource("/db.properties").toURI()).toString()
                );

                Properties properties = new Properties();
                properties.load(reader);

                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(properties.getProperty("db.url"));
                dataSource.setUsername(properties.getProperty("db.username"));
                dataSource.setPassword(properties.getProperty("db.password"));
                dataSource.setDriverClassName(properties.getProperty("db.driver-class-name"));

                ConnectionPoolSupplier.dataSource = dataSource;
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

}
