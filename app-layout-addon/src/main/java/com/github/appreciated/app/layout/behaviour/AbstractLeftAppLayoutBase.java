package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.design.Styles;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * The {@link AbstractLeftAppLayoutBase} is the supposed to be the base of any {@link AppLayoutElementBase} with a "Left Behaviour".
 */
public abstract class AbstractLeftAppLayoutBase extends AppLayout {

    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private final HorizontalLayout titleWrapper = new HorizontalLayout();
    private final Div menuElements;
    private final Div contentHolder;
    @Id("toggle")
    private PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    private Div appBarElements;

    @Id("drawer")
    private AppDrawer drawer;
    private Component title;
    private boolean isMenuVisible = true;
    private HasElement content;
    private Component container;

    AbstractLeftAppLayoutBase() {
        getStyle().set("width", "100%")
                .set("height", "100%");
        getClassNames().addAll(asList("app-layout-behaviour-" + getStyleName(), Styles.APP_LAYOUT));
        HorizontalLayout appBarContentHolder = new HorizontalLayout(titleWrapper, appBarElementWrapper);
        appBarContentHolder.setSizeFull();
        appBarContentHolder.setSpacing(false);
        appBarContentHolder.getElement().setAttribute("slot", "app-bar-content");

        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.getStyle().set("flex", "1 1");
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);


        menuElements = new Div();
        menuElements.setHeight("100%");
        menuElements.getElement().setAttribute("slot", "drawer-content");
        contentHolder = new Div();
        contentHolder.setHeight("100%");
        contentHolder.setWidth("100%");
        contentHolder.getElement().setAttribute("slot", "application-content");

        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        titleWrapper.setPadding(false);
        titleWrapper.setMargin(false);
        titleWrapper.getElement().getStyle().set("flex", "1 1 100px").set("overflow", "hidden");
        titleWrapper.setWidth("0px");
        getElement().getClassList().add("app-layout");

        getElement().appendChild(appBarContentHolder.getElement(), menuElements.getElement(), contentHolder.getElement());
    }

    public abstract String getStyleName();

    @Override
    public AppDrawer getDrawer() {
        return drawer;
    }

    public HorizontalLayout getAppBarElementWrapper() {
        return appBarElementWrapper;
    }

    public Component getTitleLabel() {
        return title;
    }

    public void setIconComponent(Component appBarIconComponent) {
        titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    @Override
    public void setAppLayoutContent(HasElement content) {
        this.contentHolder.getElement().removeAllChildren();
        if (content != null) {
            this.contentHolder.getElement().appendChild(content.getElement());
            this.content = content;
        }
    }

    @Override
    public void setAppBar(Component component) {
        appBarElementContainer.removeAll();
        appBarElementContainer.add(component);
    }

    @Override
    public void setAppMenu(Component container) {
        this.container = container;
        menuElements.removeAll();
        menuElements.add(container);
    }

    @Override
    public Component getTitleComponent() {
        return title;
    }

    public void setTitleComponent(Component component) {
        titleWrapper.replace(this.title, component);
        this.title = component;
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    @Override
    public void setUpNavigation(boolean visible) {
        paperIconButton.setIcon(visible ? "arrow-back" : "menu");
    }

    @Override
    public HasElement getContentElement() {
        return content;
    }


    @Override
    public void init() {
        /**
         * if no menu elements were added hide the button and menu
         */
        if (container == null) {
            setMenuVisible(false);
        }
    }

    /**
     * Weather the menu is currently hidden or not.
     *
     * @return false of the menu is currently hidden.
     */
    public boolean isMenuVisible() {
        return isMenuVisible;
    }

    /**
     * Hiding the menu will hide all menu elements also on the client side.
     * </br> Note that you still have to make sure that an unauthorized user is unable to access the paths available in the menu!
     *
     * @param isMenuVisible
     */
    public void setMenuVisible(boolean isMenuVisible) {
        if (isMenuVisible != this.isMenuVisible) { // only do something if the state was changed
            this.isMenuVisible = isMenuVisible;
            if (isMenuVisible) {
                if (menuElements.getChildren().count() == 0 && container != null) { // if the container is empty add the component
                    menuElements.add(container);
                }
                drawer.getElement().getStyle().remove("display");
                paperIconButton.getElement().getStyle().remove("display");
                getElement().getStyle().remove("--app-layout-drawer-width");
                getElement().getStyle().remove("--app-layout-drawer-small-width");
            } else {
                drawer.getElement().getStyle().set("display", "none");
                menuElements.removeAll();
                paperIconButton.getElement().getStyle().set("display", "none");
                getElement().getStyle().set("--app-layout-drawer-width", "0px");
                getElement().getStyle().set("--app-layout-drawer-small-width", "0px");
            }
        }
    }
}
