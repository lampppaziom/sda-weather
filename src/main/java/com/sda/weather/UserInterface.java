package com.sda.weather;

import com.sda.weather.location.LocationController;

import java.util.Scanner;

public class UserInterface {

    private final LocationController locationController;

    public UserInterface(LocationController locationController) { this.locationController = locationController; }

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
                    getAllLocations();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Number not valid");
            }
        }
    }

    private void getAllLocations() {

    }

    private void displayLocations() {
    }

    private void addLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nazwa miasta: ");
        String city = scanner.nextLine();
        System.out.println("Dlugosc geograficzna: ");
        double longitude = scanner.nextDouble();
        System.out.println("Szerokosc geograficzna: ");
        double latitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Region: ");
        String region = scanner.nextLine();
        System.out.println("Nazwa kraju: ");
        String country = scanner.nextLine();

        String requestBody = String.format("{\"city\":\"%s\",\"longitude\":\"%f\"," +
                "\"latitude\":\"%f\",\"region\":\"%s\",\"country\":\"%s\"}", city, longitude, latitude, region, country);
        System.out.println("Wysylany http request: " + requestBody);
        String responseBody = locationController.addLocation(requestBody);
        System.out.println("Odebrany http response: " + responseBody);
    }
}
