package loader;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import model.*;

public class XMLLoader {
    private final String xmlFilePath;

    public XMLLoader(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public List<Scenario> loadScenarios() {
        List<Scenario> scenarios = new ArrayList<>();
        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList scenarioNodes = doc.getElementsByTagName("scenario");
            for (int i = 0; i < scenarioNodes.getLength(); i++) {
                Node scenarioNode = scenarioNodes.item(i);
                if (scenarioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element scenarioElement = (Element) scenarioNode;
                    Scenario scenario = new Scenario();
                    scenario.setStory(scenarioElement.getElementsByTagName("story").item(0).getTextContent());
                    scenario.setChoices(loadChoices(scenarioElement.getElementsByTagName("choices").item(0)));
                    scenarios.add(scenario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenarios;
    }

    private List<Choice> loadChoices(Node choicesNode) {
        List<Choice> choices = new ArrayList<>();
        NodeList choiceNodes = choicesNode.getChildNodes();
        for (int i = 0; i < choiceNodes.getLength(); i++) {
            Node choiceNode = choiceNodes.item(i);
            if (choiceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element choiceElement = (Element) choiceNode;
                Choice choice = new Choice();
                choice.setId(choiceElement.getAttribute("id"));
                choice.setAction(choiceElement.getElementsByTagName("action").item(0).getTextContent());
                choice.setDialogues(loadDialogues(choiceElement.getElementsByTagName("dialogues").item(0)));
                Node subchoicesNode = choiceElement.getElementsByTagName("subchoices").item(0);
                if (subchoicesNode != null) {
                    choice.setSubChoices(loadChoices(subchoicesNode));
                }
                choice.setEffects(loadEffects(choiceElement.getElementsByTagName("effect")));
                choices.add(choice);
            }
        }
        return choices;
    }

    private List<Dialogue> loadDialogues(Node dialoguesNode) {
        List<Dialogue> dialogues = new ArrayList<>();
        NodeList dialogueNodes = dialoguesNode.getChildNodes();
        for (int i = 0; i < dialogueNodes.getLength(); i++) {
            Node dialogueNode = dialogueNodes.item(i);
            if (dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                Element dialogueElement = (Element) dialogueNode;
                Dialogue dialogue = new Dialogue();
                dialogue.setCharacter(dialogueElement.getElementsByTagName("character").item(0).getTextContent());
                NodeList textNodes = dialogueElement.getElementsByTagName("text");
                List<String> texts = new ArrayList<>();
                for (int j = 0; j < textNodes.getLength(); j++) {
                    texts.add(textNodes.item(j).getTextContent());
                }
                dialogue.setTexts(texts);
                dialogues.add(dialogue);
            }
        }
        return dialogues;
    }

    private List<Effect> loadEffects(NodeList effectNodes) {
        List<Effect> effects = new ArrayList<>();
        for (int i = 0; i < effectNodes.getLength(); i++) {
            Node effectNode = effectNodes.item(i);
            if (effectNode.getNodeType() == Node.ELEMENT_NODE) {
                Element effectElement = (Element) effectNode;
                Effect effect = new Effect();
                NodeList itemNodes = effectElement.getElementsByTagName("item");
                List<String> items = new ArrayList<>();
                for (int j = 0; j < itemNodes.getLength(); j++) {
                    items.add(itemNodes.item(j).getTextContent());
                }
                effect.setItems(items);
                effect.setReputation(Double.parseDouble(effectElement.getElementsByTagName("rep").item(0).getTextContent()));
                effect.setCheckpoint(Integer.parseInt(effectElement.getElementsByTagName("checkpoint").item(0).getTextContent()));
                effects.add(effect);
            }
        }
        return effects;
    }
}
