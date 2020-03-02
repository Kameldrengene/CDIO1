package test;

import dal.IUserDAO;
import dal.DBUserDAO;
import dto.UserDTO;

public class dbTest {
    public static void main(String[] args) {
        //Laver en fil der hedder 5.txt med info herunder og læser den igen og printer initialerne
        UserDTO user = new UserDTO();
        user.setUserId(5);
        user.setUserName("Johnny");
        user.setIni("JOH");
        user.addRole("Operator");
        user.addRole("Admin");
        DBUserDAO user2 = new DBUserDAO();
        UserDTO userDTO = new UserDTO();
        try {
            user2.createUser(user);
            userDTO = user2.getUser(5);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        System.out.println(userDTO.getIni());
    }
}
