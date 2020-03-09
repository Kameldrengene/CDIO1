package Data;

import dto.UserDTO;

import java.io.Serializable;
import java.util.HashMap;

public class Serialisering implements Serializable {
    public HashMap<Integer , UserDTO> users;
    public Serialisering(){
        users = new HashMap<>();
    }

    public HashMap<Integer, UserDTO> getUsers() {
        return users;
    }
}
