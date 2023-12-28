package lk.grb.ceylonPottersPaletteLayered.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QrGenerator {

    public static void generateQr(String id) throws IOException, WriterException {
        String input = id;
        String path = "/home/gayanuka/Documents/QR/";
        String charset = StandardCharsets.UTF_8.name();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        generateQrCode(input, path + input + ".png", charset, hints, 400, 400);
    }

    public static void generateQrCode(
            String data, String path, String charset,
            Map<EncodeHintType, ?> hints, int h, int w) throws IOException, WriterException {

        BitMatrix matrix = new MultiFormatWriter().
                encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h, hints);

        MatrixToImageWriter.
                writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
}