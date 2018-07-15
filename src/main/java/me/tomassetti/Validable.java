package me.tomassetti;

import me.tomassetti.model.Model;

public interface Validable {
	Object[] validate(Model model);
}
