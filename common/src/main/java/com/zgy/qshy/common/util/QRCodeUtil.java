package com.zgy.qshy.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


@Component
@ConfigurationProperties(prefix = "qr-code")
@Data
public class QRCodeUtil {

    // 服务器地址
    private String ip;

    // 根目录
    private String rootPath;

    // 文件目录
    private String path;

    // 文件后缀
    private String ext;

    // 图片大小
    private int size;

    public String generateQRCodeImage(String code) throws WriterException, IOException {
        String text = ip + code;
        String filePath = rootPath + path + code + "." + ext;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, ext, path);
        return this.path + code + "." + ext;
    }

    @Override
    public String toString() {
        return "QRCodeUtil{" +
                "ip='" + ip + '\'' +
                ", rootPath='" + rootPath + '\'' +
                ", path='" + path + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
