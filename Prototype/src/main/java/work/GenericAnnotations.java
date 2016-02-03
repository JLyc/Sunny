/*
 * Copyright (c) 2016. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package work;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.*;

import java.io.*;

public class GenericAnnotations extends PdfPageEventHelper {

    /** The resulting PDF. */
    public static final String RESULT
            = "D:/generic_annotations.pdf";
    /** Possible icons. */
    public static final String[] ICONS = {
            "Comment", "Key", "Note", "Help", "NewParagraph", "Paragraph", "Insert"
    };

    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args)
            throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer
                = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setPageEvent(new GenericAnnotations());
        // step 3
        document.open();
        // step 4
        Paragraph p = new Paragraph();
        Chunk chunk;
        Chunk tab = new Chunk(new VerticalPositionMark());
        for (int i = 0; i < ICONS.length; i++) {
            chunk = new Chunk(ICONS[i]);
            chunk.setGenericTag(ICONS[i]);
            PdfAction pdfAction = new PdfAction(PdfAction.STRING);
            p.add(chunk);
            p.add(tab);
        }
        document.add(p);
        // step 5
        document.close();
    }

    /**
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onGenericTag(
     *      com.itextpdf.text.pdf.PdfWriter,
     *      com.itextpdf.text.Document,
     *      com.itextpdf.text.Rectangle, String)
     */
    @Override
    public void onGenericTag(PdfWriter writer,
                             Document document, Rectangle rect, String text) {
        PdfAnnotation annotation = new PdfAnnotation(writer,
                new Rectangle(
                        rect.getRight() + 10, rect.getBottom(),
                        rect.getRight() + 30, rect.getTop()));
        annotation.setTitle("Text annotation");
        annotation.put(PdfName.SUBTYPE, PdfName.TEXT);
        annotation.put(PdfName.OPEN, PdfBoolean.PDFFALSE);
        annotation.put(PdfName.CONTENTS,
                new PdfString(String.format("Icon: %s", text)));
        annotation.put(PdfName.NAME, new PdfName(text));
        writer.addAnnotation(annotation);
        writer.addAnnotation(PdfAnnotation.createPopup(writer, new Rectangle(200f, 500f, 300f, 600f), "Saibaba", true));
    }

}