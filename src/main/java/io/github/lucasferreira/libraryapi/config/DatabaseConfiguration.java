package io.github.lucasferreira.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driver-class-name}")
    String driver;

// @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    //https://github.com/brettwooldridge/HikariCP
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        //tamanho max de pool de conexão
        //quando uma aplicação é grande e está ficando lenta
        //e tem muitos usuarios ao mesmo tempo utilizando
        //aumente o maximumPoolSize
        config.setMaximumPoolSize(10); //maximo de conexões liberadas

        //minimo que vai ser liberado no caso, uma conexão
        config.setMinimumIdle(1);

        //nome do pool que aparece no LOG
        config.setPoolName("library-db-pool");

        // tamanho maximo/tempo maximo de conexão
        config.setMaxLifetime(600000); //600 mil ms -> 10 minutos o default é 1 mi e 800 mil 30 minutos

        //tempo que vai gastar para obter uma conexão,
        // caso passe desse tempo ele vai dar timeOut, ele não vai tentar mais
        config.setConnectionTimeout(100000); //100 mil ms -> 1 minuto

        //teste rápido para verificar se a conexão está conectando com o banco
        config.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(config);
    }
}
