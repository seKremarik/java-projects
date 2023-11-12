package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    static final Scanner sc = new Scanner(System.in);
    static final char EMPTY_SEAT = 'S';
    static final char OCCUPIED_SEAT = 'B';
    static String[][] mapOfSeats;
    static int selectedRow;
    static int selectedSeat;


    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();

        initializeMapOfSeats(rows, seats);

        showMenu(rows, seats);
    }

    static void showMenu(int rows, int seats) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    getSelectedTicket(rows, seats);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    static void showStatistics(int rows, int seats) {
        System.out.printf("%nNumber of purchased tickets: %d", getSoldTickets());
        System.out.printf("%nPercentage: %.2f%%", getPercentSoldTickets(rows, seats));
        System.out.printf("%nCurrent income: $%d", getCurrentIncome());
        System.out.printf("%nTotal income: $%d %n", getTotalIncome());
    }

    static int getSoldTickets() {
        int totalTicketsPurchased = 0;
        for (String[] row: mapOfSeats) {
            for (String seat: row) {
                if (Objects.equals(seat, "B")) totalTicketsPurchased++;
            }
        }
        return totalTicketsPurchased;
    }

    static float getPercentSoldTickets(int rows, int seats) {
        return (float) (getSoldTickets() * 100) / (rows * seats);
    }

    static int getCurrentIncome() {
        int income = 0;

        for (int i = 1; i < mapOfSeats.length; i++) {
            for (int j = 1; j < mapOfSeats[i].length; j++) {
                if (Objects.equals(mapOfSeats[i][j], "B")) {
                    income += calculateTicketPrice(mapOfSeats.length - 1, mapOfSeats[i].length - 1, i);
                }
            }
        }

        return income;
    }

    static int getTotalIncome() {
        int income = 0;

        for (int i = 1; i < mapOfSeats.length; i++) {
            for (int j = 1; j < mapOfSeats[i].length; j++) {
                income += calculateTicketPrice(mapOfSeats.length - 1, mapOfSeats[i].length - 1, i);
            }
        }

        return income;
    }

    static void showSeats() {
        System.out.println("\nCinema:");
        for (String[] rowsSeat : mapOfSeats) {
            for (String s : rowsSeat) {
                System.out.printf("%s ", s);
            }
            System.out.println();
        }
    }

    static void initializeMapOfSeats(int rows, int seats) {
        mapOfSeats = new String[rows + 1][seats + 1];

        for (int i = 0; i < mapOfSeats.length; i++) {
            for (int j = 0; j < mapOfSeats[i].length; j++) {
                if (i == 0 && j == 0) {
                    mapOfSeats[i][j] = " ";
                } else if (i == 0) {
                    mapOfSeats[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    mapOfSeats[i][j] = String.valueOf(i);
                }
            }
        }
        fillEmptySeats(mapOfSeats);
    }

    static void fillEmptySeats(String[][] mapOfSeats) {
        for (int i = 1; i < mapOfSeats.length; i++) {
            for (int j = 1; j < mapOfSeats[i].length; j++) {
                if (mapOfSeats[i][j] == null) {
                    mapOfSeats[i][j] = String.valueOf(Cinema.EMPTY_SEAT);
                }
            }
        }
    }

    static void markOccupiedSeat(String[][] mapOfSeats, int selectedRow, int selectedSeat) {
        if (selectedRow > 0 && selectedSeat > 0) {
            mapOfSeats[selectedRow][selectedSeat] = String.valueOf(Cinema.OCCUPIED_SEAT);
        }
    }

    static void getSelectedTicket(int rows,int seats) {
        do {
            System.out.println("\nEnter a row number:");
            selectedRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            selectedSeat = sc.nextInt();

            if (checkOutOfBounds(rows, seats, selectedRow, selectedSeat)) {
                System.out.println("Wrong input!");
            } else if (Objects.equals(mapOfSeats[selectedRow][selectedSeat], "B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        } while (true);
        buyTicket(rows, seats, selectedRow, selectedSeat);
    }

    static void buyTicket(int rows,int seats, int selectedRow, int selectedSeat) {
        int price = calculateTicketPrice(rows, seats, selectedRow);
        markOccupiedSeat(mapOfSeats, selectedRow, selectedSeat);
        System.out.printf("Ticket price: $%d%n", price);
    }

    static boolean checkOutOfBounds(int rows,int seats, int selectedRow, int selectedSeat) {
        return selectedRow < 1 || selectedRow > rows || selectedSeat < 1 || selectedSeat > seats;
    }

    static int calculateTicketPrice(int rows,int seats, int selectedRow) {
        int price;
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            price = 10;
        } else {
            if (selectedRow <= rows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }
}