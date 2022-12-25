
package salesinvoiceconsolebasedapp.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class InvoiceHeader {
     private int invoiceNum;
	private String  invoiceDate;
	private String customerName;
	private ArrayList<InvoiceLine> lines;

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        String[] dateParts = invoiceDate.split("-")  ;  // this split date to validate it's format
            if (dateParts.length !=3) {
                JOptionPane.showMessageDialog(null, "Please Check that Date Format must be with Three Seoerate Parts Only  ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(null, "Invalid   Day or Month", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
        this.invoiceDate = invoiceDate;}
    }}

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getLines() {
        // Check if Items is null 
        if(lines==null){
            lines=new ArrayList<>();
      
        }
        return lines;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNum=" + invoiceNum + ", invoiceDate=" + invoiceDate + ", customerName=" + customerName + '}';
    }

   
}
