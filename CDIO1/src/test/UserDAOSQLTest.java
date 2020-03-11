package test;


import dal.IUserDAO;
import dal.UserDAOSQL;
import dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

class UserDAOSQLTest {

    @Test
    public void getUser() {
        UserDAOSQL DAO = new UserDAOSQL();
        UserDTO user = new UserDTO();
        user.setCpr("1122334455");
        user.setIni("MRM");
        user.setUserId(1);
        user.setUserName("Mark");
        try {
            assertEquals(user.getUserName(),DAO.getUser(user.getUserId()).getUserName());
            DAO.deleteUser(1);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getData() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}