package relatorio.java.relatorio.Report;



public class ReportData {
    private String name;
    private String email;
    private int age;
    private String job;
    

    public ReportData(String name, String email, int age, String job) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.job = job;

    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getJob() { return job; }
}
