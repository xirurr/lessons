package lessons2.lesson_5;

public class Main {
    final int size = 10000000;

    public static void main(String[] args) {
        Main mn1 = new Main();
        mn1.counter();
        mn1.counterSim();
        try {
            mn1.counterMultySim(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void counter() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);
        checkSumm(arr);
    }

    void counterSim() {
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[size / 2];
        float[] a2 = new float[size / 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + (i+h) / 5) * Math.cos(0.2f + (i+h) / 5) * Math.cos(0.4f + (i+h) / 2));
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);

        checkSumm(arr);
    }

    void counterMultySim (int streams) throws Exception {
        if (streams%2!=0){throw new Exception("Введите четное количество потоков");}

        final int h = size / streams;
        float[] arr = new float[size];
        float[][] varMassives = new float[streams][size / streams];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < streams; i++) {
            System.arraycopy(arr, 0, varMassives[i], 0, h);
        }

        Thread[] threads = new Thread[streams];

        for (int i = 0; i < threads.length; i++) {
            int streamN = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < varMassives[streamN].length; j++) {
                        varMassives[streamN][j] = (float) (varMassives[streamN][j] * Math.sin(0.2f + (j+h*streamN) / 5) * Math.cos(0.2f + (j+h*streamN) / 5) * Math.cos(0.4f + (j+h*streamN) / 2));
                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();

        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < streams ; i++) {
            System.arraycopy(varMassives[i], 0, arr, i*h, h);
        }
        System.out.println(System.currentTimeMillis() - a);
        checkSumm(arr);
    }

    void checkSumm(float[] varM){
        float summ =0;

        for (int i = 0; i < varM.length; i++) {
            summ=summ+varM[i];
        }
        System.out.println("Сумма равна = "+summ);
    }
}
