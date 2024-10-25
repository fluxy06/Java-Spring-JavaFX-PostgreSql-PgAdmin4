package pack.com.example.demo;

public class CarNumberStatus {
    private String freeNumber; // Поле для свободного номера
    private String busyNumber; // Поле для занятого номера

    public CarNumberStatus(String freeNumber, String busyNumber) {
        this.freeNumber = freeNumber;
        this.busyNumber = busyNumber;
    }

    public String getFreeNumber() {
        return freeNumber;
    }

    public void setFreeNumber(String freeNumber) {
        this.freeNumber = freeNumber;
    }

    public String getBusyNumber() {
        return busyNumber;
    }

    public void setBusyNumber(String busyNumber) {
        this.busyNumber = busyNumber;
    }
}