package com.github.appreciated.app.layout.component.menu.top.builder;

import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.menu.left.LeftMenuComponentWrapper;
import com.github.appreciated.app.layout.component.menu.top.TopMenuComponent;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * A Builder to build {@link LeftMenuComponentWrapper} this builder is meant to be used in combination with the {@link AppLayoutBuilder}
 */
public class TopAppMenuBuilder {

    private List<Component> components = new ArrayList<>();

    private TopAppMenuBuilder() {
    }

    public static TopAppMenuBuilder get() {
        return new TopAppMenuBuilder();
    }

    public TopAppMenuBuilder add(Component element) {
        return addToSection(element, Section.DEFAULT);
    }

    public TopAppMenuBuilder addToSection(Component element, Section section) {
        components.add(element);
        return this;
    }

    public Component build() {
        TopMenuComponent menu = new TopMenuComponent();
        menu.add(components.toArray(new Component[0]));
        return menu;
    }
}
