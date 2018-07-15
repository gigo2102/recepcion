package me.tomassetti.handlers;

import me.tomassetti.Validable;
import me.tomassetti.model.Model;


public class EmptyPayload implements Validable {
    @Override
    public Object[] validate(Model model) {
        return new String[0];
    }
}
