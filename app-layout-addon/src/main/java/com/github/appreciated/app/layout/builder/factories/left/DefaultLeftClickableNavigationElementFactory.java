package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.ClickableNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.component.button.NavigationBadgeIconButton;
import com.vaadin.flow.component.HasElement;

public class DefaultLeftClickableNavigationElementFactory implements ComponentFactory<HasElement, ClickableNavigationElement> {
    @Override
    public HasElement get(ClickableNavigationElement element) {
        NavigationBadgeIconButton button = new NavigationBadgeIconButton(element.getName(), element.getIcon(), null);
        element.setComponent(button);
        button.setClickListener(appMenuIconItemClickEvent -> element.getListener().onComponentEvent(appMenuIconItemClickEvent));
        return button;
    }
}
