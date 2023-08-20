public class TvProject extends Project {
    private String network;

    public TvProject(int iD, String nm, String typ, int d, int m, int y, String lcn, double cst, double custPrice, String venSize, int dur, String durUnits, String nwrk) {
        super(iD, nm, typ, d, m, y, lcn, cst, custPrice, venSize, dur, durUnits);
        network = nwrk;
    }

    public String getNetwork() {
        return network;
    }

    @Override
    public String getProject() {
        return getId() + ". " + getName() + " (" + getType() + ") will be held on " + getMonth() + "/" + getDay() + "/" + getYear() + " in " +
                getLocation() + ", it will cost " + getCost() + " and priced to customers at " + getCustomerPrice() + ". Venue is " + getVenueSize() +
                " and the duration is " + getDuration() + getDurationUnits() + ". The network is " + getNetwork() + "\n";

    }
}
