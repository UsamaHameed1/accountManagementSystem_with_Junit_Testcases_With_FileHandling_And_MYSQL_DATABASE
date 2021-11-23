import java.util.Scanner;
import java.util.Vector;

public class RUN {


    public static boolean ADMIN_PANEL(Vector<AdminBank> Admin_Bank_List, Vector<Customer>Account_Holder_List) {
        boolean checkQuit = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n================================================================================================");

        do {
            System.out.println("\t\t\t\tADMIN PANEL");

            System.out.println("1. Login To Existing Admin Accout");
            System.out.println("2. Create A New Admin Accout");
            System.out.println("3. To Quit");

            System.out.print("Enter Your Choice : ");
            String choice = sc.nextLine();

            switch (choice) {

                case "1": {
                    System.out.println("--->Login For Existing Admin");
                    System.out.print("Enter ADMIN User Name : ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter ADMIN Password : ");
                    String adminPass = sc.nextLine();

                    boolean check_flag = false;
                    int admin_index_number = -1;
                    for (int i = 0; i < Admin_Bank_List.size(); i++) {
                        if (adminUser.equals(Admin_Bank_List.get(i).getUserName())) {
                            admin_index_number = i;
                            check_flag = true;
                        }
                    }

                    if (check_flag == false) {
                        System.out.println("Error : Admin Not Found");
                        return false;
                    }

                    if (adminPass.equals(Admin_Bank_List.get(admin_index_number).getPassword())) {
                        System.out.println("\n........Admin Verification Success.....");
                        Main_Menu.MAIN_MENU(Account_Holder_List);
                    }
                    else
                    {
                        System.out.println("\n\nAdmin Details not Founded \n Try Again.......\n\n");
                    }
                    break;
                }
                case "2": {
                    sc.nextLine();
                    System.out.println("\t\t\tMenu For Adding A New Admin");
                    System.out.print("Enter The Admin User Name : ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter The Admin Password : ");
                    String adminPass = sc.nextLine();

                    AdminBank obj = new AdminBank(adminUser, adminPass);
                    Admin_Bank_List.add(obj);
                    break;
                }
                case "3": {
                    checkQuit=false;

                }
                System.out.println("\n================================================================================================");
            }
        }while (checkQuit);
        return checkQuit;
    }

    public static void START(Vector<AdminBank> Admin_List, Vector<Customer> Account_Holder_list)
    {
        System.out.println("====================================================================");
        System.out.println("Starting The Accout Management System");
        System.out.println("Default Admin User Name :admin");
        System.out.println("Default Admin Password  :admin");

        boolean quit_flag=ADMIN_PANEL(Admin_List,Account_Holder_list);
        if(quit_flag ==false)
        {
            System.out.println("Thanks For Using The Program");
            System.exit(0);
        }
    }
}
