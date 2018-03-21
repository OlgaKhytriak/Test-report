package com.epam.task3.data.utils.parserJAXB;

import java.io.File;

public class Object2Xml {
	private JAXBContextProcessor jAXBContextProcessor;

	public Object2Xml(JAXBContextProcessor jAXBContextProcessor) {
	this.jAXBContextProcessor = jAXBContextProcessor;
    }
	public <T> T load(File file) {
		Object object = (T) new Object();
		object = jAXBContextProcessor.convertXMLToObject(object.getClass(), file);
		return (T) object;
	}

	public <T> void save(File file, T object) {
		jAXBContextProcessor.convertObjectToXML(file, object);
	}

}
