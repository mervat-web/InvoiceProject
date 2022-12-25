
package salesinvoiceconsolebasedapp.model;

import exception.WrongFileFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FileOperations extends JFrame
{
    private final ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
    private final ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

    //Read Item  data Method
    public ArrayList<InvoiceLine> readLineFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = fc.getSelectedFile().getPath();
        String line;
        try {
            if (result == JFileChooser.APPROVE_OPTION && path.endsWith(".csv")) {
                FileInputStream file = null;
                try {
                    file = new FileInputStream(path);
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    while ((line = br.readLine()) != null) {
                        String[] lines = line.split(",");

                        InvoiceLine invoiceLine = new InvoiceLine();
                        invoiceLine.setNum(Integer.parseInt(lines[0]));
                        invoiceLine.setItem(lines[1]);
                        invoiceLine.setCount(Integer.parseInt(lines[2]));
                        invoiceLine.setPrice(Double.parseDouble(lines[2]));

                        invoiceLines.add(invoiceLine);
                    }
                } catch (FileNotFoundException e) {
                    
JOptionPane.showMessageDialog(null,"File Note Found");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,"Wrong file format must ends with CSV");
            }
        } catch (WrongFileFormatException e) {
           JOptionPane.showMessageDialog(null,"Wrong file format must ends with CSV");;
        }
        return invoiceLines;
    }

    //read header file function
    public ArrayList<InvoiceHeader> readHeaderFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = fc.getSelectedFile().getPath();
        String line;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            if (result == JFileChooser.APPROVE_OPTION && path.endsWith(".csv")) {
                FileInputStream file = null;
                try {
                    file = new FileInputStream(path);
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    while ((line = br.readLine()) != null) {
                        String[] headers = line.split(",");
                        InvoiceHeader invoiceHeader = new InvoiceHeader();

                        invoiceHeader.setInvoiceNum(Integer.parseInt(headers[0]));
                        invoiceHeader.setInvoiceDate(headers[1]);
                        invoiceHeader.setCustomerName(headers[2]);
                        invoiceHeaders.add(invoiceHeader);
                        try {
                            dateFormat.parse(invoiceHeader.getInvoiceDate());
                            System.out.println("Date is valid");
                        } catch (ParseException e) {
                            System.out.println("Date Is not valid ");
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        assert file != null;
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                throw new WrongFileFormatException("Wrong file format must ends with CSV");
            }
        } catch (WrongFileFormatException e) {
            e.printStackTrace();
        }
        return invoiceHeaders;
    }

    //write header data function
    public void writeHeaderFile(ArrayList<InvoiceHeader> invoiceHeadersList) {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = fc.getSelectedFile().getPath();
        File log = new File(path);
        if (result == JFileChooser.APPROVE_OPTION && path.endsWith(".csv")) {
            try {
                PrintWriter write = new PrintWriter(new FileWriter(log, true));
                for (InvoiceHeader invoice : invoiceHeadersList) {
                    write.append(invoice.getInvoiceNum() + "," + invoice.getInvoiceDate() + "," + invoice.getCustomerName() + "\n");
                }
                write.close();
                System.out.println();
                System.out.print("Done Write");
            } catch (WrongFileFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    //write invoice data function
    public void writelineFile(ArrayList<InvoiceLine> invoiceLineList) {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        String path = fc.getSelectedFile().getPath();
        File log = new File(path);
        if (result == JFileChooser.APPROVE_OPTION && path.endsWith(".csv")) {
            try {
                PrintWriter write = new PrintWriter(new FileWriter(log, true));
                for (InvoiceLine invoice : invoiceLineList) {
                    write.append(invoice.getNum() + "," + invoice.getItem() + "," + invoice.getPrice() + "," + invoice.getCount() + "\n");
                }
                write.close();
                System.out.println();
                System.out.print("Done Write");
            } catch (WrongFileFormatException | NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
