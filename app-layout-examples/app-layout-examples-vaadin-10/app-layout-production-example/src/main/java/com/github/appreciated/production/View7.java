package com.github.appreciated.production;

import com.vaadin.flow.router.Route;

@Route(value = "view7", layout = MainAppLayout.class)
public class View7 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}