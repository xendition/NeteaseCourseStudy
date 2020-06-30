package com.michael.demos.common.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   pdf工具类
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/6/29 11:19
 */
public class PDFUtils {

    public static void main(String[] args) throws IOException {

        String pdfPath = "E://pdf_test/test/test.pdf";
        createPDF(pdfPath, null);
    }

    public static void createPDF(String pdfPath, List<String> imagePaths) throws IOException {

        PDDocument pdf = new PDDocument();

        File file = new File(pdfPath);
        file.getParentFile().mkdirs();
        pdf.save(pdfPath);
        pdf.close();
    }


    /**
     * 将多页pdf转化为多张图片
     */
    public static List<String> pdfPathToImagePaths(String pdfPath) {
        File pdfFile = new File(pdfPath);
        PDDocument pdDocument;
        try {
            pdDocument = PDDocument.load(pdfFile);
            int pageCount = pdDocument.getNumberOfPages();
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            List<String> imagePathList = new ArrayList<>();
            String fileParent = pdfFile.getParent();
            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                String imgPath = fileParent + File.separator + pageIndex + ".png";
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 105, ImageType.RGB);
                ImageIO.write(image, "png", new File(imgPath));
                imagePathList.add(imgPath);

            }
            pdDocument.close();
            return imagePathList;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
