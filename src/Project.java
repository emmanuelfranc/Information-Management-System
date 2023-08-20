import java.util.Calendar;

public class Project {
    private int id;
    private String name;
    private String type;
    private Calendar date;
    private String location;
    private double cost;
    private double customerPrice;
    private String venueSize;
    private int duration;
    private String durationUnits;

    public Project(int iD, String nm, String typ, int d, int m, int y, String lcn, double cst, double custPrice, String venSize, int dur, String durUnits) {
        id = iD;
        name = nm;
        type = typ;
        Calendar inst = Calendar.getInstance();
        inst.set(y, m - 1, d);
        date = inst;
        location = lcn;
        cost = cst;
        customerPrice = custPrice;
        venueSize = venSize;
        duration = dur;
        durationUnits = durUnits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    public int getMonth() {
        return date.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        return date.get(Calendar.DATE);
    }

    public String getLocation() {
        return location;
    }

    public double getCost() {
        return cost;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public String getVenueSize() {
        return venueSize;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationUnits() {
        return durationUnits;
    }

    public String getProject() {
        return getId() + ". " + getName() + "(" + getType() + ") will be held on " + getMonth() + "/" + getDay() + "/" + getYear() + " in " +
                getLocation() + " it will cost " + getCost() + " and priced to customers at " + getCustomerPrice() + ". Venue is " + getVenueSize() +
                " and the duration is " + getDuration() + getDurationUnits() + "\n";
    }//getProject
}
