package test;

import controller.UserLogic;
import dal.IUserDAO;
import dal.UserDAONonPersistent;
import dto.UserDTO;
import functionality.Functionality;
import functionality.IFunctionality;
import tui.TUI;

import java.util.List;

public class DBnonPersistent {
    public static void main(String[] args) {
        IFunctionality functionality = new Functionality();
        TUI tui = new TUI();
        IUserDAO iDAO = new UserDAONonPersistent();
        UserDTO newUser = new UserDTO();
        UserLogic userLogic = new UserLogic(tui,functionality,iDAO);


        userLogic.start();
        //TODO test new fields...
        newUser.setIni("test");
        newUser.addRole("Admin");
        newUser.setUserName("testName");
        newUser.setUserId(2);
        printUsers(iDAO);
        try {
            iDAO.createUser(newUser);
            System.out.println(iDAO.getUser(4));
            for (int i = 0; i <iDAO.getSerialisering().size() ; i++) {
                System.out.println(iDAO.getSerialisering().get(i));
            }


        }catch (IUserDAO.DALException e){
            System.out.println(e.getMessage());
        }
    }
    private static void printUsers(IUserDAO iDAO) {
        try {
            System.out.println("Printing users...");
            List<UserDTO> userList = iDAO.getSerialisering();
            for (UserDTO userDTO : userList) {
                System.out.println(userDTO);
            }

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
