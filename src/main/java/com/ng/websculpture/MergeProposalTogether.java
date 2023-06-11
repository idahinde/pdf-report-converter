package com.ng.websculpture;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

/**
 * Author:  idris is'haq
 * E-Mail:  idahinde@gmail.com
 * Date:    12 Jul, 2021
 */

public class MergeProposalTogether {

    private final String CompDest = join("sent/poultry-farm-proposals.pdf");
    private final String FirstDest = join("compile/compile-first-page.pdf");
    private final String LastDest = join("compile/compile-continuation-farm-proposals.pdf");

    public static void main(String[] args) throws Exception {
        new MergeProposalTogether().mergeProposalFile();
    }

    protected void mergeProposalFile() throws Exception {

        PdfDocument pdf = new PdfDocument(new PdfWriter(CompDest));
        PdfMerger merger = new PdfMerger(pdf);

        //Add pages from the first document
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(FirstDest));
        merger.merge(firstSourcePdf, 1, firstSourcePdf.getNumberOfPages());

        //Add pages from the second pdf document
        PdfDocument secondSourcePdf = new PdfDocument(new PdfReader(LastDest));
        merger.merge(secondSourcePdf, 1, secondSourcePdf.getNumberOfPages());

        firstSourcePdf.close();
        secondSourcePdf.close();
        pdf.close();
    }

    private String join(String fileName) {
        return "/workspace-backup/websculpture/kano-farm-proposal/pdf/".concat(fileName);
    }

}
