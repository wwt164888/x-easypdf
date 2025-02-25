package org.dromara.pdf.pdfbox.core.component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.pdf.pdfbox.core.base.*;

import java.io.Closeable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 抽象组件
 *
 * @author xsx
 * @date 2023/9/4
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
public abstract class AbstractComponent extends AbstractBaseFont implements Component, Closeable {

    /**
     * 分页事件列表
     */
    private Set<PagingEvent> pagingEvents;
    /**
     * 自定义分页条件
     */
    private PagingCondition pagingCondition;
    /**
     * 自定义起始X轴坐标
     */
    private Float beginX;
    /**
     * 自定义起始Y轴坐标
     */
    private Float beginY;
    /**
     * X轴结束坐标
     */
    private Float endX;
    /**
     * X轴相对坐标（不影响后续坐标）
     */
    private Float relativeBeginX;
    /**
     * Y轴相对坐标（不影响后续坐标）
     */
    private Float relativeBeginY;
    /**
     * 是否自定义X轴坐标
     */
    private Boolean isCustomX;
    /**
     * 是否自定义Y轴坐标
     */
    private Boolean isCustomY;

    /**
     * 无参构造
     */
    public AbstractComponent() {
        throw new IllegalStateException("can not be initialized");
    }

    /**
     * 有参构造
     *
     * @param page 页面
     */
    public AbstractComponent(Page page) {
        Page lastPage = page.getLastPage();
        this.setContext(lastPage.getContext());
        this.getContext().reset(lastPage);
        this.pagingEvents = new HashSet<>();
    }

    /**
     * 设置自定义X轴坐标
     *
     * @param beginX X轴坐标
     */
    public void setBeginX(Float beginX) {
        this.beginX = beginX;
        this.isCustomX = Objects.nonNull(beginX);
    }

    /**
     * 设置自定义Y轴坐标
     *
     * @param beginY Y轴坐标
     */
    public void setBeginY(Float beginY) {
        this.beginY = beginY;
        this.isCustomY = Objects.nonNull(beginY);
    }

