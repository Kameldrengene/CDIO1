package dal;

import dto.UserDTO;

import java.io.Serializable;
import java.util.HashMap;

public class UserList implements Serializable {
    private static final long serialVersionUID = -7884684746401586332L;
    public HashMap<Integer , UserDTO> users;
    public UserList(){
        users = new HashMap<>();
    }

    public HashMap<Integer, UserDTO> getUsers() {
        return users;
    }

}
