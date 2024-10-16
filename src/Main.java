import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML; // Импорт аннотации FXML

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    //#region Запись информации для подключения
    static String url = "jdbc:postgresql://Nikita-MSI:5432/TrafficsPoliceDataBase";
    static String name = "postgres";
    static String pass = "123";
    //#endregion
    //#region Объявление выпадающих списков для добавления в них информации
    @FXML
    private ComboBox<String> comBoxFirstPage;
    @FXML
    private ComboBox<String> comBoxSecondPage; // Для второго таба
    @FXML
    private ComboBox<String> comBoxThirdPage; // Для третьей вкладки
    //#endregion

    //#region Объявляем переменные для создания запросов в БД для странички с просмотром информации о владельцах автомобилей
    @FXML
    private TextField modelOfCar; // модель автомобиля
    @FXML
    private TextField typeOfUser; // тип лица - юрик или физик
    @FXML
    private TextField numberOfCar; // номера машины
    @FXML
    private TextField nameOfUser; // ФИО человека
    @FXML
    private TextField valueOfEngine; // объем двигателя
    @FXML
    private TextField typeOfBody; // тип кузова
    @FXML
    private TextField numberOfFederation; // VIN номер автомобиля
    @FXML
    private DatePicker dateOfCreatCar; // Дата выпуска автомобиля
    @FXML
    private TextField colorOfCar; // Цвет автомобиля
    @FXML
    private TextField adressOfUser; // Адрес пользователя
    //#endregion

    //#region Объявляем переменные для фиксации информации в таблицу с ДТП
    @FXML
    private TextField roadConditions; // дорожные условия
    @FXML
    private TextField causeOfCollision; // причина столкновения
    @FXML
    private TextField sumOfDamage; // сумма ущерба
    @FXML
    private TextArea reportOfAccident; // ОТЧЕТ ОБ АВАРИИ
    @FXML
    private TextField categoryOfAccident; // категория аварии
    @FXML
    private TextField typesOfCars; // Автомобили, через запятую, участвующие в аварии
    @FXML
    private TextField adressOfAccident; // Адрес аварии
    @FXML
    private DatePicker dateOfAccident; // Дата аварии
    //#endregion

    //#region Объявляем те же переменные, что для второго, но меняем название, это для 3 вкладки, с просмотром ДТП
    @FXML
    private TextField roadConditions2; // дорожные условия
    @FXML
    private TextField causeOfCollision2; // причина столкновения
    @FXML
    private TextField sumOfDamage2; // сумма ущерба
    @FXML
    private TextArea reportOfAccident2; // ОТЧЕТ ОБ АВАРИИ
    @FXML
    private TextField categoryOfAccident2; // категория аварии
    @FXML
    private TextField typesOfCars2; // Автомобили, через запятую, участвующие в аварии
    @FXML
    private TextField adressOfAccident2; // Адрес аварии
    @FXML
    private DatePicker dateOfAccident2; // Дата аварии
    //#endregion


    //#region запуск приложения
    public static void main(String[] args) {
        launch(args);
    }
    //#endregion


    //#region Загрузка интерфейса, проверка на ошибки, инициализация, задаем размеры окна, некоторые свойства
    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Загрузка");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyFinallInterface.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            System.out.println("Интерфейс загружен");

            // Создаем сцену и показываем окно
            Scene scene = new Scene(root, 1280, 780);
            primaryStage.setTitle("Система учета автомобилей и ДТП");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            System.out.println("Сцена показана");

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке FXML: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //#endregion


    //#region Добавление в выпадающие списки варианты выбора
    @FXML
    public void initialize() {
        // Инициализация ComboBox
        comBoxFirstPage.getItems().addAll("Легковой", "Грузовой", "Мотоцикл", "Трактор", "Полуприцеп", "Автобус", "МикроАвтобус");

        comBoxSecondPage.getItems().addAll("Наезд на ограждение, столб", "Наезд на пешехода", "Лобовое столкновение", "Наезд на транспорт стоящий спереди", "Боковое столкновение на перекрестке");

        comBoxThirdPage.getItems().addAll("Наезд на ограждение, столб", "Наезд на пешехода", "Лобовое столкновение", "Наезд на транспорт стоящий спереди", "Боковое столкновение на перекрестке");
    }
    //#endregion

    //#region Подключение к БД
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, name, pass);
            System.out.println("Подключение успешно выполнено!");
        } catch (SQLException e) {
            System.out.println("Видимо произошла ошибка..");
            e.printStackTrace();
        }
        return connection;
    }
    //#endregion
}
