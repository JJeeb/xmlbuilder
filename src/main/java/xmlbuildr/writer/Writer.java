package xmlbuildr.writer;

import xmlbuildr.xml.Attribute;
import xmlbuildr.xml.Element;

public class Writer {

    public String write(Element element) {
        StringBuilder xml = new StringBuilder("<").append(element.getName());

        if(! element.getAttributes().isEmpty()){
            for (Attribute attribute : element.getAttributes()) {
                xml.append(" ").append(attribute.getName()).append("=\"").append(attribute.getValue()).append("\"");
            }
        }
        
        if (element.getChildren().isEmpty() && ! element.hasText()) {
            xml.append("/>");
        } else {
            xml.append(">");
            for (Element child : element.getChildren()) {
                xml.append(write(child));
            }
            if(element.hasText()){
                xml.append(element.getText());
            }
            xml.append("</" + element.getName() + ">");
        }

        return xml.toString();
    }

}
