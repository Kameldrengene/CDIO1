package controller;

import dal.IUserDAO;
import dto.UserDTO;
import functionality.IFunctionality;
import tui.TUI;

import java.util.List;

public class UserLogic {
    
    private TUI tui;
    private IFunctionality functionality;
    private IUserDAO dao;
    
    public UserLogic(TUI tui, IFunctionality functionality, IUserDAO dao){
        this.tui = tui;
        this.functionality = functionality;
        this.dao = dao;
    }
    
    public void start(){
        
        int choice;
        
        outer:
        while(true){
            
            choice = tui.showMenu("Vælg et menupunkt", "Opret ny bruger", "List brugere", "Ret bruger","Slet bruger", "Afslut program");
    
            switch (choice){
                case 1:
                    createUser();
                    break;
                case 2:
                    ListUsers();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.out.println("\n" + "Programmet lukkes...");
                    java.lang.System.exit(0); //Will exit the program with error code 0
            }
        }
    }
    
    private void createUser(){

        try {
            int lastId = dao.getData().get(dao.getData().size()-1).getUserId();
            dao.createUser(tui.createUser(lastId));
        }catch (IUserDAO.DALException e){
            System.out.println("\n" + e.getMessage());
        }
    }
    
    private void ListUsers(){
        try {
            tui.listUsers(dao.getData());
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());
        }
    }
    
    private void editUser(){
    
        int id = tui.getUserID();
        
        try{
            validateID(id);
            UserDTO userDTO = dao.getUser(id);
            
            int choice = tui.showMenu("Vælg hvad du vil redigere", "Navn", "Brugernavn", "Kodeord", "Roller");
            
            switch (choice){
                case 1:
                    String newName = tui.inputName();
                    userDTO.setUserName(newName);
                    break;
                case 2:
                    String newIni = tui.inputInit();
                    userDTO.setIni(newIni);
                    break;
                case 3:
                    try{
                        String newPassword = tui.inputString("Skriv nyt kodeord: ");
                        functionality.verifyPassword(userDTO, newPassword);
                        userDTO.setPassword(newPassword);
                        
                    }catch(Exception e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case 4:
                    tui.addRolesToUser(userDTO);
                    break;
            }
            
        } catch(userIDNotFound e){
            System.out.println("\n" + e.getMessage());
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    }
    
    private void deleteUser(){
       int id = tui.getUserID();
       
       try{
           validateID(id);
           dao.deleteUser(id);
       } catch (userIDNotFound e){
           System.out.println("\n" + e.getMessage());
       }catch (IUserDAO.DALException e){
           System.out.println("\n" + e.getMessage());
       }
    }
    
    private void validateID(int ID) throws userIDNotFound{
        try {
            int[] IDs = functionality.getUserIDs(dao.getData());
            if( !(functionality.isUserIDPresent(ID, IDs)) )
                throw new userIDNotFound("ID'et blev ikke fundet");
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());;
        }
    }
    
    public class userIDNotFound extends Exception {
        public userIDNotFound(String msg) { super(msg);}
    }
    
}
