package lessons2.lesson_2;

public class Main {

    public static void main(String[] args) {
        String test[][] = new String[4][4];
        fill(test);
        test[1][3]="i";
        try {
            System.out.println(workWithMassive(test));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    static String[][] fill(String[][] varMassive){
        for (int i=0;i<varMassive.length;i++){
            for (int i1=0;i1<varMassive[0].length;i1++){
                varMassive[i][i1]="1";
            }
        }
        return varMassive;
    }

    static int workWithMassive(String[][] varMassive)throws MyArraySizeException,MyArrayDataException{
        int summ=0;
        if (varMassive.length!=4 || varMassive[0].length!=4){
            throw new MyArraySizeException("Wrong size",String.valueOf(varMassive.length)+" "+String.valueOf(varMassive[0].length));
        }
        for (int i=0;i<varMassive.length;i++){
            for (int i1=0;i1<varMassive[0].length;i1++){
                try{
                summ+=Integer.parseInt(varMassive[i][i1]);
                }catch (Exception ex){
                    throw new MyArrayDataException("wrong data",String.valueOf(i)+" "+String.valueOf(i1));
                }
            }
        }
        return summ;
    }

}

class MyArraySizeException extends Exception{
    public MyArraySizeException(String message, String size) {
        super(message+" "+"("+size+")");
    }
}
class MyArrayDataException extends Exception{
    public MyArrayDataException(String message, String size) {
        super(message+" "+"("+size+")");
    }
}