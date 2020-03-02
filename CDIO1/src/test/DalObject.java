package test;

import controller.UserLogic;
import dal.IUserDAO;
import dal.UserListManager;
import dto.UserDTO;
import functionality.Functionality;
import functionality.IFunctionality;
import tui.TUI;

public class DalObject {
    public static void main(String[] args) {
        IFunctionality functionality = new Functionality();
        TUI tui = new TUI();
        IUserDAO iDAO = new UserListManager();
        UserDTO newUser = new UserDTO();
        UserLogic userLogic = new UserLogic(tui, functionality, iDAO);


        userLogic.start();
    }
}
