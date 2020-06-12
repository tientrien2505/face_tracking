package vn.com.nms.gms.samples.vision.face.facetracker;

public class ChamCong {
    private String date;
    private String timeCheckin;
    private String timeCheckout;

    public ChamCong() {
    }

    public ChamCong(String date, String timeCheckin, String timeCheckout) {
        this.date = date;
        this.timeCheckin = timeCheckin;
        this.timeCheckout = timeCheckout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(String timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public String getTimeCheckout() {
        return timeCheckout;
    }

    public void setTimeCheckout(String timeCheckout) {
        this.timeCheckout = timeCheckout;
    }
}
