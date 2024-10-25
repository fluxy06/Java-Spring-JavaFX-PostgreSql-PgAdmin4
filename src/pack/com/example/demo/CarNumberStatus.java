package pack.com.example.demo;

public class CarNumberStatus {
    private String number; // Поле для хранения номера (свободного или занятого)

    public CarNumberStatus(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