    /**
     * 获取基类
     *
     * @return 返回当前对象
     */
    @Override
    public AbstractBase getBase() {
        return this;
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
     * 初始化基础
     */
    @Override
    public void initBase() {
        // 初始化参数
        super.init(this.getContext().getPage(), false);
    }

    /**
     * 获取下边距
     *
     * @return 返回下边距
     */
    public float getBottom() {
        // 获取页面下边距
        float bottom = this.getContext().getPage().getMarginBottom();
        // 是否有页脚
        if (this.getContext().hasPageFooter()) {
            // 重置下边距
            bottom = bottom + this.getContext().getPageFooter().getHeight();
        }
        // 返回下边距
        return bottom;
    }

    /**
     * 关闭
     */
    @Override
    public void close() {
        super.setContext(null);
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        // 初始化基础
        this.initBase();
        // 初始化当前执行组件类型
        if (Objects.isNull(this.getContext().getExecutingComponentType())) {
            this.getContext().setExecutingComponentType(this.getType());
        }
        // 初始化页眉分页事件
        if (Objects.nonNull(this.getContext().getPageHeader())) {
            if (this.getContext().getExecutingComponentType().isNotPageHeaderOrFooter()) {
                this.getPagingEvents().add(this.getContext().getPageHeader().getPagingEvent());
            }
        }
        // 初始化页脚分页事件
        if (Objects.nonNull(this.getContext().getPageFooter())) {
            if (this.getContext().getExecutingComponentType().isNotPageHeaderOrFooter()) {
                this.getPagingEvents().add(this.getContext().getPageFooter().getPagingEvent());
            }
        }
        // 初始化是否自定义X轴坐标
        if (Objects.isNull(this.getIsCustomX())) {
            this.setIsCustomX(Boolean.FALSE);
        }
        // 初始化是否自定义Y轴坐标
        if (Objects.isNull(this.getIsCustomY())) {
            this.setIsCustomY(Boolean.FALSE);
        }
        // 初始化分页
        if (this.getIsBreak()) {
            this.processBreak();
        }
        // 检查换行
        this.checkWrap();
        // 初始化相对起始X轴坐标
        if (Objects.isNull(this.getRelativeBeginX())) {
            this.setRelativeBeginX(0F);
        }
        // 初始化相对起始Y轴坐标
        if (Objects.isNull(this.getRelativeBeginY())) {
            this.setRelativeBeginY(0F);
        }
    }

    /**
     * 设置X轴坐标
     *
     * @param beginX    X轴坐标
     * @param isCustomX 是否自定义
     */
    protected void setBeginX(Float beginX, boolean isCustomX) {
        this.beginX = beginX;
        this.isCustomX = isCustomX;
    }

    /**
     * 设置Y轴坐标
     *
     * @param beginY    Y轴坐标
     * @param isCustomY 是否自定义
     */
    protected void setBeginY(Float beginY, boolean isCustomY) {
        this.beginY = beginY;
        this.isCustomY = isCustomY;
    }

    /**
     * 检查换行
     */
    protected void checkWrap() {
        // 初始化X轴坐标
        if (Objects.isNull(this.getBeginX())) {
            this.setBeginX(this.getContext().getCursor().getX() + this.getMarginLeft(), Boolean.FALSE);
        }
        // 初始化换行
        if (this.isWrap()) {
            // 重置换行宽度
            this.resetWrapWidth();
            // 重置光标
            this.getContext().getCursor().reset(
                    this.getContext().getWrapBeginX(),
                    this.getContext().getCursor().getY() - this.getContext().getWrapHeight()
            );
            // 重置X轴坐标
            this.setBeginX(this.getContext().getCursor().getX() + this.getMarginLeft(), Boolean.FALSE);
            // 重置是否换行
            this.setIsWrap(Boolean.FALSE);
        }
        // 初始化Y轴坐标
        if (Objects.isNull(this.getBeginY())) {
            this.setBeginY(this.getContext().getCursor().getY() - this.getMarginTop(), Boolean.FALSE);
        }
    }

    /**
     * 换行
     */
    protected void wrap() {
        // 设置X轴起始坐标
        this.setBeginX(null);
        // 设置Y轴起始坐标
        this.setBeginY(null);
        // 设置是否换行
        this.setIsWrap(Boolean.TRUE);
        // 检查换行
        this.checkWrap();
    }

    /**
     * 是否换行
     *
     * @return 返回布尔值，true为是，false为否
     */
    protected boolean isWrap() {
        // 是否手动设置
        if (this.getIsWrap()) {
            return Boolean.TRUE;
        }
        // 换行宽度或X轴起始坐标未初始化
        if (Objects.isNull(this.getContext().getWrapWidth()) || Objects.isNull(this.getBeginX())) {
            return Boolean.FALSE;
        }
        // 换行宽度-X轴起始坐标是否小于0
        return this.getContext().getWrapWidth() - this.getBeginX() < 0;
    }

    /**
     * 是否分页
     *
     * @param component 当前组件
     * @param beginY    Y轴起始坐标
     * @return 返回布尔值，true为是，false为否
     */
    protected boolean isPaging(Component component, float beginY) {
        // 获取分页标识
        boolean flag = (
                this.checkPaging(beginY) ||
                        Optional.ofNullable(this.pagingCondition)
                                .map(condition -> condition.isPaging(component, beginY))
                                .orElse(false)
        ) && this.isPagingComponent();
        // 分页
        if (flag) {
            this.paging();
        }
        // 返回分页标识
        return flag;
    }

    /**
     * 是否分页组件
     *
     * @return 返回布尔值，true为是，false为否
     */
    protected boolean isPagingComponent() {
        return Optional.ofNullable(this.getContext().getExecutingComponentType())
                .map(ComponentType::isNotPageHeaderOrFooter)
                .orElse(Boolean.FALSE);
    }

    /**
     * 执行分页
     */
    protected void paging() {
        // 处理分页
        this.processBreak();
        // 换行
        this.wrap();
        // // 初始化X轴坐标
        // if (Objects.isNull(this.getBeginX())) {
        //     this.setBeginX(this.getContext().getCursor().getX() + this.getMarginLeft(), Boolean.FALSE);
        // }
        // // 初始化Y轴坐标
        // if (Objects.isNull(this.getBeginY())) {
        //     this.setBeginY(this.getContext().getCursor().getY() - this.getMarginTop(), Boolean.FALSE);
        // }
    }

    /**
     * 重置
     */
    protected void reset(ComponentType type) {
        // 重置X轴起始坐标
        this.beginX = null;
        // 重置Y轴起始坐标
        this.beginY = null;
        // 重置是否自定义X轴坐标
        this.isCustomX = null;
        // 重置是否自定义Y轴坐标
        this.isCustomY = null;
        // 重置换行起始坐标
        this.getContext().setWrapBeginX(null);
        // 重置当前执行组件类型
        this.getContext().resetExecutingComponentType(type);
    }

    /**
     * 检查分页
     *
     * @param beginY Y轴起始坐标
     * @return 返回布尔值，true为是，false为否
     */
    protected boolean checkPaging(float beginY) {
        // 获取页面下边距
        float bottom = this.getBottom();
        // Y轴坐标小于下边距+页面下边距
        if (beginY < this.getMarginBottom() + bottom) {
            // 返回true
            return true;
        }
        // 返回false
        return false;
    }

    /**
     * 处理分页
     */
    protected void processBreak() {
        // 非容器
        if (Objects.isNull(this.getContext().getContainerInfo()) || Objects.isNull(this.getContext().getContainerInfo().getPagingEvent())) {
            this.executeBreak();
        } else {
            this.getContext().getContainerInfo().getPagingEvent().before(this);
            this.executeBreak();
            this.getContext().getContainerInfo().getPagingEvent().after(this);
        }
        this.getContext().setWrapHeight(this.getContext().getPage().getFontSize());
    }

    /**
     * 执行分页
     */
    protected void executeBreak() {
        // 分页前事件
        Optional.ofNullable(this.pagingEvents).ifPresent(events -> events.forEach(event -> event.before(this)));
        // 重建页面
        this.getContext().getPage().createSubPage();
        // 重置起始XY轴坐标
        this.resetXY();
        // 初始化
        super.init(this.getContext().getPage(), false);
        // 分页后事件
        Optional.ofNullable(this.pagingEvents).ifPresent(events -> events.forEach(event -> event.after(this)));
    }

    /**
     * 重置起始XY轴坐标
     */
    protected void resetXY() {
        this.beginX = null;
        this.beginY = null;
    }

    /**
     * 重置换行宽度
     */
    protected void resetWrapWidth() {
        // 获取组件类型
        ComponentType type = this.getContext().getExecutingComponentType();
        // 容器组件
        if (Objects.nonNull(type) && ComponentType.CONTAINER == type) {
            this.getContext().resetWrapWidth(this.getContext().getContainerInfo().getWidth());
        } else {
            this.getContext().resetWrapWidth(null);
        }
    }
}
