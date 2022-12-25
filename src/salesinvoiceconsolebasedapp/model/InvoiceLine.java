
package salesinvoiceconsolebasedapp.model;

import javax.swing.JOptionPane;


public class InvoiceLine {
     private String item;
	private double price;
	private int count;
        private int num;
        private InvoiceHeader inv;

    public InvoiceLine() {
    }

    public InvoiceLine(String item, double price, int count, InvoiceHeader inv) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.inv = inv;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
//Provide Setter and Getter For Variables To Enable Access ouside Class
    public InvoiceHeader getInv() {
        return inv;
    }

    public void setInv(InvoiceHeader inv) {
        this.inv = inv;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price<1 ){
                JOptionPane.showMessageDialog(null, "Can't Add item Please Enter Postive Item Price ", "Error", JOptionPane.ERROR_MESSAGE); 
            } else{
        this.price = price;}
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count<1 ){
                JOptionPane.showMessageDialog(null, "Please Enter  Postive Count ", "Error", JOptionPane.ERROR_MESSAGE); 
            } else{
        this.count = count;}
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "item=" + item + ", price=" + price + ", count=" + count + '}';
    }
        
    
  
        
}
