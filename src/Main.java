import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML; // Импорт аннотации FXML

public class Main extends Application {

    String url = "jdbc:postgresql://Nikita-MSI:5432/Тут пишем название своей БД";
    String name = "postgres";
    String pass = "123";

    //
    //
    //
    @FXML
    private ComboBox<String> comBoxFirstPage;
    @FXML
    private ComboBox<String> comBoxSecondPage;// Объявляем переменную для ComboBox для второго таба;
    @FXML
    private ComboBox<String> comBoxThirdPage; // Объявляем выпадающий список для третьей вкладки;

    //Объявляем переменные для создания запросов в БД
    @FXML
    private TextField modelOfCar; // модель автомобиля;
    @FXML
    private TextField typeOfUser; // тип лица - юрик или физик;
    @FXML
    private TextField numberOfCar; // номера машины;
    @FXML
    private TextField nameOfUser; // ФИО человека;
    @FXML
    private TextField valueOfEngine; // объем двигателя;
    @FXML
    private TextField typeOfBody; // тип кузова;
    @FXML
    private TextField numberOfFederation; // VIN номер тачки;
    @FXML
    private DatePicker dateOfCreatCar; // Дата выпуска автомобиля

    // Объявляем переменные для фиксации информации в таблицу с ДТП
    @FXML
    private TextField roadConditions; // дорожные условия
    @FXML
    private TextField causeOfCollision; // причина столкновения;
    @FXML
    private TextField sumOfDamage; // сумма ущерба;
    @FXML
    private TextArea ReportOfAcciedent; // ОТЧЕТ ОБ АВАРИИ;
    @FXML
    private TextField categoryOfAccident; // категория аварии;
    @FXML
    private TextField typesOfCars; // Автомобили, через запятую, участвующие в аварии;
    @FXML
    private TextField adressOfAccident; // Адресс аварии;
    @FXML
    private DatePicker dateOfAccident; // Дата аварии;

    //Объявляем те же переменые что для второго, но меняем название что-бы не произошло логической ошибки;
    @FXML
    private TextField roadConditions2; // дорожные условия
    @FXML
    private TextField causeOfCollision2; // причина столкновения;
    @FXML
    private TextField sumOfDamage2; // сумма ущерба;
    @FXML
    private TextArea ReportOfAcciedent2; // ОТЧЕТ ОБ АВАРИИ;
    @FXML
    private TextField categoryOfAccident2; // категория аварии;
    @FXML
    private TextField typesOfCars2; // Автомобили, через запятую, участвующие в аварии;
    @FXML
    private TextField adressOfAccident2; // Адресс аварии;
    @FXML
    private DatePicker dateOfAccident2; // Дата аварии;

    //С объявлением переменных покончено
    //
    //
    //

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyFinallInterface.fxml"));
        loader.setController(this); // Устанавливаем контроллером текущий класс (Main)
        Parent root = loader.load(); // Загружаем интерфейс

        //Добавление элементов в выподающий список На первой страничке тип Автомобиля;
        comBoxFirstPage.getItems().add("Легковой");
        comBoxFirstPage.getItems().add("Грузовой");
        comBoxFirstPage.getItems().add("Мотоцикл");
        comBoxFirstPage.getItems().add("Трактор");
        comBoxFirstPage.getItems().add("Полуприцеп");
        comBoxFirstPage.getItems().add("Автобус");
        comBoxFirstPage.getItems().add("МикроАвтобус");


        // Добавление элементов в выпадающий список для второй странички тип происшествия;
        comBoxSecondPage.getItems().add("Наезд на ограждение, стобл");
        comBoxSecondPage.getItems().add("Наезд на пешехода");
        comBoxSecondPage.getItems().add("Лобовое столкновение");
        comBoxSecondPage.getItems().add("Наезд на транспорт стоящий спереди");
        comBoxSecondPage.getItems().add("Боковое столкновение на перекрестке");


        //Добавление элементов в выпадающий список на третьей вкладке
        comBoxThirdPage.getItems().add("Наезд на ограждение, стобл");
        comBoxThirdPage.getItems().add("Наезд на пешехода");
        comBoxThirdPage.getItems().add("Лобовое столкновение");
        comBoxThirdPage.getItems().add("Наезд на транспорт стоящий спереди");
        comBoxThirdPage.getItems().add("Боковое столкновение на перекрестке");

        // Создаём сцену
        Scene scene = new Scene(root, 1280, 780);
        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Система учета автомобилей и ДТП");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}