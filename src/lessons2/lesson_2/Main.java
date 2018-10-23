package lessons2.lesson_2;

public class Main {
    public static void main(String[] args) {
        String var[][] = new String[4][4];
        var = fill(var);
        var[0][2] = "g";
        try {
            System.out.println(workWithMassive(var));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }catch (MyArrayDataException e){
            e.printStackTrace();
        }
    }

    static String[][] fill (String[][] varMassive){
        for (int i=0; i<varMassive.length;i++){
            for (int i1=0;i1<varMassive[0].length;i1++){
                varMassive[i][i1]="1";
            }
        }
        return varMassive;
    }

    static int workWithMassive(String[][] varMassive)throws MyArraySizeException,MyArrayDataException{
        int summ=0;
        if (varMassive.length!=4 || varMassive[0].length!=4)
            throw new MyArraySizeException("Размер не равен 4", String.valueOf(varMassive.length) + "," + String.valueOf(varMassive[0].length));

        for (int i=0; i<varMassive.length;i++){
            for (int i1=0;i1<varMassive[0].length;i1++){
                try{
                summ+=Integer.parseInt(varMassive[i][i1]);
            }catch (Exception ex){
                    throw new MyArrayDataException(ex.getMessage(),i+","+i1);
                }
            }
        }
        return summ;
    }
}

class MyArraySizeException extends Exception{
    public String size;
    public MyArraySizeException(String message, String size) {
        super(message+"("+size+")");
        this.size=size;
    }
    public String getSize() {
        return size;
    }
}

class MyArrayDataException  extends Exception{
    public String coords;
    public MyArrayDataException (String message, String coords) {
        super(message+"("+coords+")");
        this.coords=coords;
    }
    public String getSize() {
        return coords;
    }
}
