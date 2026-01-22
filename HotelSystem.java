// Hotel Reservation System
import java.util.*; 
import java.util.ArrayList;
import java.util.Scanner;

enum RoomCategory{
    STANDRED,DELUXE,SUITE
}
//ROOM classs keep it private for security through Encapsulation
class Room{
    private int roomNumber;
    private String roomCategory;
    private double price;
    private boolean isbooked;

    public Room(int roomNumber,String roomCategory,double price,boolean isbooked){
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.price = price;
        this.isbooked = isbooked;
    }
    //public void display(){
       // System.out.println("RoomNumber==>"+roomNumber+"Category==>"+roomCategory+"price==>"+price+"Room is booked==>"+isbooked);
   // }
//CALL GETTER--SETTER METHODS
    public int RoomNumber(){
        return roomNumber;
    }
    public String RoomCategory(){
        return roomCategory;
    }
    public double price(){
        return price;
    }
    public boolean isBooked(){
    return isbooked;
    }
    public void setBooked(boolean booked){
        isbooked = booked;
// remaining of override
    }
}

// RESERVATION class
class Reservation{
    private int reservationId;
    private String guestName;
    private Room room;

    public Reservation(int reservationId,String guestName,Room room){
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
    }
// call GETTER--SETTER methods
    public int ReservationId(){
        return reservationId;
    }
    public String GuestName(){
        return guestName;
    }
    public Room Room(){
        return room;
    }
//Display Details
    public void displayDetails(){
        System.out.println("============BOOKING CONFIRMATION==========");
        System.out.println("Reservation ID:"+reservationId);
        System.out.println("Guest Name:"+guestName);
        System.out.println("Room Number:"+room.RoomNumber()+"Room Category:"+room.RoomCategory());
        System.out.println("============================================");
    }
}
// Main logic of CODE
class HotelSystem{
    private ArrayList<Room> room = new ArrayList<>();
    private ArrayList<Reservation> reservation = new ArrayList<>();

    public void addRoom (Room newroom){
        room.add(newroom);
    }
//Search booked room by Category
    public void searchRoom(String Category){
        System.out.println("Searching for:"+Category+"rooms");
    }
//Book Room with PAYMENT Simulation
    public void bookRoom(String guestName,int roomNumber){
        for(Room room : room){
            if(room.RoomNumber() == roomNumber &&  room.isBooked()){
                //PAYMENT simulation
                System.out.println("PROCCING PAYMENT of $" +room.price()+"for"+guestName+"----");
                System.out.println(" =================PAYMENT SUCESSFULL===================");
                room.setBooked(true);
                Reservation res = new Reservation (101,guestName,room);
                reservation.add(res);
                res.displayDetails();
                return;
            }
        }
        System.out.println("Room not Available");
    }
// Cancel Reservation
        public void cancelBooking(String resId){
            Reservation toRemove = null;
            for(Reservation res : reservation){
                if(String.valueOf(res.ReservationId()).equals(resId)){    //convert ID to String
                    res.Room().setBooked(false);
                    toRemove = res;
                    break;
                }
            }
            if(toRemove != null){
                reservation.remove(toRemove);
                System.out.println("Booking"+resId+"Canceled Successfully");
            }else{
                System.out.println("Reservation not Found");
            }
        }
    
    public static void main(String []args){
        HotelSystem hotel = new HotelSystem();

        //1.search
        hotel.searchRoom("DELUXE");
        hotel.searchRoom("STANDRED");

        //2.Book
        hotel.bookRoom("Payal",201);
        hotel.bookRoom("vedant",202);

        //3.Cancel
        hotel.cancelBooking("RESXXXXXX");
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        //pre add some rooms
        hotel.addRoom(new Room(101,"STANDRED",50.0,false));
        hotel.addRoom(new Room(102,"STANDRED",50.0,false));
        hotel.addRoom(new Room(201,"DELUXE",120.0,true));
        hotel.addRoom(new Room(202,"DELUXE",120.0,true));
        hotel.addRoom(new Room(301,"SUITE",300.0,false));
        hotel.addRoom(new Room(302,"SUITE",300.0,false));

        while(running){
            System.out.println("============HOTEL RESERVATION SYSTEM==========");
            System.out.println("1.Search Room");
            System.out.println("2.Book Room");
            System.out.println("3.Cancel Booking");
            System.out.println("4.Exit");
            System.out.println("Enter your choice");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter Category (STANDRED/DELUXE/SUITE):");
                    String cat = sc.nextLine();
                    hotel.searchRoom(cat);
                    break;
                case 2:
                    System.out.println("Enter Guest Name:");
                    String guestName = sc.nextLine();
                    System.out.println("Enter Room Number:");
                    int roomNumber = sc.nextInt();
                    hotel.bookRoom(guestName,roomNumber);
                    break;
                case 3:
                    System.out.println("Enter ReservationId to cancel:");
                    int resId = sc.nextInt();
                    hotel.cancelBooking(String.valueOf(resId));
                    break;
                case 4:
                    running = false;
                    System.out.println("Existing System..");
                    break;
                default:
                    System.out.println("Invalid Choice ! please try again");
            }
        }
                sc.close();
}
}
