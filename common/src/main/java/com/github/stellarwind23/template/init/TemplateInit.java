package com.github.stellarwind23.template.init;

import com.github.stellarwind23.template.content.TemplateBlocks;
import com.github.stellarwind23.template.content.TemplateItems;
import com.github.stellarwind23.template.content.TemplateTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TemplateInit {

    public static final String MOD_ID = "template";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        // Write common init code here.
        TemplateTab.init();
        TemplateBlocks.init();
        TemplateItems.init();
    }
}
