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
import java.util.ArrayList;
import java.util.List;

public class XMLLoader {
    public List<Scenario> loadScenarios(String filename) {
        List<Scenario> scenarios = new ArrayList<>();

        try {
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
                    scenario.setStory(element.getElementsByTagName("story").item(0).getTextContent());

                    NodeList choiceNodes = element.getElementsByTagName("choice");
                    for (int j = 0; j < choiceNodes.getLength(); j++) {
                        Element choiceElement = (Element) choiceNodes.item(j);
                        Choice choice = parseChoice(choiceElement);
                        scenario.getChoices().add(choice);
                    }

                    scenarios.add(scenario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    private Choice parseChoice(Element choiceElement) {
        Choice choice = new Choice(choiceElement.getAttribute("id"));
        choice.setAction(choiceElement.getElementsByTagName("action").item(0).getTextContent());

        NodeList dialogueNodes = choiceElement.getElementsByTagName("dialogue");
        for (int k = 0; k < dialogueNodes.getLength(); k++) {
            Element dialogueElement = (Element) dialogueNodes.item(k);
            Dialogue dialogue = new Dialogue(
                    dialogueElement.getElementsByTagName("character").item(0).getTextContent(),
                    dialogueElement.getElementsByTagName("text").item(0).getTextContent()
            );
            choice.getDialogues().add(dialogue);
        }

        if (choiceElement.getElementsByTagName("effect").getLength() > 0) {
            List<Effect> effects = new ArrayList<>();
            NodeList effectNodes = choiceElement.getElementsByTagName("effect");
            for (int k = 0; k < effectNodes.getLength(); k++) {
                Element effectElement = (Element) effectNodes.item(k);
                Effect effect = new Effect();
                if (effectElement.getElementsByTagName("item").getLength() > 0) {
                    effect.setItem(effectElement.getElementsByTagName("item").item(0).getTextContent());
                }
                if (effectElement.getElementsByTagName("rep").getLength() > 0) {
                    effect.setRep(Double.parseDouble(effectElement.getElementsByTagName("rep").item(0).getTextContent()));
                }
                if (effectElement.getElementsByTagName("checkpoint").getLength() > 0) {
                    effect.setCheckpoint(Integer.parseInt(effectElement.getElementsByTagName("checkpoint").item(0).getTextContent()));
                }
                effects.add(effect);
            }
            choice.setEffects(effects);
        }

        NodeList subchoiceNodes = choiceElement.getElementsByTagName("choice");
        for (int k = 0; k < subchoiceNodes.getLength(); k++) {
            Element subchoiceElement = (Element) subchoiceNodes.item(k);
            Choice subchoice = parseChoice(subchoiceElement);
            choice.getSubchoices().add(subchoice);
        }

        return choice;
    }
}
