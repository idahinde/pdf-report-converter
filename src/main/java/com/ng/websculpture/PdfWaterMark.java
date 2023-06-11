package com.ng.websculpture;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Author:  idris is'haq
 * E-Mail:  idahinde@gmail.com
 * Date:    12 Jul, 2021
 */

public class PdfWaterMark implements IEventHandler {

    private float opacity = 0;
    private String imageFilePath = "";

    public PdfWaterMark(String imageFilePath, float opacity) {
        this.opacity = opacity;
        this.imageFilePath = imageFilePath;
    }

    @Override
    public void handleEvent(Event event) {

        try {

            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();

            Rectangle rect = docEvent.getPage().getPageSize();

            FileInputStream fileInputStream = new FileInputStream(new File(imageFilePath));
            byte[] targetArray = IOUtils.toByteArray(fileInputStream);
            ImageData imageData = ImageDataFactory.create(targetArray);

            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
            pdfCanvas.saveState();
            PdfExtGState state = new PdfExtGState().setFillOpacity(opacity);
            pdfCanvas.setExtGState(state);

            pdfCanvas.addImage(imageData, rect, false);
            pdfCanvas.restoreState();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
