package com.github.appreciated.production;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


/**
 * If you choose the route properly the addon supports back navigation. Also if the Subcontent does not appear in the Menu, the parent route will be highlighted.
 */

@Route(value = "view2/subcontent", layout = MainAppLayout.class)
public class SubContent extends VerticalLayout {

    public SubContent() {
        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
        add(getLabel());
    }

    Paragraph getLabel() {
        Paragraph label = new Paragraph(" SubContent ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ........................................ ");
        label.setWidth("100%");
        return label;
    }

}