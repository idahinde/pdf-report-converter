package com.ng.websculpture;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

/**
 * Author:  idris is'haq
 * E-Mail:  idahinde@gmail.com
 * Date:    12 Jul, 2021
 */

public class PrepareProposalsFirstPage {

    private final String imageFilePath = join("jpg/laterhead.jpg");
    private final String Dest = join("pdf/compile/compile-first-page.pdf");
    private final String Source = join("pdf/proposal-first-page.pdf");

    public static void main(String[] args) throws Exception {
        new PrepareProposalsFirstPage().manipulatePdf();
    }

    protected void manipulatePdf() throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(Source), new PdfWriter(Dest));
        Document doc = new Document(pdfDoc);

        PdfWaterMark pdfWaterMark = new PdfWaterMark(imageFilePath, 1.39f);
        doc.getPdfDocument().addEventHandler(PdfDocumentEvent.END_PAGE, pdfWaterMark);
        doc.close();
    }

    private String join(String fileName) {
        return "/workspace-backup/websculpture/kano-farm-proposal/".concat(fileName);
    }

}
