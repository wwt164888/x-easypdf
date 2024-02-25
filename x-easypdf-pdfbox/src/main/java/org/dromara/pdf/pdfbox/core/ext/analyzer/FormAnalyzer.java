package org.dromara.pdf.pdfbox.core.ext.analyzer;

import org.apache.pdfbox.pdmodel.PDPage;
import org.dromara.pdf.pdfbox.core.base.Document;

/**
 * 表单分析器
 *
 * @author xsx
 * @date 2024/2/21
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf-pdfbox is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class FormAnalyzer extends AbstractFormAnalyzer {

    /**
     * 有参构造
     *
     * @param document 文档
     */
    public FormAnalyzer(Document document) {
        super(document);
    }

    /**
     * 处理表单
     *
     * @param pageIndex 页面索引
     * @param page      pdfbox页面
     */
    @Override
    public void processForm(int pageIndex, PDPage page) {
        this.infoSet.addAll(this.getFields(pageIndex));
    }
}