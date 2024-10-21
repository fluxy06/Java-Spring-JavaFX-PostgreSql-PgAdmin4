package pack.com.example.demo;

import java.time.LocalDate;

public class InfoCarsUsers {
        //#region Объявляем переменные для того чтобы в последующем привязать их к БД
        private String carBrand;
        private String carType;
        private String personType;
        private String licensePlate;
        private LocalDate year;
        private String ownerName;
        private String address;
        private String engineVolume;
        private String color;
        private String bodyType;
        private String vin;
        //#endregion
        //#region Конструктор класса
        public InfoCarsUsers(String carBrand, String carType, String personType, String licensePlate, LocalDate year,
                             String ownerName, String address, String engineVolume, String color, String bodyType, String vin) {
            this.carBrand = carBrand; // Марка
            this.carType = carType; // Тип машины, легковая, трактор и т.п
            this.personType = personType;// Тип лица
            this.licensePlate = licensePlate;// Гос номера
            this.year = year;// Год создания тачки
            this.ownerName = ownerName; // ФИО собственника машины
            this.address = address; // Адрес его проживания
            this.engineVolume = engineVolume; // Объем двигателя машины
            this.color = color; // Цвет тачки
            this.bodyType = bodyType; // Тип кузова тачк
            this.vin = vin; // VIN номер тачки
        }
        //#endregion
        //#region Геттеры для каждого поля
        public String getCarBrand() { // Геттер для модели машины
            return carBrand;
        }

        public String getCarType() { // Тип автомобиляв
            return carType;
        }

        public String getPersonType() { // Тип лица
            return personType;
        }

        public String getLicensePlate() { // Гос номер авто
            return licensePlate;
        }

        public LocalDate getYear() { //Дата создания тачки
            return year;
        }

        public String getOwnerName() { // Имя человека на которого офрмлена машина
            return ownerName;
        }

        public String getAddress() { // Адресс где проживает этот человек
            return address;
        }

        public String getEngineVolume() { // Объем двигателя
            return engineVolume;
        }

        public String getColor() { // Цвет машины
            return color;
        }

        public String getBodyType() { // Тип кузова тачки
            return bodyType;
        }

        public String getVin() { // VIN номер машины
            return vin;
        }
        //#endregion
    }
