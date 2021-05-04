package com.dao;

import com.annotations.Singleton;
import com.api.dao.IRequestDao;
import com.model.Request;

@Singleton
public class RequestDao extends AbstractDao<Request> implements IRequestDao {

//    private static final String INSERT_QUERY = "INSERT INTO request" +
//            "(book_id, date, request_count, status) " +
//            "VALUES(?, ?, ?, ?);";
//    private static final String DELETE_QUERY = "DELETE FROM request WHERE id=?;";
//    private static final String UPDATE_QUERY = "UPDATE request SET book_id=?, date=?, request_count=?" +
//            ", status=? WHERE id=? ;";
//    private static final String GET_ALL_QUERY = "SELECT * FROM request;";
//    private static final String GET_COUNT_OF_OBJECTS_QUERY = "SELECT COUNT(*) FROM request;";
//    private static final String TABLE_NAME = "REQUEST";

    public RequestDao() {
    }

    @Override
    protected Request updateEntityFields(Request entityToUpdate, Request entity) {
        entityToUpdate.setBook(entity.getBook());
        entityToUpdate.setRequestDate(entity.getRequestDate());
        entityToUpdate.setRequestCount(entity.getRequestCount());
        entityToUpdate.setRequestStatus(entity.getRequestStatus());
        return entityToUpdate;
    }

    @Override
    protected String getClassName() {
        return "Request";
    }

    @Override
    protected Class<Request> getClazz() {
        return Request.class;
    }


//    @Override
//    protected String getInsertQuery() {
//        return INSERT_QUERY;
//    }
//
//    @Override
//    protected String getGetAllQuery() {
//        return GET_ALL_QUERY;
//    }
//
//    @Override
//    protected String getDeleteQuery() {
//        return DELETE_QUERY;
//    }
//
//    @Override
//    protected String getUpdateQuery() {
//        return UPDATE_QUERY;
//    }
//
//    @Override
//    protected String getCountOfObjectsQuery() {
//        return GET_COUNT_OF_OBJECTS_QUERY;
//    }
//
//    @Override
//    protected void preparedStatementForCreate(PreparedStatement statement, Request entity) throws SQLException {
//        statement.setLong(1, entity.getBookId());
//        statement.setTimestamp(2, Timestamp.valueOf(entity.getRequestDate()));
//        statement.setInt(3, entity.getRequestCount());
//        statement.setString(4, String.valueOf(entity.getRequestStatus()));
//    }
//
//    @Override
//    protected void preparedStatementForUpdate(PreparedStatement statement, Request entity) throws SQLException {
//        statement.setLong(1, entity.getId());
//        statement.setTimestamp(2, Timestamp.valueOf(entity.getRequestDate()));
//        statement.setInt(3, entity.getRequestCount());
//        statement.setString(4, String.valueOf(entity.getRequestStatus()));
//        statement.setLong(5, entity.getId());
//    }
//
//    @Override
//    protected String getTableName() {
//        return TABLE_NAME;
//    }
}
