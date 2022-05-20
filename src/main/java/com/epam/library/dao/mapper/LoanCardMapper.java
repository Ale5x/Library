package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link LoanCardMapper} builds the entity 'LoanCard'.
 *
 *
 * @author Alexander Pishchala
 */

public class LoanCardMapper implements RowMapper<LoanCard>{

    @Override
    public LoanCard map(ResultSet resultSet) throws SQLException {
        LoanCard loanCard = new LoanCard();
        loanCard.setLoanCardId(resultSet.getLong(ColumnName.LOAN_CARD_ID_CARD));
        loanCard.setUserId(resultSet.getLong(ColumnName.USER_ID_USERS));
        loanCard.setBookId(resultSet.getLong(ColumnName.LOAN_CARD_ID_BOOK));
        loanCard.setCityLibrary(resultSet.getString(ColumnName.LIBRARY_CITY));
        loanCard.setTakingBook(resultSet.getDate(ColumnName.LOAN_CARD_TAKING_BOOK).toLocalDate());
        loanCard.setReturnBook(resultSet.getString(ColumnName.LOAN_CARD_RETURN_BOOK));
        loanCard.setDeadline(resultSet.getDate(ColumnName.LOAN_DEADLINE).toLocalDate());
        loanCard.setStatus(LoanCardStatus.valueOf(resultSet.getString(ColumnName.CARD_STATUS_STATUS).toUpperCase()));
        loanCard.setTypeUse(BookTypeUse.valueOf(resultSet.getString(ColumnName.LOAN_CARD_TYPE_USE).toUpperCase()));
        return loanCard;
    }
}
