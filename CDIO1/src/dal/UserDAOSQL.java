package dal;

import Services.SQLDatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOSQL implements IUserDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk",8003);

    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID="+userId);
        UserDTO user = new UserDTO();
        try {
            rs.next();
            user.setUserId(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.addRole(rs.getString("roles"));
            rs.close();
        } catch (SQLException e) {
            db.close();
            e.printStackTrace();
        }

        db.close();
        return user;
    }

    @Override
    public List<UserDTO> getData() throws DALException {
        db.connect();
        db.query("use userdto");
        ResultSet rs = db.query("SELECT * FROM userdto");
        db.close();
        List<UserDTO> userList = null;
        try {
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setIni(rs.getString("ini"));
                user.addRole(rs.getString("roles"));
                userList.add(user);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        db.connect();
        db.update("insert into userdto (userID, userName, ini, cpr, password, roles) VALUE ('" + user.getUserId() + "','" + user.getUserName() + "','" + user.getIni() + "','" + user.getCpr() + "','" + user.getPassword() + "','" + user.getRoles().get(0) + "')");
        db.close();
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM userdto where userID=" + user.getUserId());
            rs.next();
            if (rs.getInt("userID") == user.getUserId()) {
                db.update("UPDATE userdto SET userID = '" + user.getUserId() + "' WHERE (userID = '" + user.getUserId() + "');");
                db.update("UPDATE userdto SET userName = '" + user.getUserName() + "' WHERE (userID = '" + user.getUserId() + "');");
                db.update("UPDATE userdto SET ini = '" + user.getIni() + "' WHERE (userID = '" + user.getUserId() + "');");
                db.update("UPDATE userdto SET cpr = '" + user.getCpr() + "' WHERE (userID = '" + user.getUserId() + "');");
                db.update("UPDATE userdto SET password = '" + user.getPassword() + "' WHERE (userID = '" + user.getUserId() + "');");
                db.update("UPDATE userdto SET roles = '" + user.getRoles().get(0) + "' WHERE (userID = '" + user.getUserId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();

    }

    @Override
    public void deleteUser(int userId) throws DALException {
        db.connect();
        db.update("delete from userdto where userID=" + userId);
        db.close();
    }
}
