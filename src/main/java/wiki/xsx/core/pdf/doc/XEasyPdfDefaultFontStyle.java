package wiki.xsx.core.pdf.doc;

/**
 * 默认字体样式枚举
 *
 * @author xsx
 * @date 2021/10/3
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
public enum XEasyPdfDefaultFontStyle {
    /**
     * 细体
     */
    LIGHT("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Light.ttf", "SourceHanSansCN-Light"),
    /**
     * 正常
     */
    NORMAL("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Normal.ttf", "SourceHanSansCN-Regular"),
    /**
     * 粗体
     */
    BOLD("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Bold.ttf", "SourceHanSansCN-Bold");

    /**
     * 字体路径
     */
    private final String path;

    /**
     * 字体名称
     */
    private final String name;

    /**
     * 有参构造
     *
     * @param path 字体路径
     * @param name 字体名称
     */
    XEasyPdfDefaultFontStyle(String path, String name) {
        this.path = path;
        this.name = name;
    }

    /**
     * 获取字体路径
     *
     * @return 返回字体路径
     */
    public String getPath() {
        return this.path;
    }

    /**
     * 获取字体名称
     *
     * @return 返回字体名称
     */
    public String getName() {
        return this.name;
    }
}
