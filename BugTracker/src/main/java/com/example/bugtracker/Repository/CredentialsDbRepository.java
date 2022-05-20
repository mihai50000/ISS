package com.example.bugtracker.Repository;

import com.example.bugtracker.Model.Credentials;
import com.example.bugtracker.Model.User;
import com.example.bugtracker.Model.UserType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@AllArgsConstructor
public class CredentialsDbRepository implements CredentialsRepository {
    private static final Logger LOGGER = LogManager.getLogger(CredentialsDbRepository.class);

    private final JdbcUtils dbUtils;

    public CredentialsDbRepository(Properties props) {
        LOGGER.info("Initializing CarsDBRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    @SneakyThrows
    public Credentials getCredentials(String username) {
        LOGGER.traceEntry();
        Connection connection = dbUtils.getConnection();

        try(PreparedStatement preparedStatement=connection.prepareStatement("Select * FROM credentials WHERE username=(?)")){
            preparedStatement.setString(1, username);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    int userId = resultSet.getInt("user_id");
                    User user = new User(userId, "mocked_name", UserType.PROGRAMMER);

                    return new Credentials(username, password, user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        LOGGER.traceExit();
        return null;
    }
}
