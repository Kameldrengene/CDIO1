package dal;

import Data.Serialisering;
import dto.UserDTO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDAOSerialisering implements IUserDAO{
    Serialisering serialisering;
    public UserDAOSerialisering(){
        serialisering = readUserList();
    }


    public Serialisering readUserList(){
        Serialisering temp = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("dataobject.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (Serialisering) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (Exception e){
            System.out.println("Filen findes ikke");
        }
        return temp;
    }

    public void writeUserList(Serialisering savethisList){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("dataobject.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(savethisList);
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println("Kunne ikke sktive");
        }
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return serialisering.getUsers().get(userId);
    }

    @Override
    public List<UserDTO> getSerialisering() throws DALException {
        return new ArrayList(serialisering.getUsers().values());

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (serialisering.getUsers().containsKey(user.getUserId())){
            throw new DALException("\n" + "Bruger navn er optaget");
        }
        else {
            serialisering.getUsers().put(user.getUserId(), user);
            writeUserList(serialisering);
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (serialisering.getUsers().containsKey(user.getUserId())) {
            serialisering.getUsers().replace(user.getUserId(), user);
            writeUserList(serialisering);
        }
        else
            throw new DALException("\n" + "Brugeren eksistet ikke");
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (serialisering.getUsers().containsKey(userId)) {
            serialisering.getUsers().remove(userId);
            writeUserList(serialisering);
        }
        else
            throw new DALException("\n" + "Brugeren eksisterer ikke");

    }
}