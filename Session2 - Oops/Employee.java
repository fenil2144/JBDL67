public class Employee {

    private String name;
    private int age;
    private int salary;
    private String dob;

    public Employee(){

    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public int getAge() {
        return age;
    }

//    public void setAge(int age) {
//
//        this.age = age;
//    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        //calculate age based on dob
//        this.age = basedOnDob
        this.dob = dob;
    }
}
