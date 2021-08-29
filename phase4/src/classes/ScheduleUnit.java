package classes;

public class ScheduleUnit {
    private int id;
    public String day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;
    private boolean isOccupied;

    ScheduleUnit(int id, String day, int startHour, int startMinute, int finishHour, int finishMinute){
        this.id = id;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.finishHour = finishHour;
        this.finishMinute = finishMinute;
        this.isOccupied = false;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getId() {
        return id;
    }

    public String getStartTime() {
        String startH = (startHour > 9) ? Integer.toString(startHour) : "0" + Integer.toString(startHour);
        String startM = (startMinute > 9) ? Integer.toString(startMinute) : "0" + Integer.toString(startMinute);
        String startTime =  startH + ":" + startM;
        return startTime;

    }

    public String getFinishTime() {
        String finishH = (finishHour > 9) ? Integer.toString(finishHour) : "0" + Integer.toString(finishHour);
        String finishM = (finishMinute > 9) ? Integer.toString(finishMinute) : "0" + Integer.toString(finishMinute);
        String finishTime = finishH + ":" + finishM;
        return finishTime;
    }

    public String getDay() {
        return day;
    }
}
