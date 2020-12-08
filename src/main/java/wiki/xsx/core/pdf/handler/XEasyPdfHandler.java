package wiki.xsx.core.pdf.handler;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.footer.XEasyPdfDefaultFooter;
import wiki.xsx.core.pdf.component.header.XEasyPdfDefaultHeader;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfDottedSplitLine;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.line.XEasyPdfSolidSplitLine;
import wiki.xsx.core.pdf.component.mark.XEasyPdfDefaultWatermark;
import wiki.xsx.core.pdf.component.rect.XEasyPdfRect;
import wiki.xsx.core.pdf.component.table.XEasyPdfCell;
import wiki.xsx.core.pdf.component.table.XEasyPdfRow;
import wiki.xsx.core.pdf.component.table.XEasyPdfTable;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * pdf助手
 * @author xsx
 * @date 2020/4/1
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class XEasyPdfHandler {

    /**
     * pdf文档
     */
    public static class Document {
        /**
         * 构建pdf
         */
        public static XEasyPdfDocument build() {
            return new XEasyPdfDocument();
        }

        /**
         * 构建pdf
         * @param sourcePath 源文件路径
         */
        @SneakyThrows
        public static XEasyPdfDocument build(String sourcePath) {
            return new XEasyPdfDocument(sourcePath);
        }

        /**
         * 构建pdf
         * @param sourceInputStream 源文件数据流
         */
        @SneakyThrows
        public static XEasyPdfDocument build(InputStream sourceInputStream) {
            return new XEasyPdfDocument(sourceInputStream);
        }
    }

    /**
     * pdf页面
     */
    public static class Page {
        /**
         * 构建页面
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfComponent...components) {
            return new XEasyPdfPage().addComponent(components);
        }

        /**
         * 构建页面
         * @param components 组件
         * @param watermark 页面水印组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfDefaultWatermark watermark, XEasyPdfComponent...components) {
            return new XEasyPdfPage().setWatermark(watermark).addComponent(components);
        }

        /**
         * 构建页面
         * @param pageSize pdfBox页面尺寸
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(PDRectangle pageSize, XEasyPdfComponent...components) {
            return new XEasyPdfPage(pageSize).addComponent(components);
        }

        /**
         * 构建页面
         * @param pageSize pdfBox页面尺寸
         * @param watermark 页面水印组件
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(PDRectangle pageSize, XEasyPdfDefaultWatermark watermark, XEasyPdfComponent...components) {
            return new XEasyPdfPage(pageSize).setWatermark(watermark).addComponent(components);
        }
    }

    /**
     * pdf水印组件
     */
    public static class Watermark {
        /**
         * 构建页面水印
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfDefaultWatermark build(String text) {
            return new XEasyPdfDefaultWatermark(text);
        }

        /**
         * 构建页面水印
         * @param fontSize 字体大小
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfDefaultWatermark build(float fontSize, String text) {
            return new XEasyPdfDefaultWatermark(fontSize, text);
        }
    }

    /**
     * pdf文本组件
     */
    public static class Text {
        /**
         * 构建文本
         * @param text 待写入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String text) {
            return new XEasyPdfText(text);
        }
        /**
         * 构建文本
         * @param textList 待写入文本列表
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(List<String> textList) {
            return new XEasyPdfText(textList);
        }
        /**
         * 构建文本
         * @param fontSize 字体大小
         * @param text 待写入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(float fontSize, String text) {
            return new XEasyPdfText(fontSize, text);
        }
        /**
         * 构建文本
         * @param fontSize 字体大小
         * @param textList 待写入文本列表
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(float fontSize, List<String> textList) {
            return new XEasyPdfText(fontSize, textList);
        }
    }

    /**
     * pdf线条组件
     */
    public static class Line {
        /**
         * 构建线条
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param endX 当前页面X轴结束坐标
         * @param endY 当前页面Y轴结束坐标
         * @return 返回pdf线条组件
         */
        public static XEasyPdfLine build(float beginX, float beginY, float endX, float endY) {
            return new XEasyPdfLine(beginX, beginY, endX, endY);
        }
    }

    /**
     * pdf分割线组件
     */
    public static class SplitLine {
        /**
         * 实线
         */
        public static class SolidLine {
            /**
             * 构建实线分割线
             * @return 返回pdf实线分割线组件
             */
            public static XEasyPdfSolidSplitLine build() {
                return new XEasyPdfSolidSplitLine();
            }
        }

        /**
         * 虚线
         */
        public static class DottedLine {
            /**
             * 构建虚线分割线
             * @return 返回pdf虚线分割线组件
             */
            public static XEasyPdfDottedSplitLine build() {
                return new XEasyPdfDottedSplitLine();
            }
        }
    }

    /**
     * pdf图片组件
     */
    public static class Image {
        /**
         * 构建图片
         * @param image 待添加图片
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image) {
            return new XEasyPdfImage(image);
        }

        /**
         * 构建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType) {
            return new XEasyPdfImage(imageInputStream, imageType);
        }

        /**
         * 构建图片
         * @param image 待添加图片
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image, int width, int height) {
            return new XEasyPdfImage(image, width, height);
        }

        /**
         * 构建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType, int width, int height) {
            return new XEasyPdfImage(imageInputStream, imageType, width, height);
        }
    }

    /**
     * pdf矩形组件
     */
    public static class Rect {
        /**
         * 构建矩形
         * @param width 宽度
         * @param height 高度
         * @return 返回pdf矩形组件
         */
        public static XEasyPdfRect build(float width, float height) {
            return new XEasyPdfRect(width, height);
        }

        /**
         * 构建矩形
         * @param width 宽度
         * @param height 高度
         * @param beginX X轴起始坐标
         * @param beginY Y轴起始坐标
         * @return 返回pdf矩形组件
         */
        public static XEasyPdfRect build(float width, float height, float beginX, float beginY) {
            return new XEasyPdfRect(width, height, beginX, beginY);
        }
    }

    /**
     * pdf表格组件
     */
    public static class Table {
        /**
         * pdf表格行
         */
        public static class Row {
            /**
             * pdf单元格
             */
            public static class Cell {
                /**
                 * 构建单元格
                 * @param text 待写入文本
                 * @return 返回pdf单元格组件
                 */
                public static XEasyPdfCell build(String text) {
                    return new XEasyPdfCell(text);
                }

                /**
                 * 构建单元格
                 * @param text 待写入文本
                 * @param hasBorder 是否有边框
                 * @return 返回pdf单元格组件
                 */
                public static XEasyPdfCell build(String text, boolean hasBorder) {
                    return new XEasyPdfCell(text, hasBorder);
                }

                /**
                 * 构建单元格
                 * @param width 宽度
                 * @param height 高度
                 * @return 返回pdf单元格组件
                 */
                public static XEasyPdfCell build(float width, float height) {
                    return new XEasyPdfCell(width, height);
                }
            }

            /**
             * 构建表格行
             * @param cells 单元格
             * @return 返回pdf表格行组件
             */
            public static XEasyPdfRow build(XEasyPdfCell...cells) {
                return new XEasyPdfRow(cells);
            }

            /**
             * 构建表格行
             * @param cellList 单元格列表
             * @return 返回pdf表格行组件
             */
            public static XEasyPdfRow build(List<XEasyPdfCell> cellList) {
                return new XEasyPdfRow(cellList);
            }
        }

        /**
         * 构建表格
         * @param rows 表格行
         * @return 返回pdf表格组件
         */
        public static XEasyPdfTable build(XEasyPdfRow ...rows) {
            return new XEasyPdfTable(rows);
        }

        /**
         * 构建表格
         * @param rowList 表格行列表
         * @return 返回pdf表格组件
         */
        public static XEasyPdfTable build(List<XEasyPdfRow> rowList) {
            return new XEasyPdfTable(rowList);
        }
    }

    /**
     * pdf页眉组件
     */
    public static class Header {
        /**
         * 构建页眉
         * @param text 待写入文本
         * @return 返回pdf页眉组件
         */
        public static XEasyPdfDefaultHeader build(String text) {
            return new XEasyPdfDefaultHeader(text);
        }

        /**
         * 构建页眉
         * @param fontSize 字体大小
         * @param text 待写入文本
         * @return 返回pdf页眉组件
         */
        public static XEasyPdfDefaultHeader build(float fontSize, String text) {
            return new XEasyPdfDefaultHeader(fontSize, text);
        }
    }

    /**
     * pdf页脚组件
     */
    public static class Footer {
        /**
         * 构建页脚
         * @param text 待写入文本
         * @return 返回pdf页脚组件
         */
        public static XEasyPdfDefaultFooter build(XEasyPdfText text) {
            return new XEasyPdfDefaultFooter(text);
        }

        /**
         * 构建页脚
         * @param image 待绘制图片
         * @return 返回pdf页脚组件
         */
        public static XEasyPdfDefaultFooter build(XEasyPdfImage image) {
            return new XEasyPdfDefaultFooter(image);
        }

        /**
         * 构建页脚
         * @param image 待绘制图片
         * @param text 待写入文本
         * @return 返回pdf页脚组件
         */
        public static XEasyPdfDefaultFooter build(XEasyPdfImage image, XEasyPdfText text) {
            return new XEasyPdfDefaultFooter(image, text);
        }
    }
}
