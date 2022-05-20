package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link LoanCardDtoMapper} builds the entity 'LoanCardDto'.
 *
 *
 * @author Alexander Pishchala
 */

public class LoanCardDtoMapper implements RowMapper<LoanCardDto> {

    @Override
    public LoanCardDto map(ResultSet resultSet) throws SQLException {
        LoanCardDto loanCard = new LoanCardDto();
        loanCard.setLoanCardDtoId(resultSet.getLong(ColumnName.LOAN_CARD_ID_CARD));
        loanCard.setUserId(resultSet.getLong(ColumnName.USER_ID_USERS));
        loanCard.setBookId(resultSet.getLong(ColumnName.BOOK_ID_BOOK));
        loanCard.setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        loanCard.setIsbn(resultSet.getString(ColumnName.BOOK_ISBN));
        loanCard.setCityLibrary(resultSet.getString(ColumnName.LIBRARY_CITY));
        loanCard.setTakingBook(resultSet.getDate(ColumnName.LOAN_CARD_TAKING_BOOK).toLocalDate());
        loanCard.setReturnBook(resultSet.getString(ColumnName.LOAN_CARD_RETURN_BOOK));
        loanCard.setDeadline(resultSet.getDate(ColumnName.LOAN_DEADLINE).toLocalDate());
        loanCard.setStatus(LoanCardStatus.valueOf(resultSet.getString(ColumnName.CARD_STATUS_STATUS).toUpperCase()));
        loanCard.setTypeUse(BookTypeUse.valueOf(resultSet.getString(ColumnName.LOAN_CARD_TYPE_USE).toUpperCase()));
        return loanCard;
    }
}
