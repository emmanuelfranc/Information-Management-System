public class FilmProject extends Project {
    private String format;

    public FilmProject(int iD, String nm, String typ, int d, int m, int y, String lcn, double cst, double custPrice, String venSize, int dur, String durUnits, String fmt) {
        super(iD, nm, typ, d, m, y, lcn, cst, custPrice, venSize, dur, durUnits);
        format = fmt;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String getProject() {
        return getId() + ". " + getName() + " (" + getType() + ") will be held on " + getMonth() + "/" + getDay() + "/" + getYear() + " in " +
                getLocation() + ", it will cost " + getCost() + " and priced to customers at " + getCustomerPrice() + ". Venue is " + getVenueSize() +
                " and the duration is " + getDuration() + getDurationUnits() + ". The format is " + getFormat() + "\n";
    }
}
