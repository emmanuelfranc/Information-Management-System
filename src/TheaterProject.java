public class TheaterProject extends Project {
    private String playwright;

    public TheaterProject(int iD, String nm, String typ, int d, int m, int y, String lcn, double cst, double custPrice, String venSize, int dur, String durUnits, String plwr) {
        super(iD, nm, typ, d, m, y, lcn, cst, custPrice, venSize, dur, durUnits);
        playwright = plwr;
    }

    public String getPlaywright() {
        return playwright;
    }

    @Override
    public String getProject() {
        return getId() + ". " + getName() + " (" + getType() + ") will be held on " + getMonth() + "/" + getDay() + "/" + getYear() + " in " +
                getLocation() + ", it costs " + getCost() + " and priced to customers at " + getCustomerPrice() + ". Venue is " + getVenueSize() +
                " and the duration is " + getDuration() + getDurationUnits() + ". The  Playwright is " + getPlaywright() + "\n";
    }
}
