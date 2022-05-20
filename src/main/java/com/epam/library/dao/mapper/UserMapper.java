package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link UserMapper} builds the entity 'User'.
 *
 *
 * @author Alexander Pishchala
 */

public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getLong(ColumnName.USER_ID_USERS));
        user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE_ROLE).toUpperCase()));
        user.setFirstName(resultSet.getString(ColumnName.USER_FIRST_NAME));
        user.setLastName(resultSet.getString(ColumnName.USER_LAST_NAME));
        user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
                user.setDateRegistration(resultSet.getDate(ColumnName.USER_REGISTRATION).toLocalDate());
        user.setCountViolations(resultSet.getInt(ColumnName.USER_COUNT_VIOLATIONS));
        user.setStatus(UserStatus.valueOf(resultSet.getString(ColumnName.USER_STATUS_STATUS).toUpperCase()));
        return user;
    }
}
