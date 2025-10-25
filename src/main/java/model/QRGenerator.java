/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
public class QRGenerator {

    /**
     * Generates and shows a QR code image in a dialog.
     * @param code the code or text to encode in the QR image.
     */
  public class QRGenerator {

    /**
     * Generates and shows a QR code image in a dialog.
     * @param code the code or text to encode in the QR image.
     */
    public static void showQRDialog(int code) {
        try {
            String qrText = "Payment Code: " + code;

            // Create QR matrix
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrWriter.encode(qrText, BarcodeFormat.QR_CODE, 250, 250);

            // Convert matrix to image
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Create a label to display image
            ImageIcon icon = new ImageIcon(qrImage);
            JLabel label = new JLabel(icon);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            // Create dialog
            JDialog dialog = new JDialog((Frame) null, "Scan to Pay", true);
            dialog.setLayout(new BorderLayout());
            dialog.add(label, BorderLayout.CENTER);

            JLabel info = new JLabel("Scan this code and enter the number to confirm payment", SwingConstants.CENTER);
            info.setFont(new Font("SansSerif", Font.PLAIN, 14));
            dialog.add(info, BorderLayout.SOUTH);

            dialog.setSize(300, 350);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (WriterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating QR Code: " + e.getMessage());
        }
    }
}