package homework_library_v5_io.common.utils.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class XmlDomUtils {
    private XmlDomUtils() {
    }

    public static Document getDocument(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    public static Element getOneElement(Document doc, String tagName) {
        return (Element) doc.getElementsByTagName(tagName).item(0);
    }

    public static String getOneElementTextContent(Element elementSource, String tagName) {
        NodeList elementsByTagName = elementSource.getElementsByTagName(tagName);
        return elementsByTagName.item(0).getTextContent();

    }
}
