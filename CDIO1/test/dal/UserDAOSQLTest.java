package dal;

import dal.IUserDAO;
import dal.UserDAOSQL;
import dto.UserDTO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOSQLTest {

    @Test
    public void getUser() {
        UserDAOSQL DAO = new UserDAOSQL();
        UserDTO user = new UserDTO();
        user.setCpr("1122334455");
        user.setIni("MRM");
        user.setUserId(1);
        user.setUserName("Mark");
        try {
            DAO.createUser(user);
            assertEquals(user.getUserName(),DAO.getUser(user.getUserId()).getUserName());
            DAO.deleteUser(1);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getData() {
        UserDAOSQL DAO = new UserDAOSQL();
        try {
            List<UserDTO> users = DAO.getData();
            for(UserDTO a:users){
                assertEquals(a.getUserName(),DAO.getUser(a.getUserId()).getUserName());
            }
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createUser() {
        getUser();
    }

    @Test
    public void updateUser() {
        UserDAOSQL DAO = new UserDAOSQL();
        UserDTO user = new UserDTO();
        user.setCpr("1122334455");
        user.setIni("MRM");
        user.setUserId(1);
        user.setUserName("Mark");
        try {
            DAO.createUser(user);
            user.setUserName("Jens");
            assertEquals("Mark",DAO.getUser(user.getUserId()).getUserName());
            DAO.updateUser(user);
            assertEquals("Jens",DAO.getUser(user.getUserId()).getUserName());
            DAO.deleteUser(1);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser() {
        getUser();
    }
}