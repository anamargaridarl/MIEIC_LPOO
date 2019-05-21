package com.lpoo_32.view;

import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

import java.io.IOException;

public interface KeyboardAnalyzer {
    EventType processKey() throws IOException, HungerOVF, ThirstOVF;
}
