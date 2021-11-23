package Databases;

import java.sql.*;

public class connectMySQL {
    public static void connectMySQL_DB()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:4399/account_management","root","hello123");

            System.out.println("Success");
            Statement stmt =con.createStatement();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void insertMYSQL_DB(String insertName,String insertAdd,String insertaccNo, long balance,String phoneNo,int accountType)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:4399/account_management","root","hello123");

            System.out.println("Success");
            Statement stmt =con.createStatement();

            //account type will help in determining which type of the account it is given
            if(accountType ==1) //checking account type
            {
                System.out.println("Checking Acc DB");
                String sql ="INSERT INTO checking_account (name,address,accountNumber,balance,phone_no) VALUES (?,?,?,?,?)";
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1,insertName);
                statement.setString(2,insertAdd);
                statement.setString(3,insertaccNo);
                statement.setInt(4,(int)balance);
                statement.setString(5,phoneNo);

                int rowInserted = statement.executeUpdate();
                if(rowInserted >0)
                {
                    System.out.println("New Row Inserted Successfully");
                }
                else
                {
                    System.out.println("New Row Inserted Failed");
                }
            }
            else if(accountType ==2)    //saving account
            {
                System.out.println("Saving Acc DB");
                String sql ="INSERT INTO saving_account (name,address,accountNumber,balance,phone_no) VALUES (?,?,?,?,?)";
                PreparedStatement statement=con.prepareStatement(sql);
                statement.setString(1,insertName);
                statement.setString(2,insertAdd);
                statement.setString(3,insertaccNo);
                statement.setInt(4,(int)balance);
                statement.setString(5,phoneNo);

                int rowInserted = statement.executeUpdate();
                if(rowInserted >0)
                {
                    System.out.println("New Row Inserted Successfully");
                }
                else
                {
                    System.out.println("New Row Inserted Failed");
                }
            }
            else
            {
                System.out.println("Invalid Account Type .....");
            }

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

    public static void main(String[] args) {
        connectMySQL_DB();
    }
}
