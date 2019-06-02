package com.lpoo_32.view;

import java.io.IOException;

public interface MenuAbstractFactory {
    Runnable getMenu() throws IOException;
}
