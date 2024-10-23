package pack.com.example.demo;
import java.time.LocalDate;

public class Acidenst {
    //объявляем наши переменные
    private LocalDate Data;
    private String ModelsAuto;
    private String Cause;
    private String Report;
    private String Adress;
    private String SumDamage;
    private String Conditions;
    private String Category;
    private String TypeAccident;
    private String NameOfUsersOfAccident;
    //объявили переменные

    //Далее делаем конструктор класса
    public Acidenst(LocalDate Data,String ModelsAuto,String Cause,String Report,
                    String Adress,String SumDamage,String Conditions,String Category,
                    String TypeAccident, String NameOfUsersOfAccident) {
        this.Data = Data;
        this.ModelsAuto = ModelsAuto;
        this.Cause = Cause;
        this.Report = Report;
        this.Adress = Adress;
        this.SumDamage = SumDamage;
        this.Conditions = Conditions;
        this.Category = Category;
        this.TypeAccident = TypeAccident;
        this.NameOfUsersOfAccident = NameOfUsersOfAccident;
    }
    //сделали конструктор

    //геттеры
    public LocalDate getData() {
        return Data;
    }

    public String getModelsAuto() {
        return ModelsAuto;
    }

    public String getCause() {
        return Cause;
    }

    public String getReport() {
        return Report;
    }

    public String getAdress() {
        return Adress;
    }

    public String getSumDamage() {
        return SumDamage;
    }

    public String getConditions() {
        return Conditions;
    }

    public String getCategory() {
        return Category;
    }

    public String getTypeAccident() {
        return TypeAccident;
    }
    public String getNameOfUsersOfAccident() {
        return NameOfUsersOfAccident;
    }
}
