package dal;

import Services.DatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUserDAO implements IUserDAO {
    //DatabaseIO db = new DatabaseIO("root", "root", "localhost");
    DatabaseIO db = new DatabaseIO("C:/Users/blued/Documents/Github repos/CDIO1_2/CDIO1/test.db");
    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.connect();
        //db.query("use CDIO1");
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
            db.closeConnection();
            e.printStackTrace();
        }

        db.closeConnection();
        return user;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        db.connect();
        //db.query("use userdto");
        ResultSet rs = db.query("SELECT * FROM userdto");
        db.closeConnection();
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
        //db.query("use cdio1");
        db.update("delete from userdto where userID="+user.getUserId());
        db.insert(user.getUserId(),user.getUserName(),user.getIni(),"2138-231","1234",user.getRoles().get(0));
        db.closeConnection();
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
