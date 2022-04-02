package com.sda.weather;

import com.sda.weather.location.LocationController;

import java.util.Scanner;

public class UserInterface {

    public void run() {
        System.out.println("Aplikacja zostala uruchomiona.\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Witaj w aplikacji, co chcesz zrobic?");
            System.out.println("1. Dodac nowa lokalizacje");
            System.out.println("2. Wyswietlic aktualnie dodane lokalizacje");
            System.out.println("3. Pobrac wartosci pogodowe");
            System.out.println("0. Zamknac aplikacje");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addLocation();
                    break;
                case 2:
                    displayLocations();
                    break;
                case 3:
                    downloadWeatherValues();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Number not valid");
            }
        }
    }

    private void downloadWeatherValues() {

    }

    private void displayLocations() {
    }

    private void addLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nazwa miasta: ");
        String location = scanner.nextLine();
        System.out.println("Dlugosc geograficzna: ");
        double longitude = scanner.nextDouble();
        System.out.println("Szerokosc geograficzna: ");
        double latitude = scanner.nextDouble();
        System.out.println("Region: ");
        String region = scanner.nextLine();
        System.out.println("Nazwa kraju: ");
        String country = scanner.nextLine();

        String requestBody = String.format("{\"location\":\"%s\",\"longitude\":\"%d\"," +
                "\"latitude\":\"%d\",\"region\":\"%s\",\"country\":\"%s\"}");
        System.out.println("Wysylany http request: " + requestBody);
        String responseBody = LocationController.createLocation(requestBody);
        System.out.println("Odebrany http response: " + responseBody);
    }
}
