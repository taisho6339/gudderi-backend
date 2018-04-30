package com.gudderi.core.component.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gudderi.core.enums.InformationType;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MessageTemplate {
    private TemplateEngine templateEngine;
    private Gson gson;

    public MessageTemplate(TemplateEngine templateEngine, Gson gson) {
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    public String applyTemplate(InformationType informationType, Map<String, Object> props) {
        Context context = new Context(Locale.getDefault(), props);
        return templateEngine.process(informationType.getTemplateName(), context);
    }

    public String applyTemplate(InformationType informationType, String parametersText) {
        Map<String, Object> params = gson.fromJson(parametersText, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return applyTemplate(informationType, params);
    }
}
