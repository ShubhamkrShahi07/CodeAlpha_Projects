import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true; // Initially, rooms are available
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void cancelBooking() {
        isAvailable = true;
    }
}

class Hotel {
    private ArrayList<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
        // Add rooms to the hotel
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 200));
        rooms.add(new Room(202, "Deluxe", 200));
        rooms.add(new Room(301, "Suite", 500));
        rooms.add(new Room(302, "Suite", 500));
    }

    public ArrayList<Room> searchAvailableRooms(String category) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

class Reservation {
    private String customerName;
    private Room reservedRoom;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(String customerName, Room reservedRoom, String checkInDate, String checkOutDate) {
        this.customerName = customerName;
        this.reservedRoom = reservedRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservedRoom.bookRoom(); // Mark room as booked
    }

    public void showReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Number: " + reservedRoom.getRoomNumber());
        System.out.println("Room Category: " + reservedRoom.getCategory());
        System.out.println("Price: $" + reservedRoom.getPrice());
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
    }

    public Room getReservedRoom() {
        return reservedRoom;
    }
}

class Payment {
    public static boolean processPayment(double amount) {
        // Simulate payment processing (In a real system, integrate with payment gateway)
        System.out.println("Processing payment of $" + amount);
        return true; // Assume payment is successful for simplicity
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Reservation reservation = null;

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1: {
                    System.out.print("Enter room category (Standard, Deluxe, Suite): ");
                    String category = scanner.nextLine();
                    ArrayList<Room> availableRooms = hotel.searchAvailableRooms(category);

                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available in this category.");
                    } else {
                        System.out.println("Available Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    Room room = hotel.getRoomByNumber(roomNumber);

                    if (room == null) {
                        System.out.println("Room not found.");
                    } else if (!room.isAvailable()) {
                        System.out.println("Room is already booked.");
                    } else {
                        System.out.print("Enter your name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter check-in date (YYYY-MM-DD): ");
                        String checkInDate = scanner.nextLine();
                        System.out.print("Enter check-out date (YYYY-MM-DD): ");
                        String checkOutDate = scanner.nextLine();

                        reservation = new Reservation(customerName, room, checkInDate, checkOutDate);
                        System.out.println("Reservation made successfully.");
                        break;
                    }
                    break;
                }
                case 3: {
                    if (reservation != null) {
                        reservation.showReservationDetails();
                        System.out.print("Proceed to payment (Y/N)? ");
                        char proceed = scanner.nextLine().charAt(0);
                        if (proceed == 'Y' || proceed == 'y') {
                            double amount = reservation.getReservedRoom().getPrice();
                            if (Payment.processPayment(amount)) {
                                System.out.println("Payment successful. Reservation complete!");
                            } else {
                                System.out.println("Payment failed. Try again.");
                            }
                        }
                    } else {
                        System.out.println("No reservation found.");
                    }
                    break;
                }
                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
