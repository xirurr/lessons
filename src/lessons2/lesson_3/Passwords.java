package lessons2.lesson_3;

import java.util.Scanner;

public class Passwords {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String pwd = in.next();
        System.out.println(checkPwd(pwd));
    }

    static boolean checkPwd(String pwd) {
        //   return pwd.matches("^.*[A-Z]+.*$")&&pwd.matches("^.*[a-z]+.*$")&&pwd.matches("^.*?[0-9]+.*")&&pwd.length()>=8;
        return pwd.matches("^(?=.*[a-z]+.*)(?=.*[A-Z]+.*)(?=.*[0-9])+.*$") && pwd.length() >= 8;
    }
}
