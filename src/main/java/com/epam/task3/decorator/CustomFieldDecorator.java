package com.epam.task3.decorator;

import java.lang.reflect.Field;

import com.epam.task3.decorator.anotation.ElementName;
import com.epam.task3.decorator.anotation.PageName;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import com.epam.task3.decorator.elements.Element;
import com.epam.task3.decorator.elements.CustomElementFactory;

public class CustomFieldDecorator extends DefaultFieldDecorator {
    private static final Logger LOG = Logger.getLogger(CustomFieldDecorator.class);
    private CustomElementFactory elementFactory = new CustomElementFactory();

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {

        if (Element.class.isAssignableFrom(field.getType())) {
            WebElement wrappedElement = proxyForLocator(loader, factory.createLocator(field));
            Element newElement = elementFactory.create((Class<? extends Element>) field.getType(), wrappedElement);

            ElementName ann = field.getAnnotation(ElementName.class);
            String pageName = field.getDeclaringClass().getAnnotation(PageName.class).value();
            if (null != ann) {
                newElement.setName(ann.value() + "; " + pageName);
            }
            return newElement;
        }

        return super.decorate(loader, field);
    }

}
