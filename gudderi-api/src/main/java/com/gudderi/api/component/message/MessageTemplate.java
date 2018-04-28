package com.gudderi.api.component.message;

import com.gudderi.api.enums.InformationType;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

public class MessageTemplate {
    private TemplateEngine templateEngine;

    public MessageTemplate(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String applyTemplate(InformationType informationType, Map<String, Object> props) {
        Context context = new Context(Locale.getDefault(), props);
        return templateEngine.process(informationType.getTemplateName(), context);
    }
}
