package lesson_5;

public class lesson_5 {
    public static void main(String[] args) {
        new lesson_5().start();
    }
  private void start(){
        worker[] cabinet = new worker[5];
        cabinet[0] = new worker();
        cabinet[1] = new worker("John","nobody","text@gmail.com","666666",10000f,42);
        cabinet[2] = new worker("Anot","CO","gogo@mail.ru","666667",999999f,35);
        cabinet[3] = new worker("Gendalf","LORD","nomail","666668",20000f,99);
        cabinet[4] = new worker("Boris","worker","test@gmail.com","666669",10000f,11);

        for (int i=0; i<cabinet.length;i++){
            if (cabinet[i].getAge()>40){
                cabinet[i].showInfo();
            }
        }
    }

    class worker{
        public worker() {
            this.fullname = "BasicName";
            this.profession = "stager";
            this.email = "no@email";
            this.phone = "02";
            this.salary = 0.0f;
            this.age = 0;
        }
        public worker(String fullname, String profession, String email, String phone, Float salary, int age) {
            this.fullname = fullname;
            this.profession = profession;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
            this.age = age;
        }
        private String fullname;
        private String profession;
        private  String email;
        private String phone;
        private Float salary;
        private int age;

        public int getAge() {
            return age;
        }

        void showInfo() {
            System.out.println("worker{" +
                    "fullname='" + fullname + '\'' +
                    ", profession='" + profession + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}');
        }
    }
}
