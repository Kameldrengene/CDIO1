package data;

import dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class MapNonPersistent {
    Map<Integer , UserDTO> users;
    public MapNonPersistent(){
        users = new HashMap<>();
        UserDTO volkan = new UserDTO();
        UserDTO mikkel = new UserDTO();
        UserDTO talha = new UserDTO();
        volkan.setUserId(11);
        volkan.addRole("Worker");
        volkan.setUserName("Volkan");
        volkan.setIni("vol");
        mikkel.setUserId(12);
        mikkel.addRole("Admin");
        mikkel.setUserName("Mikkel");
        mikkel.setIni("mik");
        talha.setUserId(13);
        talha.addRole("Admin");
        talha.setUserName("Talha");
        talha.setIni("tal");
        users.put(volkan.getUserId(),volkan);
        users.put(mikkel.getUserId(),mikkel);
        users.put(talha.getUserId(),talha);
    }

    public Map< Integer , UserDTO> getUsers() {
        return users;
    }

}