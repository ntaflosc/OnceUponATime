package loader;

import model.Choice;
import model.Dialogue;
import model.Effect;
import model.Scenario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLLoader {
    public List<Scenario> loadScenarios(String filename) {
        List<Scenario> scenarios = new ArrayList<>();

        try {
            // Initialize and parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filename);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("scenario");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Scenario scenario = new Scenario();

                    NodeList dialogueNodes = element.getElementsByTagName("dialogue");
                    for (int j = 0; j < dialogueNodes.getLength(); j++) {
                        scenario.getDialogues().add(new Dialogue(dialogueNodes.item(j).getTextContent()));
                    }

                    NodeList choiceNodes = element.getElementsByTagName("choice");
                    for (int j = 0; j < choiceNodes.getLength(); j++) {
                        Element choiceElement = (Element) choiceNodes.item(j);
                        Choice choice = new Choice(choiceElement.getAttribute("id"));
                        choice.setCheckpoint(Integer.parseInt(choiceElement.getAttribute("checkpoint")));

                        NodeList choiceDialogueNodes = choiceElement.getElementsByTagName("dialogue");
                        for (int k = 0; k < choiceDialogueNodes.getLength(); k++) {
                            choice.getDialogues().add(new Dialogue(choiceDialogueNodes.item(k).getTextContent()));
                        }

                        if (choiceElement.getElementsByTagName("action").getLength() > 0) {
                            choice.setAction(choiceElement.getElementsByTagName("action").item(0).getTextContent());
                        }

                        if (choiceElement.getElementsByTagName("effect").getLength() > 0) {
                            List<Effect> effects = new ArrayList<>();
                            NodeList effectNodes = choiceElement.getElementsByTagName("effect");
                            for (int k = 0; k < effectNodes.getLength(); k++) {
                                effects.add(new Effect(effectNodes.item(k).getTextContent()));
                            }
                            choice.setEffects(effects);
                        }

                        scenario.getChoices().add(choice);
                    }
                    scenarios.add(scenario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
