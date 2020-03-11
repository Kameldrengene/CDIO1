package test;

import controller.UserLogic;
import dal.IUserDAO;
import dal.UserDAOSerialisering;
import dto.UserDTO;
import bll.Functionality;
import bll.IFunctionality;
import pl.TUI;

public class DalObject {
    public static void main(String[] args) {
        IFunctionality functionality = new Functionality();
        TUI tui = new TUI();
        IUserDAO iDAO = new UserDAOSerialisering();
        UserDTO newUser = new UserDTO();
        UserLogic userLogic = new UserLogic(tui, functionality, iDAO);


        userLogic.start();
    }
}
