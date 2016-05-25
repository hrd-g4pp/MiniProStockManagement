package record;
import java.io.Serializable;
public class DataRecord implements Serializable{
    private int id;
    private String name;
    private double price;
    private long qty;
    private String date;

    public DataRecord() {
    }

    public DataRecord(int id, String name, double price, long qty, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.date = date;
    }

    public int getId() {
        return id;
    }
void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
