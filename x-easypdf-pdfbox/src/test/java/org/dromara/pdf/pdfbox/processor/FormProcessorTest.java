package org.dromara.pdf.pdfbox.processor;

import org.dromara.pdf.pdfbox.base.BaseTest;
import org.dromara.pdf.pdfbox.core.base.Document;
import org.dromara.pdf.pdfbox.core.ext.processor.FormProcessor;
import org.dromara.pdf.pdfbox.handler.PdfHandler;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xsx
 * @date 2023/11/22
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf-pdfbox is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class FormProcessorTest extends BaseTest {

    /**
     * 测试文本填写
     */
    @Test
    public void fillTextTest() {
        this.test(() -> {
            try (
                    Document document = PdfHandler.getDocumentHandler().load("E:\\PDF\\pdfbox\\form\\fillTextTest.pdf")
            ) {
                FormProcessor processor = new FormProcessor(document);

                Map<String, String> map = new HashMap<>(1);
                map.put("test", null);
                processor.fillText(map, PdfHandler.getFontHandler().getPDFont(document.getTarget(), "微软雅黑"));
                processor.clear();

                document.save("E:\\PDF\\pdfbox\\form\\fillTextTest2.pdf");
            }
        });
    }

    /**
     * 测试图像填写
     */
    @Test
    public void fillImageTest() {
        this.test(() -> {
            try (
                    Document document = PdfHandler.getDocumentHandler().load("E:\\PDF\\fo\\test\\test1.pdf")
            ) {
                FormProcessor processor = new FormProcessor(document);

                Map<String, BufferedImage> map = new HashMap<>(1);
                map.put("image", null);
                processor.fillImage(map);

                document.save("E:\\PDF\\pdfbox\\form\\fillImageTest.pdf");
            }
        });
    }

}
