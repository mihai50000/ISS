package com.example.bugtracker.Repository.Bugs;

import com.example.bugtracker.Model.Bug;
import com.example.bugtracker.Model.Complain;
import com.example.bugtracker.Repository.Complains.ComplainsDbRepository;
import com.example.bugtracker.Repository.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class BugsDbRepository implements BugsRepository {
    private static final Logger LOGGER = LogManager.getLogger(BugsDbRepository.class);

    private final JdbcUtils dbUtils;

    public BugsDbRepository(Properties props) {
        LOGGER.info("Initializing ComplainsDbRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void save(Bug bug) {
        LOGGER.traceEntry(bug.toString());
        Connection connection = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                INSERT INTO bugs (name, creation_date, severity, description, status)\040
                VALUES ((?), (?), (?), (?), (?))
                """)) {
            preparedStatement.setString(1, bug.getName());
            preparedStatement.setString(2, bug.getCreationDate().toString());
            preparedStatement.setString(3, bug.getSeverity().toString());
            preparedStatement.setString(4, bug.getDescription());
            preparedStatement.setString(5, bug.getStatus().toString());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.traceExit();
    }
}
