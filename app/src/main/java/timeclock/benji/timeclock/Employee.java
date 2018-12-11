package timeclock.benji.timeclock;

import java.io.Serializable;

public class Employee implements Serializable {
    private String fname;
    private String lname;
    private String clockintime;
    private String clockoutime;
    private String jobNumber;
   // private String latIn;
   // private String longIn;
   // private String latOut;
   // private String longOut;
    private String inAddress;
    private String outAddress;
    private String currentDate;
    private int hoursWorked;
   // private int outseconds;
   // private int inseconds;

    public Employee (String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setInAddress(String inAddress) {
        this.inAddress = inAddress;
    }

    public String getInAddress() {
        return inAddress;
    }

    public void setOutAddress(String outAddress) {
        this.outAddress = outAddress;
    }

    public String getOutAddress() {
        return outAddress;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }




}
