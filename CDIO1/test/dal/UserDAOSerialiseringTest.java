package dal;

import data.MapSerialisering;
import dto.UserDTO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOSerialiseringTest {
    IUserDAO iUserDAO = new UserDAOSerialisering();

    @Test
    public void getData() {
        try {
            assertEquals(6, iUserDAO.getData().size());
        }catch (IUserDAO.DALException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createUser() {
        UserDTO newUser = new UserDTO();
        newUser.setUserId(17);
        newUser.setIni("AND");
        newUser.setUserName("Anders");
        newUser.setCpr("6758493021");
        newUser.addRole("Admin");
        try {
            iUserDAO.createUser(newUser);
            assertEquals("AND",iUserDAO.getUser(17).getIni());
        }catch (IUserDAO.DALException e){
            System.out.println(e.getMessage());
        }

    }
}