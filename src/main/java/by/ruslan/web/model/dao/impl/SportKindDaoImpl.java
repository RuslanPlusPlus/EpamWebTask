package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.EventMemberColumn;
import by.ruslan.web.model.dao.SportKindColumn;
import by.ruslan.web.model.dao.SportKindDao;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SportKindDaoImpl implements SportKindDao {

    private static final String SQL_FIND_ALL_SPORT_KINDS =
            "SELECT * FROM sport_kinds";
    private static final String SQL_ADD_SPORT_KIND=
            "INSERT INTO sport_kinds (kind_name) " +
                    "VALUES (?);";
    private static final String SQL_UPDATE_SPORT_KIND =
            "UPDATE sport_kinds SET kind_name = ?" +
                    "WHERE kind_id = ?";
    private static final String SQL_FIND_SPORT_KIND_BY_ID =
            "SELECT * FROM sport_kinds WHERE kind_id = ?";

    @Override
    public List<SportKind> findAll() throws DAOException {
        List<SportKind> sportKinds = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SPORT_KINDS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                SportKind sportKind = buildSportKind(resultSet);
                sportKinds.add(sportKind);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return sportKinds;
    }

    @Override
    public boolean add(SportKind sportKind) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_SPORT_KIND)){
            String kindName = sportKind.getKindName();
            statement.setString(1, kindName);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public boolean updateSportKind(SportKind sportKind) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SPORT_KIND)){
            String kindName = sportKind.getKindName();
            statement.setString(1, kindName);
            statement.setLong(2, sportKind.getKind_id());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public Optional<SportKind> findSportKindById(long id) throws DAOException {
        Optional<SportKind> kindOptional = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_SPORT_KIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                SportKind sportKind = buildSportKind(resultSet);
                kindOptional = Optional.of(sportKind);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return kindOptional;
    }

    private SportKind buildSportKind(ResultSet resultSet) throws SQLException {
        long kindId = resultSet.getLong(SportKindColumn.KIND_ID);
        String kindName = resultSet.getString(SportKindColumn.KIND_NAME);
        SportKind sportKind = new SportKind();
        sportKind.setKind_id(kindId);
        sportKind.setKindName(kindName);
        return sportKind;
    }
}
