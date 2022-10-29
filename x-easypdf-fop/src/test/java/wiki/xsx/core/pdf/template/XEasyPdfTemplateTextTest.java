package wiki.xsx.core.pdf.template;

import org.junit.Test;
import wiki.xsx.core.pdf.template.doc.XEasyPdfTemplateDocument;
import wiki.xsx.core.pdf.template.handler.XEasyPdfTemplateHandler;

import java.awt.*;

/**
 * @author xsx
 * @date 2022/8/6
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
 * x-easypdf is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class XEasyPdfTemplateTextTest {

    @Test
    public void testText() {
        // 定义fop配置文件路径
        String configPath = "E:\\pdf\\test\\fo\\fop.xconf";
        // 定义输出路径
        String outputPath = "E:\\pdf\\test\\fo\\template-text.pdf";
        // 转换pdf
        XEasyPdfTemplateDocument document = XEasyPdfTemplateHandler.Document.build().setConfigPath(configPath).addPage(
                XEasyPdfTemplateHandler.Page.build()
                        .setFontSize("30pt")
                        .setFontColor(Color.BLUE)
                        .addBodyComponent(
                                XEasyPdfTemplateHandler.Text.build().setText("加粗")
                                        .setFontFamily("微软雅黑")
                                        .setFontWeight("bold")
                                        .setHorizontalStyle("right")
                                        .setMarginRight("10pt"),
                                XEasyPdfTemplateHandler.Text.build().setText("不加粗")
                                        .setFontFamily("微软雅黑")
                                        .setFontColor(Color.BLUE)
                                        .enableDeleteLine()
                                        .setDeleteLineColor(Color.RED)
                                        .enableUnderLine()
                                        .setUnderLineColor(Color.RED)
                                        .setUnderLineWidth("3pt")
                        )
        );
        // 转换pdf
        document.transform(outputPath);
    }

    @Test
    public void testTextExtend() {
        // 定义fop配置文件路径
        String configPath = "E:\\pdf\\test\\fo\\fop.xconf";
        // 定义输出路径
        String outputPath = "E:\\pdf\\test\\fo\\template-textExtend.pdf";
        // 转换pdf
        XEasyPdfTemplateDocument document = XEasyPdfTemplateHandler.Document.build().setConfigPath(configPath).addPage(
                XEasyPdfTemplateHandler.Page.build().addBodyComponent(
                        XEasyPdfTemplateHandler.TextExtend.build().addTexts(
                                XEasyPdfTemplateHandler.Text.build().setText("hello"),
                                XEasyPdfTemplateHandler.Text.build().setText(" my world")
                        )
                )
        );
        // 转换pdf
        document.transform(outputPath);
    }
}
