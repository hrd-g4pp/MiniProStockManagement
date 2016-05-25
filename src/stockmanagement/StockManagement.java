package stockmanagement;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import record.DataRecord;

public class StockManagement {
    public  ArrayList<DataRecord> arr=new ArrayList<DataRecord>();
    private File f;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private ObjectOutputStream oos;
    
    private Date d=new Date();
    private int id=1;
    private String name;
    private double price;
    private long qty;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String date=sdf.format(d);
    private Scanner sc=new Scanner(System.in);
    
    public void readRecord() throws IOException, ClassNotFoundException
    {
        f=new File("record/record.ppg4");
        
        if(!f.exists())
        {
            f.createNewFile();
        }
        
        fis=new FileInputStream(f);
        bis=new BufferedInputStream(fis);
        
        try {
            ois=new ObjectInputStream(bis);
            arr=(ArrayList<DataRecord>)ois.readObject();
        } catch (EOFException e) {
        }

        if(arr.size()>0)
        {
            try {
                System.out.println("\n\nID\tName\tPrice\tQty\tDate");
                for(int i=0;i<arr.size();i++){                
                    DataRecord  obj=arr.get(i);
                    String st[]={obj.getId()+"",obj.getName(),obj.getQty()+"",obj.getPrice()+"",obj.getDate()};
                    System.out.println(st[0]+"\t"+st[1]+"\t"+st[2]+"\t"+st[3]+"\t"+st[4]);
                }
            } catch (NullPointerException e) {
            }
        }
        else
        {
            System.out.println("\nThere are no record!");
        }
        
  //    ois.close();
        bis.close();
        fis.close();
    }

    public void writeRecord() throws Exception,NullPointerException
    {
        f=new File("record/record.ppg4");
       
        //Read Object
        //We need to read all record from file to ArrayList first. Because we overwrite old record of File with new record of Last ArrayList.
        //If We don't read from File to ArrayList first, old record will lost and has only the last record we inserted.
        fis=new FileInputStream(f);
        bis=new BufferedInputStream(fis);
        
        //We need to catch EOF error. Because sometime our file has no record.
        try {
            ois=new ObjectInputStream(bis);
            arr=(ArrayList<DataRecord>)ois.readObject();
        } catch (EOFException e) {
        }
        
        //Write Object
        fos=new FileOutputStream(f);
        bos=new BufferedOutputStream(fos);
        oos=new ObjectOutputStream(bos);
                    
        //Get ID        
        try {
            for(int i=0;i<arr.size();i++)
            {
                DataRecord obj=arr.get(i);
                String st[]={obj.getId()+"",obj.getName(),obj.getQty()+"",obj.getPrice()+"",obj.getDate()};
                id=Integer.valueOf(st[0])+1;
            }
        } catch (NullPointerException e) {
        }

     
        System.out.println("\n\nCreating new record...");
        System.out.println("ID:"+id);
        System.out.print("Name: ");
        name=sc.nextLine();
        System.out.print("Price: ");           
        price=sc.nextDouble();
        System.out.print("Qty: ");           
        qty=sc.nextLong();
            
        DataRecord obj=new DataRecord(id,name,price,qty,date);          
        arr.add(obj);
        oos.writeObject(arr);
        
        oos.close();
        bos.close();
        fos.close();
    }
    public static void main(String[] args) throws Exception {
        StockManagement s=new StockManagement();
        Threads th=new Threads("Loading Record...");
        th.start();
        s.readRecord();
        s.writeRecord();
        s.readRecord();
        
        System.out.println("testing....coding with github");
    }
}
