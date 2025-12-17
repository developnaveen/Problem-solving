package org.example.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class QRScan {

    public static String scan(String filePath) throws Exception {

        BufferedImage bufferedImage = ImageIO.read(new File(filePath));

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result result = new MultiFormatReader().decode(bitmap);

        return result.getText();
    }
}
