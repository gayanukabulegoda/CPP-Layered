package lk.grb.ceylonPottersPaletteLayered.qr;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class QrReader {

    private static Webcam webcam;

    public static String readQr() {
        //get the webCam
        webcam = webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));

        //webCam sets to webCam Panel
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setMirrored(false);

        JFrame jFrame = new JFrame();
        jFrame.add(webcamPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        //in-order to close webCam Manually
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the window closing event
                webcam.close();
                jFrame.dispose(); // This will dispose of the JFrame
            }
        });

        do {
            try {
                //bufferedImage --> captured images for, each loop
                BufferedImage image = webcam.getImage();

                //luminanceSource enhances the bufferedImage to reduce blur
                LuminanceSource source = new BufferedImageLuminanceSource(image);

                //binaryBitmap get encoded Qr and decoded via multiFormatReader
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(binaryBitmap);

                //if QR decoded or not
                if ( result.getText() != null ) {
                    jFrame.setVisible(false);
                    jFrame.dispose();
                    webcam.close();
                    return result.getText(); //the result; id
                }
            } catch (Exception e) {
            }
        } while (true);
    }
}
