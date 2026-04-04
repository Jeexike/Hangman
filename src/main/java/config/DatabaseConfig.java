package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
	private static final HikariDataSource dataSource;

	static {
		try {
			Properties props = new Properties();
			try (InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties")) {

				if (is == null) {
					throw new RuntimeException("application.properties not found in classpath");
				}
				props.load(is);
			}

			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(props.getProperty("url"));
			config.setUsername(props.getProperty("username"));
			config.setPassword(props.getProperty("password"));
			config.setDriverClassName(props.getProperty("driver"));

			config.setMaximumPoolSize(10);
			config.setMinimumIdle(2);
			config.setConnectionTimeout(30000);

			dataSource = new HikariDataSource(config);

		} catch (IOException e) {
			throw new RuntimeException("Failed to load database configuration", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void shutdown() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}
}
