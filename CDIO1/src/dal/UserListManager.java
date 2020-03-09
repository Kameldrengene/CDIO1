package dal;

import dto.UserDTO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserListManager implements IUserDAO{
    UserList userList;

    public UserListManager(){
        userList=readUserList();
        if (userList==null){
            userList = new UserList();
            writeUserList(userList);
        }

    }


    public UserList readUserList(){
        UserList temp = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("dataobject.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (UserList) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (Exception e){
            System.out.println("Filen findes ikke");
        }
        return temp;
    }

    public void writeUserList(UserList savethisList){
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
        return userList.getUsers().get(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return new ArrayList(userList.getUsers().values());

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (userList.getUsers().containsKey(user.getUserId())){
            throw new DALException("\n" + "Bruger navn er optaget");
        }
        else {
            userList.getUsers().put(user.getUserId(), user);
            writeUserList(userList);
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (userList.getUsers().containsKey(user.getUserId())) {
            userList.getUsers().replace(user.getUserId(), user);
            writeUserList(userList);
        }
        else
            throw new DALException("\n" + "Brugeren eksistet ikke");
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (userList.getUsers().containsKey(userId)) {
            userList.getUsers().remove(userId);
            writeUserList(userList);
        }
        else
            throw new DALException("\n" + "Brugeren eksisterer ikke");

    }
}