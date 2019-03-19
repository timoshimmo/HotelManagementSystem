/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.dialogs;

import greenvalley.MainPage;
import greenvalley.database.DatabaseConnection;
import greenvalley.PrintPreview;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author freshfuturesmy
 */
public final class ReceiptDialog extends JDialog implements ActionListener  {
    
    JLabel lblDialogTitle;
    JLabel lblDate;
    JPanel receiptPanel;
    
    final static String ROOM_SERVICES_PANEL = "RoomServicesPanel";
    DatabaseConnection dbc;
    
    String guestForm;
    String nairaSymbol;
    
    private final JTextPane mTextPane;
    
    public ReceiptDialog(final Frame frame, boolean modal, String fName, String surName, String mNo,
            String styDays, String nlty, String gndr, String discount, String roomType, 
            String rNo, String prc, String arrDate, String dptDate, String totAmt, JPanel contentLayout) {
        
        super(frame, modal);
        
        dbc = new DatabaseConnection();
        
        loadJSONFromResource();
        
        setTitle("Receipt");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        mTextPane = new JTextPane();
        mTextPane.setContentType("text/html");
        
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>GUEST FORM</h1><table width=\"100%\">");
        builder.append("<tr><td>First Name    :</td><td>").append(fName).append("</td></tr>");
        builder.append("<tr><td>Last Name     :</td><td>").append(surName).append("</td></tr>");
        builder.append("<tr><td>Mobile No.    :</td><td>").append(mNo).append("</td></tr>");
        builder.append("<tr><td>Nationality   :</td><td>").append(nlty).append("</td></tr>");
        builder.append("<tr><td>Gender        :</td><td>" + gndr + "</td></tr>");
        builder.append("<tr><td>Room Type     :</td><td>").append(roomType).append("</td></tr>");
        builder.append("<tr><td>Room No.      :</td><td>").append(rNo).append("</td></tr>");
        builder.append("<tr><td>Room Price    :</td><td>").append(nairaSymbol).append(prc).append("</td></tr>");
        builder.append("<tr><td>Checkin       :</td><td>").append(arrDate).append("</td></tr>");
        builder.append("<tr><td>Departure     :</td><td>").append(dptDate).append("</td></tr>");
        builder.append("<tr><td>Staying For   :</td><td>").append(styDays).append(" days</td></tr>");
        builder.append("<tr><td></td><td></td></tr>");
        builder.append("<tr><td>Total Amount  :</td><td>").append(nairaSymbol).append(totAmt).append("</td></tr>");
        builder.append("</table>");
        
        mTextPane.setText(builder.toString());
		
        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener(this);

        panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);
        panel.add(previewButton, BorderLayout.SOUTH);
        getContentPane().add(panel);
        
        pack();
        setLocationRelativeTo(frame);
    }
    
    public void loadJSONFromResource() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(getClass().getResource("/json/common_currency.json").getFile()));
            JSONObject jsonObject = (JSONObject) obj;
              
            JSONObject currencyObj = (JSONObject) jsonObject.get("NGN");
            nairaSymbol = (String) currencyObj.get("symbol");
           
        } catch (IOException ex) {
           
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(700, 400);
        dialog.setLayout(new BorderLayout());
        
        this.dispose();

        HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        set.add(MediaSizeName.ISO_A4);
        set.add(OrientationRequested.PORTRAIT);
        
        PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

        final PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);

        JButton printButton = new JButton("Print!");
        printButton.addActionListener((ActionEvent arg0) -> {
            preview.print();
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(printButton);
        buttonsPanel.add(preview.getControls());

        dialog.getContentPane().add(preview, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
