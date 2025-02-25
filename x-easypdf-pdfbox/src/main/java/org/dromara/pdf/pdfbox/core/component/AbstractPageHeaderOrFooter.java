package org.dromara.pdf.pdfbox.core.component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.dromara.pdf.pdfbox.core.base.*;
import org.dromara.pdf.pdfbox.util.BorderUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 抽象页眉页脚
 *
 * @author xsx
 * @date 2023/9/21
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
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractPageHeaderOrFooter extends AbstractBaseBorder {
    /**
     * 宽度
     */
    private Float width;
    /**
     * 高度
     */
    private Float height;
    /**
     * 上下文
     */
    private Context context;
    /**
     * X轴起始坐标
     */
    private Float beginX;
    /**
     * Y轴起始坐标
     */
    private Float beginY;
    /**
     * 组件列表
     */
    private List<Component> components;

    /**
     * 无参构造
     */
    public AbstractPageHeaderOrFooter() {
        throw new IllegalStateException("can not be initialized");
    }

    /**
     * 有参构造
     *
     * @param page 页面
     */
    public AbstractPageHeaderOrFooter(Page page) {
        this.context = page.getContext();
    }

    /**
     * 获取类型
     *
     * @return 返回类型
     */
    public abstract ComponentType getType();

    /**
     * 获取分页事件
     *
     * @return 返回分页事件
     */
    public abstract PagingEvent getPagingEvent();

    /**
     * 设置宽度
     *
     * @param width 宽度
     */
    public void setWidth(Float width) {
        // 校验宽度
        Objects.requireNonNull(width, "the width can not be null");
        if (width > this.getContext().getPage().getWithoutMarginWidth()) {
            throw new IllegalArgumentException("the width can not be greater than the page width");
        }
        this.width = width;
    }

    /**
     * 设置高度
     *
     * @param height 高度
     */
    public void setHeight(Float height) {
        // 校验高度
        Objects.requireNonNull(height, "the height can not be null");
        if (height > this.getContext().getPage().getWithoutMarginHeight()) {
            throw new IllegalArgumentException("the height can not be greater than the page height");
        }
        this.height = height;
    }

    /**
     * 获取页面
     *
     * @return 返回页面
     */
    public Page getPage() {
        return this.getContext().getPage();
    }

    /**
     * 渲染前
     */
    public void renderBefore() {

    }

    /**
     * 虚拟渲染
     */
    @SneakyThrows
    public void virtualRender() {
        // 校验宽度
        Objects.requireNonNull(this.getWidth(), "the width can not be null");
        // 校验高度
        Objects.requireNonNull(this.getHeight(), "the height can not be null");
        // 初始化
        this.init();
        // 渲染组件
        Optional.ofNullable(this.getComponents())
                .orElse(Collections.emptyList())
                .forEach(this::virtualRenderComponent);
        // 重置
        this.reset();
    }

    /**
     * 渲染
     */
    public void render() {
        // 校验宽度
        Objects.requireNonNull(this.getWidth(), "the width can not be null");
        // 校验高度
        Objects.requireNonNull(this.getHeight(), "the height can not be null");
        // 初始化
        this.init();
        // 渲染前
        this.renderBefore();
        // 渲染组件
        Optional.ofNullable(this.getComponents())
                .orElse(Collections.emptyList())
                .forEach(this::renderComponent);
        // 渲染后
        this.renderAfter();
        // 重置
        this.reset();
    }

    /**
     * 渲染后
     */
    public void renderAfter() {
        // 绘制边框
        BorderUtil.drawBorderWithBase(
                this,
                new PDRectangle(
                        this.getBeginX(),
                        this.getBeginY() - this.getHeight(),
                        this.getWidth(),
                        this.getHeight()
                )
        );
    }

    /**
     * 虚拟渲染组件
     *
     * @param component 组件
     */
    public void virtualRenderComponent(Component component) {
        // 初始化类型
        component.getContext().setExecutingComponentType(this.getType());
        // 初始化X轴换行起始坐标
        this.getContext().setWrapBeginX(this.getBeginX());
        // 虚拟渲染组件
        component.virtualRender();
    }

    /**
     * 渲染组件
     *
     * @param component 组件
     */
    public void renderComponent(Component component) {
        // 初始化类型
        component.getContext().setExecutingComponentType(this.getType());
        // 初始化X轴换行起始坐标
        this.getContext().setWrapBeginX(this.getBeginX());
        // 渲染组件
        component.render();
    }

    /**
     * 重置
     */
    public void reset() {
        // 重置光标
        this.getContext().getCursor().reset(this.getBeginX(), this.getBeginY() - this.getHeight());
        // 重置当前执行组件类型
        this.getContext().resetExecutingComponentType(this.getType());
        // 重置换行宽度
        this.getContext().setWrapWidth(this.getContext().getPage().getWithoutMarginWidth());
        // 重置换行起始坐标
        this.getContext().setWrapBeginX(this.getContext().getPage().getMarginLeft());
    }

    /**
     * 获取Y轴起始坐标偏移量
     *
     * @return 返回便宜量
     */
    public float getBeginYOffset() {
        // 定义偏移量
        float offset = 0F;
        // 组件存在
        if (!this.getComponents().isEmpty()) {
            // 获取第一个组件
            Component component = this.getComponents().get(0);
            // 初始化基础参数
            component.getBase().initBase();
            // 匹配类型
            switch (component.getType()) {
                // 文本域
                case TEXTAREA: {
                    offset = Optional.ofNullable(((Textarea) component).getFontSize()).orElse(this.getContext().getPage().getFontSize());
                    break;
                }
                // 自定义
                case CUSTOM: {
                    // TODO 待添加自定义类型逻辑
                }
            }
        }
        // 返回偏移量
        return offset;
    }
}
