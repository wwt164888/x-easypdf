package org.dromara.pdf.pdfbox.component;

import org.dromara.pdf.pdfbox.base.BaseTest;
import org.dromara.pdf.pdfbox.core.base.Document;
import org.dromara.pdf.pdfbox.core.base.MemoryPolicy;
import org.dromara.pdf.pdfbox.core.base.Page;
import org.dromara.pdf.pdfbox.core.base.PageSize;
import org.dromara.pdf.pdfbox.core.component.Image;
import org.dromara.pdf.pdfbox.core.component.Textarea;
import org.dromara.pdf.pdfbox.handler.PdfHandler;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;

/**
 * @author xsx
 * @date 2023/8/24
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
public class ImageTest extends BaseTest {

    /**
     * 测试png图片
     */
    @Test
    public void pngTest() {
        this.test(() -> {
            Document document = PdfHandler.getDocumentHandler().create();
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);

            Image image = new Image(page);
            image.setImage(Paths.get("E:\\PDF\\pdfbox\\image\\test.png").toFile());
            image.setWidth(100);
            image.setHeight(100);
            image.setAngle(45F);
            image.render();

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\pngTest.pdf");
            document.close();
        });
    }

    /**
     * 测试jpeg图片
     */
    @Test
    public void jpegTest() {
        this.test(() -> {
            Document document = PdfHandler.getDocumentHandler().create();
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);

            Image image = new Image(page);
            image.setImage(Paths.get("E:\\PDF\\pdfbox\\image\\test.jpg").toFile());
            image.render();

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\jpegTest.pdf");
            document.close();
        });
    }

    /**
     * 测试svg图片
     */
    @Test
    public void svgTest() {
        this.test(() -> {
            Document document = PdfHandler.getDocumentHandler().create();
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);

            Image image = new Image(page);
            image.setImage(new File("E:\\PDF\\pdfbox\\image\\test.svg"));
            image.render();

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\svgTest.pdf");
            document.close();
        });
    }

    /**
     * 测试图片尺寸
     */
    @Test
    public void sizeTest() {
        this.test(() -> {
            Document document = PdfHandler.getDocumentHandler().create();
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);

            Image image = new Image(page);
            image.setWidth(200);
            image.setHeight(200);
            image.setImage(Paths.get("E:\\PDF\\pdfbox\\image\\test.jpg").toFile());
            image.render();

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\sizeTest.pdf");
            document.close();
        });
    }

    /**
     * 测试文本+图片
     */
    @Test
    public void mixTest() {
        this.test(() ->{
            Document document = PdfHandler.getDocumentHandler().create();
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);

            Textarea leftTextarea = new Textarea(page);
            leftTextarea.setText("左侧文本");
            leftTextarea.setFontColor(Color.BLUE);
            leftTextarea.render();

            Image image = new Image(page);
            image.setWidth(300);
            image.setHeight(300);
            image.setRelativeBeginY(-12F);
            image.setImage(Paths.get("E:\\PDF\\pdfbox\\image\\test.jpg").toFile());
            image.render();

            Textarea rightTextarea = new Textarea(page);
            rightTextarea.setText("右侧文本");
            rightTextarea.setFontColor(Color.GREEN);
            rightTextarea.render();

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\mixTest.pdf");
            document.close();
        });
    }

    /**
     * 测试大数据
     */
    @Test
    public void bigDataTest() {
        this.test(() -> {
            Document document = PdfHandler.getDocumentHandler().create(MemoryPolicy.setupTempFileOnly());
            document.setMargin(50F);

            Page page = document.createPage(PageSize.A4);
            int width = page.getWidth().intValue();
            int height = page.getHeight().intValue();

            this.test(() -> {
                for (int i = 1; i < 3; i++) {
                    Image image = new Image(document.getCurrentPage());
                    image.setWidth(width);
                    image.setHeight(height);
                    image.setImage(Paths.get("E:\\PDF\\pdfbox\\document\\imager\\x-easypdf" + i + ".png").toFile());
                    image.render();
                }
            }, "渲染完成");

            document.appendPage(page);
            document.save("E:\\PDF\\pdfbox\\image\\bigDataTest.pdf");
            document.close();
        }, "生成文件");
    }
}
