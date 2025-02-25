package org.dromara.pdf.pdfbox.support.fonts;

import org.dromara.pdf.pdfbox.core.enums.FontType;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * External font service provider interface.
 *
 * @author John Hewson
 */
public abstract class FontProvider {

    /**
     * Adds a font to the list of available fonts.
     *
     * @param file the font file
     * @return returns the font name
     */
    public abstract String addFont(File file);

    /**
     * Adds a font to the list of available fonts.
     *
     * @param inputStream the font file input stream
     * @param tempName    the font temp name
     * @param type        the font type
     * @return returns the font name
     */
    public abstract String addFont(InputStream inputStream, String tempName, FontType type);

    /**
     * Returns a string containing debugging information. This will be written to the log if no
     * suitable fonts are found and no fallback fonts are available. May be null.
     */
    public abstract String toDebugString();

    /**
     * Returns a list of information about fonts on the system.
     */
    public abstract List<? extends FontInfo> getFontInfo();
}