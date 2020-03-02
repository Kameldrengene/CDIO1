package dal;

import Services.DatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUserDAO implements IUserDAO {
    DatabaseIO db = new DatabaseIO("root", "root", "localhost");

    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.Connect();
        db.Query("use CDIO1");
        ResultSet rs = db.Query("SELECT * FROM userdto where userID="+userId);
        UserDTO user = new UserDTO();
        try {
            rs.next();
            user.setUserId(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.addRole(rs.getString("roles"));
            rs.close();
        } catch (SQLException e) {
            db.CloseConnection();
            e.printStackTrace();
        }

        db.CloseConnection();
        return user;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        db.Connect();
        db.Query("use userdto");
        ResultSet rs = db.Query("SELECT * FROM userdto");
        db.CloseConnection();
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
        db.Connect();
        db.Query("use cdio1");
        db.Update("delete from userdto where userID="+user.getUserId());
        db.Update("insert into userdto (userID, userName, ini, cpr, password, roles) VALUE ('" + user.getUserId() + "','" + user.getUserName() + "','" + user.getIni() + "','123456-7890','hej123','" + user.getRoles().get(0) + "')");
        db.CloseConnection();
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
