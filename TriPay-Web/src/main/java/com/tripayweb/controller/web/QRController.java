package com.tripayweb.controller.web;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import com.tripayweb.app.model.request.QRRequest;
import com.tripayweb.util.LogCat;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

@Controller
public class QRController {
   static ByteArrayOutputStream jpegOutputStream;
    @RequestMapping(value="/GetQR",method= RequestMethod.GET)
    public void  getQRCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String qrCodeData = "Hello World!";
        String filePath = "QRCode.png";

        File file = new File("C:\\Users\\vibhu\\Documents\\QRCode.png");
        String charset = "UTF-8"; // or "ISO-8859-1"
        jpegOutputStream = new ByteArrayOutputStream();
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            createQRImage(file,qrCodeData,1000, "png");
            System.err.print(file.getPath());
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(jpegOutputStream.toByteArray());
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        System.out.println("QR Code image created successfully!");
        response.setContentType("image/png");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//        responseOutputStream.write(qrCode);
//        responseOutputStream.flush();
//        responseOutputStream.close();

    }

    public static void createQRCode(String qrCodeData, String filePath,
                                    String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {
//        BitMatrix matrix = new MultiFormatWriter().encode(
//                new String(qrCodeData.getBytes(charset), charset),
//                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, );
//        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
//                .lastIndexOf('.') + 1), new File(filePath));
    }

    public static String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
//                new BufferedImageLuminanceSource(
//                        ImageIO.read(new FileInputStream(filePath)))));
//        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
//                hintMap);
        return null;
    }



    private static void createQRImage(File qrFile, String qrCodeText, int size,
                                      String fileType) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
                BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
                BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j <  matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        System.err.print(qrFile.getPath());
        ImageIO.write(image, fileType, qrFile);
//        ImageIO.write(image,fileType,)
    }
    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

}
