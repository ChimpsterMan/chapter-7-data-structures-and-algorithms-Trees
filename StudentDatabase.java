//by Erik Nilsson
import java.util.Scanner;

public class StudentDatabase
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    
    BinaryTree database = new BinaryTree();
    
    String name;
    while (true){
      System.out.println("Enter:");
      System.out.println("1 to insert a new student's information,");
      System.out.println("2 to fetch and output a student's information,");
      System.out.println("3 to delete a student's information,");
      System.out.println("4 to update a student's information,");
      System.out.println("5 to output all the student information in descending order,");
      System.out.println("6 to exit the program.");
      
      switch (scan.nextInt())
      {
        case 1:
          scan.nextLine();
          System.out.println("Please enter the following information in this order: Student Name, Student ID, and Student GPA");
          if (database.insert(new Listing(scan.nextLine(), scan.nextInt(), scan.nextFloat())) == false)
          {
            System.out.println("Error: information not entered. No memory or data was inputed incorrectly.");
          }
          break;
        case 2:
          scan.nextLine();
          System.out.println("Please enter the name of the student you wish to view");
          System.out.println(database.fetch(scan.nextLine()));
          break;
        case 3:
          scan.nextLine();
          System.out.println("Please enter the name of the student you wish to be deleted");
          if (database.delete(scan.nextLine()) == true)
          {
            System.out.println("Student successfully deleted");
          }
          else 
          {
            System.out.println("Student could not be found");
          }
          break;
        case 4:
          scan.nextLine();
          System.out.println("Please enter the name of the student you wish to update");
          name = scan.nextLine();
          System.out.println("Please enter the following information in this order: Student Name, Student ID, and Student GPA");
          if (database.update(name, new Listing(scan.nextLine(), scan.nextInt(), scan.nextFloat())) == false)
          {
            System.out.println("Error: information not entered. No memory or data was inputed incorrectly.");
          }
          break;
        case 5:
          database.showAll();
          break;
        case 6:
          return;
      }
    }
  }
}