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
            assertEquals(3, iUserDAO.getData().size());
        }catch (IUserDAO.DALException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createUser() {
    }
}