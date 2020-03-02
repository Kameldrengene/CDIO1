package dal;

import dto.UserDTO;

import java.io.Serializable;
import java.util.HashMap;

public class UserList implements Serializable {
    HashMap<Integer , UserDTO> users;
    public UserList(){
        users = new HashMap<>();
    }

    public HashMap<Integer, UserDTO> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, UserDTO> users) {
        this.users = users;
    }
}
