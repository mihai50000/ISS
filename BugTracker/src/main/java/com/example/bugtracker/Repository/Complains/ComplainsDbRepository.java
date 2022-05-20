package com.example.bugtracker.Repository.Complains;

import com.example.bugtracker.Model.Complain;
import com.example.bugtracker.Model.Severity;
import com.example.bugtracker.Repository.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ComplainsDbRepository implements ComplainsRepository {
    private static final Logger LOGGER = LogManager.getLogger(ComplainsDbRepository.class);

    private final JdbcUtils dbUtils;

    public ComplainsDbRepository(Properties props) {
        LOGGER.info("Initializing ComplainsDbRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void save(Complain complain) {
        LOGGER.traceEntry(complain.toString());
        Connection connection = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                INSERT INTO complains (name, creation_date, severity, description, linked_bug_id)\040
                VALUES ((?), (?), (?), (?), (?))
                """)) {
            preparedStatement.setString(1, complain.getName());
            preparedStatement.setString(2, complain.getDateTime().toString());
            preparedStatement.setString(3, complain.getSeverity().toString());
            preparedStatement.setString(4, complain.getDescription());

            if (complain.getLinkedBug() != null) {
                preparedStatement.setInt(5, complain.getLinkedBug().getId());
            } else {
                preparedStatement.setNull(5, Types.NULL);
            }

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.traceExit();
    }

    @Override
    public void linkComplain(int complainId, int bugId) {
        LOGGER.traceEntry(String.valueOf(complainId), bugId);
        Connection connection = dbUtils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("""
        UPDATE complains SET linked_bug_id=(?) WHERE complain_id=(?)
""")) {
            preparedStatement.setInt(1, bugId);
            preparedStatement.setInt(2, complainId);

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.traceExit();
    }

    @Override
    public List<Complain> getAllComplains() {
        LOGGER.traceEntry();
        Connection connection = dbUtils.getConnection();

        List<Complain> complains = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM complains")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int complainId = resultSet.getInt("complain_id");
                    String name = resultSet.getString("name");
                    LocalDateTime localDateTime = LocalDateTime.parse(resultSet.getString("creation_date"));
                    Severity severity = Severity.valueOf(resultSet.getString("severity"));
                    String description = resultSet.getString("description");
//                    int linked_bug_id = resultSet.getInt("linked_bug_id"); ///TODO GET BUG
                    complains.add(new Complain(complainId, name, localDateTime, severity, description, null));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.traceExit();
        return complains;
    }
}
