package repositories.entities;

import domain.User;
import repositories.db.PostgresRepository;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IEntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserRepository implements IEntityRepository<User> {
    private IDBRepository dbrepo;

    public UserRepository() {
        dbrepo = new PostgresRepository();
    }

    @Override
    public void add(User entity) {
        try {
            String sql = "INSERT INTO users(name, surname, username, password, birthday) " +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = dbrepo.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setDate(5, entity.getBirthday());
            preparedStatement.executeQuery();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {
        try{
            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement preparedStatement = dbrepo.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Iterable<User> query(String sql) {
        try {
            Statement stmt = dbrepo.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            LinkedList<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("birthday")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

