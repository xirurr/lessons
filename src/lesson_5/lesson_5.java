public class lesson_5 {
    public static void main(String[] args) {
        new lesson_5().start();
    }
  private void start(){
        worker first = new worker();
        first.showInfo();
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
