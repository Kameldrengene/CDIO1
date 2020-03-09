package test;

import dal.IUserDAO;
import dal.UserDAOSQL;
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
        UserDAOSQL user2 = new UserDAOSQL();
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
        try {
            user2.deleteUser(5);
            System.out.println(user2.getUser(5).getIni());
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
