package dal;

import dto.UserDTO;

import java.io.Serializable;
import java.util.HashMap;

public class UserList implements Serializable {
    public HashMap<Integer , UserDTO> users;
    public UserList(){
        users = new HashMap<>();
    }

    public HashMap<Integer, UserDTO> getUsers() {
        return users;
    }
}
