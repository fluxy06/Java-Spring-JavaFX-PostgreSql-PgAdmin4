    //Для архитектуры проекта правильной создал по рекомендациям несколько папок и обернул все в пакет
    package pack.com.example.demo;
    //Библиотека для работы с javaFX
    import javafx.application.Application;

    import java.sql.*;
    import java.sql.Date;
    import java.time.LocalDate;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.stage.Stage;
    import javafx.fxml.FXML; // Импорт аннотации FXML
    //import sun.util.resources.LocaleData;
    // Библиотеки для работы с БД и Исключениями
    import java.io.IOException;
    import java.util.*;
    // Для записи данных в табличку БД
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import org.checkerframework.checker.units.qual.A;

    public class Main extends Application {

        //#region Запись информации для подключения
        static String url = "jdbc:postgresql://Nikita-MSI:5432/TrafficsPoliceDataBase";
        static String name = "postgres";
        static String pass = "123";
        //#endregion

        //#region Создание переменных для работы со столбцами вкладки просмотра информации о пользователе
        @FXML
        private TableView<InfoCarsUsers> MyTableFirstPage; // переменная всей таблицы в целом
        // каким образом работает: Получается, выбираем столбец, задаем значение, задаем навзание переменной
        @FXML
        private TableColumn<InfoCarsUsers, String> carBrandColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> carTypeColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> personTypeColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> licensePlateColumn;
        @FXML
        private TableColumn<InfoCarsUsers, LocalDate> yearColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> ownerNameColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> addressColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> engineVolumeColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> colorColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> bodyTypeColumn;
        @FXML
        private TableColumn<InfoCarsUsers, String> vinColumn;
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
        private TextField RoadConditions; // дорожные условия
        @FXML
        private TextField Cause; // причина столкновения
        @FXML
        private TextField SumDamage; // сумма ущерба
        @FXML
        private TextArea Report; // ОТЧЕТ ОБ АВАРИИ
        @FXML
        private TextField Category; // категория аварии
        @FXML
        private TextField ModelsAuto; // Автомобили, через запятую, участвующие в аварии
        @FXML
        private TextField AdressAccident; // Адрес аварии
        @FXML
        private DatePicker DataAccident; // Дата аварии
        //#endregion

        //#region Объявляем те же переменные, что для второго, но меняем название, это для 3 вкладки, с просмотром ДТП
        @FXML
        private TextField RoadConditions2; // дорожные условия
        @FXML
        private TextField Cause2; // причина столкновения
        @FXML
        private TextField SumDamage2; // сумма ущерба
        @FXML
        private TextArea Report2; // ОТЧЕТ ОБ АВАРИИ
        @FXML
        private TextField Category2; // категория аварии
        @FXML
        private TextField ModelsAuto2; // Автомобили, через запятую, участвующие в аварии
        @FXML
        private TextField AdressAccident2; // Адрес аварии
        @FXML
        private DatePicker DataAccident2; // Дата аварии
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MyFinallInterface.fxml"));
                loader.setController(this);
                Parent root = loader.load();
                System.out.println("Интерфейс загружен");

                // Создаем сцену и показываем окно
                Scene scene = new Scene(root, 1280, 780);
                primaryStage.setTitle("Система учета автомобилей и ДТП");
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();

            } catch (IOException e) {
                System.err.println("Ошибка при загрузке FXML: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Произошла ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
        //#endregion
        //Тут начинаются функции программы тоесть по типу нажали на кнопочку и что то произошло
        //#region Функция Добавление информации в БД с проверкой для таблицы InformationOfUsers
        @FXML
        private void CreateReport() {
            // Создаем объект нашего класса проверки строк
            StrinMethods meth = new StrinMethods();

            // Записываем в переменные данные, введенные пользователем
            String name = nameOfUser.getText();
            String model = modelOfCar.getText();
            String numbersCars = numberOfCar.getText();
            String typeUser = typeOfUser.getText();
            String valueEngine = valueOfEngine.getText();
            String typeBody = typeOfBody.getText();
            String vinNumber = numberOfFederation.getText();
            LocalDate dateCreated = dateOfCreatCar.getValue();
            String carColor = colorOfCar.getText();
            String userAdres = adressOfUser.getText();
            String typeAuto = getTypeAuto();

            //#region Проверки на корректность заполнения полей
            // Получаем текущую дату для сравнения, чтобы не произошла ошибка случайно.
            LocalDate currentDate = LocalDate.now();

            // Проверяем на пустые поля
            if (meth.isNullOrEmpty(name) || meth.isNullOrEmpty(model) || meth.isNullOrEmpty(numbersCars) ||
                    meth.isNullOrEmpty(typeUser) || meth.isNullOrEmpty(valueEngine) || meth.isNullOrEmpty(typeBody) ||
                    meth.isNullOrEmpty(vinNumber) || dateCreated == null || meth.isNullOrEmpty(carColor) ||
                    meth.isNullOrEmpty(userAdres) || meth.isNullOrEmpty(typeAuto)) {
                System.out.println("Одно или несколько полей пустые.");
                return; // Останавливаем выполнение функции в случае, если одно из полей пустое
            }

            // Проверяем корректность даты
            if (dateCreated.isAfter(currentDate)) {
                System.out.println("Выбранная дата является некорректной.");
                return;
            }
            //#endregion

            // Создаем объект InfoCarsUsers для дальнейшей работы
            InfoCarsUsers carUser = new InfoCarsUsers(model, typeAuto, typeUser, numbersCars, dateCreated, name, userAdres,
                    valueEngine, carColor, typeBody, vinNumber);

            // Переходим к записи данных в базу данных
            String sqlInsert = "INSERT INTO \"InformationOfUsers\" (\"Model\", \"TypeOfCar\", \"TypeOfBody\", \"TypeOfUser\", \"FederationNumber\", \"VinNumber\", \"DateCreate\", \"NameOfUser\", \"Adress\", \"ValueEngine\", \"Color\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "ON CONFLICT (\"VinNumber\", \"FederationNumber\") DO NOTHING";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

                pstmt.setString(1, model);
                pstmt.setString(2, typeAuto);
                pstmt.setString(3, typeBody);
                pstmt.setString(4, typeUser);
                pstmt.setString(5, numbersCars); // FederationNumber
                pstmt.setString(6, vinNumber); // VinNumber
                pstmt.setDate(7, java.sql.Date.valueOf(dateCreated));
                pstmt.setString(8, name);
                pstmt.setString(9, userAdres);
                pstmt.setString(10, valueEngine);
                pstmt.setString(11, carColor);

                pstmt.executeUpdate();
                System.out.println("Данные успешно добавлены или уже существуют.");
            } catch (SQLException e) {
                System.out.println("Ошибка при добавлении данных в базу данных: " + e.getMessage());
                e.printStackTrace();
            }
        }
        //#endregion

        //#region Функция добавления информации в таблицу TrafficAccidents
        @FXML
        private void CreateRoadAccident() {
            // Создаем объект нашего класса проверки строк
            StrinMethods meth = new StrinMethods();
            //#region Записываем в переменные данные, введенные пользователем
            LocalDate data = DataAccident.getValue();
            String models = ModelsAuto.getText();
            String sumDamage = SumDamage.getText();
            String cause = Cause.getText();
            String roadConditions = RoadConditions.getText();
            String typAccident = getTypeAccident();
            String category = Category.getText();
            String adressAccident = AdressAccident.getText();
            String report = Report.getText();
            //#endregion записываем значения
            LocalDate currentDate = LocalDate.now();

            //#region Проверяем на пустые поля
            if (meth.isNullOrEmpty(models) || meth.isNullOrEmpty(typAccident) ||
                    meth.isNullOrEmpty(cause) ||
                    meth.isNullOrEmpty(category) || data == null || meth.isNullOrEmpty(adressAccident) ||
                    meth.isNullOrEmpty(report)){
                System.out.println("Одно или несколько полей пустые.");
                return; // Останавливаем выполнение функции в случае, если одно из полей пустое
            }

            // Проверяем корректность даты
            if (data.isAfter(currentDate)) {
                System.out.println("Выбранная дата является некорректной.");
                return;
            }
            //#endregion

            Acidenst accident = new Acidenst(data, models, sumDamage, cause, roadConditions, typAccident,
                    category, adressAccident,report);

            String sqlInsert = "INSERT INTO \"TrafficAccidents\" (\"DataAccident\", \"TypeAccident\"," +
                    "\"AdressAccident\", \"ModelsAuto\"," +
                    "\"SumDamage\", \"Cause\", \"Category\", \"RoadConditions\", \"Report\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +  // 9 полей
                    "ON CONFLICT (\"DataAccident\", \"TypeAccident\") DO NOTHING";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

                pstmt.setDate(1, java.sql.Date.valueOf(data));      // DataAccident
                pstmt.setString(2, typAccident);                   // TypeAccident
                pstmt.setString(3, adressAccident);                // AdressAccident
                pstmt.setString(4, models);                        // ModelsAuto
                pstmt.setString(5, sumDamage);                     // SumDamage
                pstmt.setString(6, cause);                         // Cause
                pstmt.setString(7, category);                      // Category
                pstmt.setString(8, roadConditions);                // RoadConditions
                pstmt.setString(9, report);                        // Report

                pstmt.executeUpdate();
                System.out.println("Данные успешно добавлены или уже существуют.");
            } catch (SQLException e) {
                System.out.println("Ошибка при добавлении данных в базу данных: " + e.getMessage());
                e.printStackTrace();
            }


        }
        //#endregion

        //#region Функция для метода добавления информации в БД
        public ObservableList<InfoCarsUsers> executeQueryAndGetData(String sql) {
            ObservableList<InfoCarsUsers> data = FXCollections.observableArrayList();
            try (Connection conn = DriverManager.getConnection(url, name, pass);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String carBrand = rs.getString("Model");
                    String carType = rs.getString("TypeOfCar");
                    String personType = rs.getString("TypeOfUser");
                    String licensePlate = rs.getString("FederationNumber");
                    LocalDate year = rs.getDate("DateCreated").toLocalDate();
                    String ownerName = rs.getString("NameOfUser");
                    String address = rs.getString("Adress");
                    String engineVolume = rs.getString("ValueEngine");
                    String color = rs.getString("Color");
                    String bodyType = rs.getString("TypeOfBody");
                    String vin = rs.getString("VinNumber");

                    InfoCarsUsers userInfo = new InfoCarsUsers(carBrand, carType, personType, licensePlate, year, ownerName, address, engineVolume, color, bodyType, vin);
                    data.add(userInfo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Обработка ошибок подключения и запроса
            }

            return data;
        }
        //#endregion

        //#region Создание функции вывода информации в таблицу по заданной выборке
        @FXML
        public void LooksAtUsers() {
            // Записываем в переменные данные, введенные пользователем
            StrinMethods meth = new StrinMethods();
            String name = nameOfUser.getText();
            String model = modelOfCar.getText();
            String numbersCars = numberOfCar.getText();
            String typeUser = typeOfUser.getText();
            String valEng = valueOfEngine.getText();
            String typeBody = typeOfBody.getText();
            String vinNumber = numberOfFederation.getText();
            LocalDate dateCreated = dateOfCreatCar.getValue();
            String carColor = colorOfCar.getText();
            String userAdres = adressOfUser.getText();
            String typeAuto = getTypeAuto();

            // Создаем список с названиями столбцов из БД
            List<String> allColumnNames = new ArrayList<>();
            meth.addColumnNames(allColumnNames); // Добавили все названия столбцов

            // Создаем список со всеми введенными значениями
            List<String> allInputValues = new ArrayList<>();
            meth.addValueIntoList(name, model, numbersCars, typeUser, valEng, typeBody, vinNumber, carColor, userAdres, typeAuto, allInputValues);

            // Создаем мапу для хранения непустых значений
            Map<String, String> listNoNullValues = new HashMap<>();
            meth.addNewValuesNoneNullIntoList(allInputValues, listNoNullValues, allColumnNames); // Прогоняем список и добавляем непустые значения в мапу

            System.out.println(listNoNullValues); // Для отладки: выводим список непустых значений

            // Генерируем SQL-запрос
            String sql = meth.generateSelectQuery("public.\"InformationOfUsers\"", listNoNullValues, dateCreated);
            System.out.println(sql); // Для отладки: выводим запрос на экран

            // Выполняем запрос и получаем данные
            ObservableList<InfoCarsUsers> data = executeQueryAndGetData(sql); // Передаем SQL-запрос в метод

            // Устанавливаем полученные данные в таблицу
            MyTableFirstPage.setItems(data);
        }
        //#endregion

        // Тут они заканчиваются
        //#region Создаем слушатель событий для первой страницы с выпдающим списком
        private String typeAuto;


        // Метод для получения текущего значения типа авто
        private String getTypeAuto() {
            return typeAuto;
        }

        //#endregion

        //#region Создаем слушатель для второй страницы с выпадающим списком
        private String typeAccident;

        private String getTypeAccident() {
            return typeAccident;
        }
        //#endregion

        //#region Инициализация приложения
        @FXML
        public void initialize() {
            // Инициализация ComboBox
            comBoxFirstPage.getItems().addAll("Легковой", "Грузовой", "Мотоцикл", "Трактор", "Полуприцеп", "Автобус", "МикроАвтобус");

            comBoxSecondPage.getItems().addAll("Наезд на ограждение, столб", "Наезд на пешехода", "Лобовое столкновение", "Наезд на транспорт стоящий спереди", "Боковое столкновение на перекрестке");

            comBoxThirdPage.getItems().addAll("Наезд на ограждение, столб", "Наезд на пешехода", "Лобовое столкновение", "Наезд на транспорт стоящий спереди", "Боковое столкновение на перекрестке");

            comBoxFirstPage.valueProperty().addListener((observable, oldValue, newValue) -> {  // тут лямба функция, я не понимаю как точно она работает поэтому запомнить нужно конструкцию
                typeAuto = newValue; // Сохраняем выбранное значение в поле
            });
            comBoxSecondPage.valueProperty().addListener((observable, oldValue, newValue) -> {  // тут лямба функция, я не понимаю как точно она работает поэтому запомнить нужно конструкцию
                typeAccident = newValue; // Сохраняем выбранное значение в поле
            });
            initializeTableColumns();

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

        //#region функция инициализации столбцов
        public void initializeTableColumns() {
            carBrandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
            carTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carType"));
            personTypeColumn.setCellValueFactory(new PropertyValueFactory<>("personType"));
            licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            ownerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            engineVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("engineVolume"));
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
            bodyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bodyType"));
            vinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        }
        //#endregion

    }

    class StrinMethods {
        private boolean isEmpty; // переменная для метода
        //#region Метод проверки строки на пустую или же значение null
        public boolean isNullOrEmpty(String str) {
            return str == null || str.trim().isEmpty(); // Проверяет, является ли строка null или пустой
        }
        //#endregion

        //#region Метод добавления моих переменных для первой странички в массив
        public void addValueIntoList(String name, String model, String numbersCars, String typeUser, String valueEngine,
                                     String typeBody, String vinNumber, String carColor, String userAdres, String typeAuto,
                                     List<String> allInputValues) {
            allInputValues.add(name);         // NameOfUser
            allInputValues.add(model);        // Model
            allInputValues.add(numbersCars);  // FederationNumber
            allInputValues.add(typeUser);     // TypeOfUser
            allInputValues.add(valueEngine);  // ValueEngine
            allInputValues.add(typeBody);     // TypeOfBody
            allInputValues.add(vinNumber);    // VinNumber
            allInputValues.add(carColor);     // Color
            allInputValues.add(userAdres);    // Adress
            allInputValues.add(typeAuto);
        }
        //#endregion

        //#region Метод для того чтобы пробежаться по списку List<String> и записать в новый список List<Strin> уже не пустые
        public void addNewValuesNoneNullIntoList(List<String> allInputValues, Map<String, String> NewValues, List<String> columnNames) {
            for(int i = 0; i < allInputValues.size(); i++) {
                if(!isNullOrEmpty(allInputValues.get(i))) {
                    NewValues.put(columnNames.get(i), allInputValues.get(i));
                }
            }
        }
        //#endregion

        //#region Метод добавления названия столбцов в список
        public void addColumnNames(List<String> ColumnNames) {
            ColumnNames.add("NameOfUser");
            ColumnNames.add("Model");
            ColumnNames.add("FederationNumber");
            ColumnNames.add("TypeOfUser");
            ColumnNames.add("ValueEngine");
            ColumnNames.add("TypeOfBody");
            ColumnNames.add("VinNumber");
            ColumnNames.add("Color");
            ColumnNames.add("Adress");
            ColumnNames.add("TypeOfCar");
        }
        //#endregion

        //#region Метод генерации запроса на основе заполненных полей
        public String generateSelectQuery(String tableName, Map<String, String> data, LocalDate dateCreated) {
            // Строим часть для столбцов и значений
            StringBuilder whereClause = new StringBuilder();
            boolean first = true;

            // Добавляем параметры из data
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!first) {
                    whereClause.append(" AND ");
                }
                whereClause.append("\"").append(entry.getKey()).append("\" = '").append(entry.getValue()).append("'");
                first = false;
            }

            // Проверяем дату и добавляем её в запрос, если она не пустая
            if (dateCreated != null) {
                if (whereClause.length() > 0) {
                    whereClause.append(" AND "); // Добавляем AND, если уже есть условия
                }
                whereClause.append("\"DateCreated\" = '").append(dateCreated.toString()).append("'"); // Преобразуем дату в строку
            }

            // Возвращаем финальный SQL-запрос
            return "SELECT * FROM " + tableName + " WHERE " + whereClause.toString() + ";";
        }
        //#endregion
    }