package dal;

import services.SQLDatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOSQL implements IUserDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID=" + userId);

        UserDTO user = new UserDTO();
        List<String> roles = new ArrayList<>();

        try {
            rs.next();
            user.setUserId(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));


            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs_roles = db.query("SELECT * FROM userdto_roles where userID = " + userId + ";");
        try {
            while (rs_roles.next()) {
                roles.add(rs_roles.getString("role_name"));
            }
            rs_roles.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setRoles(roles);

        db.close();
        return user;
    }

    @Override
    public List<UserDTO> getData() throws DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto");


        List<UserDTO> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setIni(rs.getString("ini"));
                List<String> roles = new ArrayList<>();
                ResultSet rs_roles = db.query("SELECT * FROM userdto_roles where userID = " + user.getUserId() + ";");
                try {
                    while (rs_roles.next()) {
                        roles.add(rs_roles.getString("role_name"));
                    }
                    rs_roles.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                user.setRoles(roles);
                userList.add(user);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return userList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        db.connect();
        db.update("insert into userdto (userID, userName, ini, cpr, password) VALUE ('" + user.getUserId() + "','" + user.getUserName() + "','" + user.getIni() + "','" + user.getCpr() + "','" + user.getPassword() + "')");
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
