package passwordVerifier;

import dto.UserDTO;

public class PasswordVerifier {
    //                        !   +   -   .   =   ?   _
    private int[] specials = {33, 43, 45, 46, 61, 63, 95};

    public PasswordVerifier() {

    }

    public boolean verify(UserDTO user, String pass) throws Exception {
        if (pass.length() < 6 || pass.length() > 50){ // length error
            throw new Exception("length error");
        }

        boolean lowercase = false;
        boolean uppercase = false;
        boolean number = false;
        boolean symbol = false;

        for (int i = 0; i < pass.length(); i++) {
            int c = (int) pass.charAt(i);

            if (97 <= c && c <= 122){ //lowercase
                lowercase = true;
                continue;
            }

            else if (65 <= c && c <= 90){ //uppercase
                uppercase = true;
                continue;
            }

            else if (48 <= c && c <= 57){ //number
                number = true;
                continue;
            }

            boolean mathcing = false;
            for (int j = 0; j < specials.length; j++) {
                if (c == specials[j]) {
                    symbol = true;
                    mathcing = true;
                    break;
                }
            }

            if (!mathcing){ // not allowed symbol error
                throw new Exception("symbol error");
            }

            Boolean nameInPass = false;
            for (String name : user.getUserName().split(" ")) {
                if (pass.contains(name)) {
                    nameInPass = true;
                    break;
                }
            }

            if (pass.contains(Integer.toString(user.getUserId())) || nameInPass) {
                throw new Exception("information in password error");
            }
        }

        int count = 0;

        if (lowercase) {
            count++;
        }
        if (uppercase) {
            count++;
        }
        if (number) {
            count++;
        }
        if (symbol) {
            count++;
        }


        if (count >= 3) {
            return true;
        }
        else { //not enough categories included error
            throw new Exception("category error");
        }
    }

}
