package dal;

import Data.NonPersistent;
import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAONonPersistent implements IUserDAO {

    NonPersistent data1;

    public UserDAONonPersistent() {
        data1 = new NonPersistent();

    }

    @Override
    public UserDTO getUser(int userId) throws DALException {

        return data1.getUsers().get(userId);
    }

    @Override
    public List<UserDTO> getSerialisering() throws DALException {
       return new ArrayList(data1.getUsers().values());

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (data1.getUsers().containsKey(user.getUserId())){
            throw new DALException("\n" + "Bruger navn er optaget");
        }
        else
        data1.getUsers().put(user.getUserId(),user);

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (data1.getUsers().containsKey(user.getUserId()))
        data1.getUsers().replace(user.getUserId(),user);
        else
            throw new DALException("\n" + "Brugeren eksistet ikke");

    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (data1.getUsers().containsKey(userId))
            data1.getUsers().remove(userId);
        else
            throw new DALException("\n" + "Brugeren eksisterer ikke");

    }
}