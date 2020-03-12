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

    @Test
    public void verifyPassword() {

        UserDTO testUser = new UserDTO();
        testUser.setUserId(11);
        testUser.setUserName("Volkan");

        int count = 0;

        try {
            func.verifyPassword(testUser, "Aa1");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Length error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "AAAAAAAAAAbbbbbbbbbbCCCCCCCCCCddddddddddEEEEEEEEEE!");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Length error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "Aa123aA¤");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Symbol error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "Aa123aA@");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Symbol error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "Aa123aA&");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Symbol error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "Volkan123");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Information in password error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "Aa11!23");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Information in password error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "AAAAAAAAAAAA");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Category error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "AAAAAAbbbbb");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Category error")) {
                count++;
            }
        }

        try {
            func.verifyPassword(testUser, "AAAAAA!!!!!");
            System.out.println("Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Category error")) {
                count++;
            }
        }

        try {
            if (func.verifyPassword(testUser, "AAAbbb123")){
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            if (func.verifyPassword(testUser, "AAAbbb123!.")){
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            if (func.verifyPassword(testUser, "!+-.=?_aA")){
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }


        assertEquals(13, count);
    }
}