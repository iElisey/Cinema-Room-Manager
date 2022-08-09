package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int seatsOnEachRow;
    private static char[][] rowsAndSeats;
    private static int currentIncome;
    private static int totalIncome;


    public static void main(String[] args) {
        start();
        selector();
    }

    private static void selector() {
        boolean isNotEnd = true;
        while (isNotEnd) {
            System.out.println();

            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int select = scanner.nextInt();
            switch (select) {
                case 1 -> cinema();
                case 2 -> getTicketPrice();
                case 3 -> getStats();
                case 0 -> isNotEnd = false;
            }
        }
    }

    private static void getStats() {
        int purchasedTickets = 0;

        for (char[] rowsAndSeat : rowsAndSeats) {
            for (char c : rowsAndSeat) {
                if (c == 'B') {
                    purchasedTickets += 1;
                }
            }
        }
        float purchasedTicketsFloat = purchasedTickets;
        float seatsFloat = (rows * seatsOnEachRow);
        float full = purchasedTicketsFloat / seatsFloat;
        float percentage = full * 100;
        System.out.println();
        System.out.printf("Number of purchased tickets: %d" +
                        "\nPercentage: %.2f%%" +
                        "\nCurrent income: $%d" +
                        "\nTotal income: $%d\n",
                purchasedTickets,
                percentage,
                currentIncome,
                totalIncome);
    }

    private static void getTicketPrice() {
        boolean isNoEnd = true;
        while (isNoEnd) {
            System.out.println();
            System.out.println("Enter a row number: ");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seat = scanner.nextInt();

            boolean badCredentials = false;
            for (int i = 0; i < rowsAndSeats.length; i++) {
                for (int j = 0; j < rowsAndSeats[i].length; j++) {
                    if (row - 1 == i && seat - 1 == j && rowsAndSeats[i][j] == 'B') {
                        badCredentials = true;
                        System.out.println("That ticket has already been purchased!");
                    }
                }
            }
            if (row > rowsAndSeats.length || seat > rowsAndSeats[0].length) {
                badCredentials = true;
                System.out.println("Wrong input!");
            }

            if (!badCredentials) {
                int seats = rows * seatsOnEachRow;
                int priceTicket;
                if (seats <= 60) {
                    priceTicket = 10;
                } else {
                    if (rows % 2 == 0) {
                        priceTicket = row <= rows / 2 ? 10 : 8;
                    } else {
                        int frontRows = (rows - 1) / 2;
                        priceTicket = row <= frontRows ? 10 : 8;
                    }
                }

                System.out.println("Ticket price: $" + priceTicket);
                rowsAndSeats[row - 1][seat - 1] = 'B';
                currentIncome += priceTicket;
                isNoEnd = false;
            }
        }
    }

    private static void start() {
        System.out.println();
        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seatsOnEachRow = scanner.nextInt();

        rowsAndSeats = new char[rows][seatsOnEachRow];
        for (char[] rowsAndSeat : rowsAndSeats) {
            Arrays.fill(rowsAndSeat, 'S');
        }


        int seats = rows * seatsOnEachRow;
        if (seats <= 60) {
            totalIncome = seats * 10;
        } else {
            if (rows % 2 == 0) {
                totalIncome = (seats / 2 * 10) + (seats / 2 * 8);
            } else {
                int frontRows = (rows - 1) / 2;
                int frontIncome = (frontRows * seatsOnEachRow) * 10;
                int backRows = (rows + 1) / 2;
                int backIncome = (backRows * seatsOnEachRow) * 8;
                totalIncome = frontIncome + backIncome;
            }
        }


    }

    private static void cinema() {
        System.out.println();
        System.out.println("Cinema: ");
        for (int i = 0; i < rowsAndSeats[0].length; i++) {
            int j = i + 1;
            if (j == 1) {
                System.out.print("  " + j);
            } else {
                System.out.print(" " + j);
            }
        }
        System.out.println();
        for (int i = 0; i < rowsAndSeats.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < rowsAndSeats[i].length; j++) {
                System.out.print(" " + rowsAndSeats[i][j]);
            }
            System.out.println();
        }
    }
}