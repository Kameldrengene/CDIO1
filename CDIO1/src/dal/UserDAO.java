package dal;

import Services.TxtReader;
import dto.UserDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class UserDAO implements IUserDAO {
    TxtReader txtReader;
    @Override
    public UserDTO getUser(int userId) throws DALException{
       txtReader.openFile("src/Services/users/",userId +".txt");
       txtReader.readLines();
       return null;
    }
    @Override
    public List<UserDTO> getUserList() throws DALException{
        return null;
    }
    @Override
    public void createUser(UserDTO user) throws DALException{
        //Opretter en fil med User'id'et som navn
        try{
            File file = new File("src/Services/users/",user.getUserId()+"");
            if (file.createNewFile()){
                System.out.println("File created" + file.getName());
            }
        }
        catch(IOException e){ //Todo skal muligvis ændres til en DALException når vi får lavet den - Lasse
            e.printStackTrace();
        }

    }
    @Override
    public void updateUser(UserDTO user) throws DALException{

    }
    @Override
    public void deleteUser(int userId) throws DALException{

    }
}
