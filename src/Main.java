import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carId;
    private String carName;
    private String carBrand;
    private double basePricePerDay;
    boolean isAvilable;

    public Car(String carId, String carName, String carBrand, double basePricePerDay) {
        this.carId = carId;
        this.carName = carName;
        this.carBrand = carBrand;
        this.basePricePerDay = basePricePerDay;
        this.isAvilable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarBrand() {
        return carBrand;
    }


    public double calculatePrice(int rentalDays){
        return basePricePerDay*rentalDays;
    }
    public boolean isAvilable(){
        return isAvilable;

    }
    public void rent(){
         isAvilable=false;
    }
    public void returnCar(){
        isAvilable=true;
    }
}
class Customer{
    private String customerId;
    private String customerName;

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
}
class Rental{
    Car car;
    Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

}
class RentalSystem{
    private List<Car> carList;
    private List<Customer> customerList;
    private List<Rental> rentalList;
public RentalSystem(){
    carList = new ArrayList<>();
    customerList = new ArrayList<>();
    rentalList = new ArrayList<>();
}
public void addCar(Car car){
    carList.add(car);
}
public void addCustomer(Customer customer){
    customerList.add(customer);

}
public void rentCar(Car car , Customer customer, int days){
    if (car.isAvilable()) {
        car.rent();
        rentalList.add(new Rental(car,customer,days));
    }
    else {
        System.out.println("car is not avilable for rent");
    }

}
public void returnCar(Car car){
    Rental retuenToRemove = null;
    car.returnCar();
    for(Rental rental:rentalList){
        if(rental.getCar()==car){
            retuenToRemove= rental;
            break;
        }
        if(retuenToRemove!=null){
            rentalList.remove(retuenToRemove);
        }
        else {
            System.out.println("car was not rented");
        }
    }

}
public void menu(){
    Scanner scanner = new Scanner(System.in);
    while(true){
        System.out.println("==== Car Rental System ====");
        System.out.println("1 : Rent a car");
        System.out.println("2 : Return  a car");
        System.out.println("3 : Exit ");
        System.out.println("Enter your choice ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            System.out.println("=== Rent a Car");
            System.out.println("Enter your name");
            String customerName = scanner.nextLine();
            System.out.println("\n Avilable Cars");
            for(Car car : carList){
                if(car.isAvilable()){
                    System.out.println("Car id : "+car.getCarId()+" Car Name : "+car.getCarName()+" Brand : "+car.getCarBrand());
                }
            }
            System.out.println("Enter the car id you want to Rent");
            String carId = scanner.nextLine();
            System.out.println("Enter the number fo days you want to rent");
            int rentDays = scanner.nextInt();

            Customer newCustomer = new Customer("CUS" + (customerList.size() + 1), customerName);
            addCustomer(newCustomer);

            Car selectedCar = null;
            for(Car car : carList){
                if(car.getCarId().equals(carId)&&car.isAvilable()){
                    selectedCar = car;
                    break;
                }
            }
            if(selectedCar!=null){
                double totalPrice = selectedCar.calculatePrice(rentDays);
                System.out.println("\n ==== Rental Information ====");
                System.out.println("Customer Id : "+newCustomer.getCustomerId());
                System.out.println("Customer name : "+newCustomer.getCustomerName());
                System.out.println("Car : "+selectedCar.getCarName() + "Brand : "+selectedCar.getCarBrand());
                System.out.println("Total Price : "+totalPrice);
                System.out.println("Conferm Rental (Y/N)");
               scanner.nextLine();
                String confirms = scanner.nextLine();
                if(confirms.equalsIgnoreCase("Y")){
                    rentCar(selectedCar,newCustomer,rentDays);
                    System.out.println("Rented Successfully ");

                }
                else {
                    System.out.println("Rented cancelled");
                }

            }
            else {
                System.out.println("Invalid car selectoin OR car is not avilable for rent");
            }



        } else if (choice==2) {
            System.out.println("Enter the car id you want to return");
            String carid = scanner.nextLine();
            Car cartoReturn = null;
            for (Car car : carList){
                if(car.getCarId().equalsIgnoreCase(carid)&&!car.isAvilable()){
                    cartoReturn = car;
                    break;
                }
            }
            if(cartoReturn!=null){
                Customer customer = null;
               for(Rental rental:rentalList){
                   if(rental.getCar()==cartoReturn){
                       customer = rental.getCustomer();
                       break;

                   }
               }
               if(customer!=null){
                   returnCar(cartoReturn);
                   System.out.println("Car Raturned successfully by : "+customer.getCustomerName());
               }
               else {
                   System.out.println("car was not rented");
               }
            }

            
        } else if (choice==3) {
            break;

        }
        else {
            System.out.println("Invalid choice !!!");
        }
    }
    System.out.println("\n Thank You For Using Car Rental System✌️");
}



}



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();
        Car car1 = new Car("C001","BMW", "BMW",2000);
        Car car2 = new Car("C002","Maruti", "Tata",1500);
        Car car3 = new Car("C003","Thar", "Mahindra",2500);
        Car car4 = new Car("C004","Figo", "ford",1100);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.menu();


    }
}