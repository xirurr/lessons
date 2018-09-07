package lesson_2;

import java.util.Arrays;

public class HomeWork {
    public static void main(String[] args) {
        HomeWork hm2 = new HomeWork();
      hm2.massiveZeroToOne();
       hm2.fillMassive();
       hm2.reduceEachInMassive();
       hm2.fillDoubleDimensionMassive();
       hm2.findMaxMin();
        int[] var = {10, 5, 3, 2, 11, 4, 5, 0, 4, 8, 9, 10};
        System.out.println(hm2.checkSideValues(var));
        int[] var1 = {0,1,2,3,4,5,6,7,8,9};
        hm2.changeElementsPositions(var1,12);
     hm2.spiralFill2();
    }

    void massiveZeroToOne (){
        int[] a = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(a));
       for (int ab=0;ab<a.length;ab++){
           if (a[ab]==0){
               a[ab]=1;
           }
           else
           {
               a[ab]=0;
           }
       }
        System.out.println(Arrays.toString(a));
    }
    void fillMassive(){
        System.out.println("задание 2");

        int [] a1 = new int[8];
        int var =0;
        for (int i=0; i<a1.length; i++){
            a1[i] = var;
            var+=3;
        }
        System.out.println(Arrays.toString(a1));
    }
    void reduceEachInMassive(){
        System.out.println("задание 3");

        int[] a = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i=0;i<a.length;i++){
            if (a[i]<6)
                a[i]*=2;
        }
        System.out.println(Arrays.toString(a));
    }
    void fillDoubleDimensionMassive(){
        System.out.println("задание 4");

        int a[][] = new int[10][10];
        for (int i=0;i<a.length;i++){
            a[i][i]=1;
        }

        for (int i=0;i<a.length;i++){
            for (int i1=0;i1<a.length;i1++){
                System.out.print(a[i][i1]);
            }
            System.out.println("");
        }
    }
    void findMaxMin(){
        System.out.println("задание 5");

        int[] a = {8, 5, 3, 2, 11, 4, 5, 0, 4, 8, 9, 10};
        int varMin = a[0];
        int varMax = a[0];
        for (int i=0;i<a.length;i++){
            if (varMax <= a[i]) {
                varMax = a[i];
            }
            if (varMin >a[i]) {
                varMin = a[i];
            }
        }
        System.out.println("max "+varMax);
        System.out.println("min "+varMin);
    }
    boolean checkSideValues(int b[]){
        System.out.println("задание 6");

        if (b[0]==b[b.length-1]){
            return true;
        }
        return false;
    }
    void changeElementsPositions(int b[],int a){
        System.out.println("задание 7");
        if (a>b.length){
            a=a-b.length;
        }
        int var;
        int varl =b.length-a;
        for (int i=0;i<a;i++){
            var=b[varl];
            for (int z=b.length-a; z>0; z--){
                b[z+i]=b[z+i-1];
            }
            b[i]=var;
            varl++;
        }
        System.out.println(Arrays.toString(b));
    }
    void spiralFill2(){
        int[][] a = new int[5][5];
        int change=(a.length*a[1].length)-1;
        int x=0;
        int y=0;
        int xmax = a.length;
        int ymax = a[1].length;
        a[x][y]=1;
        while (change>0) {
            if (y+1<ymax && a[x][y+1]!=1){
                if(x-1>=0 && a[x-1][y]!=1){
                    a[x-1][y]=(a[x-1][y])+1;
                    change--;
                    x--;
              //      testPrintMassive(a);
                }
                else {
                    a[x][y + 1] = (a[x][y + 1]) + 1;
                    change--;
                    y++;
              //      testPrintMassive(a);
                }
            }
            else if(x+1<xmax &&a[x+1][y]!=1 ){
                a[x+1][y]=(a[x+1][y])+1;
                change--;
                x++;
            //    testPrintMassive(a);
            }
            else if(y-1>=0 && a[x][y-1]!=1){
                a[x][y-1]=(a[x][y-1])+1;
                change--;
                y--;
           //     testPrintMassive(a);
            }
            else if(x-1>=0 && a[x-1][y]!=1){
                a[x-1][y]=(a[x-1][y])+1;
                change--;
                x--;
           //     testPrintMassive(a);
            }
        }
        testPrintMassive(a);
        }


    void testPrintMassive(int a[][]){
        for (int i=0;i<a.length;i++){
            System.out.println(Arrays.toString(new String[]{Arrays.toString(a[i])}));
        }
        System.out.println("");
    }
    }




