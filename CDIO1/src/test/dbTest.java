package test;

import dal.IUserDAO;
import dal.DBUserDAO;
import dto.UserDTO;

public class dbTest {
    public static void main(String[] args) {
        //Laver en userDTO indsætter i database og priter
        UserDTO user = new UserDTO();
        user.setUserId(5);
        user.setUserName("John");
        user.setIni("JOH");
        user.addRole("Operator");
        user.addRole("Admin");
        DBUserDAO user2 = new DBUserDAO();
        UserDTO userDTO = new UserDTO();
        try {
            user2.createUser(user);
            userDTO = user2.getUser(5);

        } catch (IUserDAO.DALException e) {
            System.out.println("No user with that ID");
            e.printStackTrace();
        }
        System.out.println(userDTO.getIni());
        user.setIni("KAM");
        try {
            user2.updateUser(user);
            userDTO = user2.getUser(5);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }

        System.out.println(userDTO.getIni());
    }
}
