package bll;

import dto.UserDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionalityTest {
    
    IFunctionality func = new Functionality();
    
    @Test
    public void isUserIDPresent() {
        
        assertSame(true, func.isUserIDPresent(3, new int[]{1,2,3,4,5}));
        assertSame(false, func.isUserIDPresent(3, new int[]{1,2,4,5}));
        assertSame(false, func.isUserIDPresent(0, new int[]{1,2,4,5}));
        assertSame(false, func.isUserIDPresent(11, new int[]{1,2,4,5}));
        assertSame(true, func.isUserIDPresent(2, new int[]{10,6,4,2}));
        assertSame(false, func.isUserIDPresent(3, new int[]{}));
        assertSame(false, func.isUserIDPresent(-1, new int[]{1,2,4,5}));
        
    }
    
    @Test
    public void getUserIDs() {
    
        List<UserDTO> users = new ArrayList<UserDTO>();
        
        UserDTO U1 = new UserDTO();
        U1.setUserId(13);
        users.add(U1);
    
        System.out.println(users);
        assertArrayEquals(new int[]{13}, func.getUserIDs(users));
        
        UserDTO U2 = new UserDTO();
        U2.setUserId(100);
        users.add(U2);
    
        System.out.println(users);
        assertArrayEquals(new int[]{13, 100}, func.getUserIDs(users));
    
        UserDTO U3 = new UserDTO();
        U3.setUserId(-1);
        users.add(U3);
    
        assertArrayEquals(new int[]{13, 100, -1}, func.getUserIDs(users));
        
        UserDTO U4 = new UserDTO();
        U4.setUserId(0);
        users.add(U4);
    
        assertArrayEquals(new int[]{13, 100, -1, 0}, func.getUserIDs(users));
        
    }
}