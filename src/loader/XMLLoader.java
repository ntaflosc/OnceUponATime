package loader;

import model.Choice;
import model.Dialogue;
import model.Effect;
import model.Scenario;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLLoader {
    private List<Scenario> scenarios;

    public XMLLoader(String filePath) {
        scenarios = new ArrayList<>();
        loadScenarios(filePath);
    }

    private void loadScenarios(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList scenarioList = doc.getElementsByTagName("scenario");
            for (int i = 0; i < scenarioList.getLength(); i++) {
                Node scenarioNode = scenarioList.item(i);
                if (scenarioNode != null && scenarioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Scenario scenario = new Scenario();

                    NodeList scenarioDetails = scenarioNode.getChildNodes();
                    for (int j = 0; j < scenarioDetails.getLength(); j++) {
                        Node detail = scenarioDetails.item(j);
                        if (detail != null && detail.getNodeType() == Node.ELEMENT_NODE) {
                            switch (detail.getNodeName()) {
                                case "location":
                                    scenario.setLocation(detail.getTextContent());
                                    break;
                                case "checkpoint":
                                    scenario.setCheckpoint(Integer.parseInt(detail.getTextContent()));
                                    break;
                                case "story":
                                    scenario.setStory(detail.getTextContent());
                                    break;
                                case "dialogues":
                                    scenario.setDialogues(loadDialogues(detail.getChildNodes()));
                                    break;
                                case "choices":
                                    scenario.setChoices(loadChoices(detail.getChildNodes()));
                                    break;
                            }
                        }
                    }
                    scenarios.add(scenario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Dialogue> loadDialogues(NodeList dialoguesNodeList) {
        List<Dialogue> dialogues = new ArrayList<>();
        for (int i = 0; i < dialoguesNodeList.getLength(); i++) {
            Node dialogueNode = dialoguesNodeList.item(i);
            if (dialogueNode != null && dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                Dialogue dialogue = new Dialogue();
                NodeList dialogueDetails = dialogueNode.getChildNodes();
                for (int j = 0; j < dialogueDetails.getLength(); j++) {
                    Node detail = dialogueDetails.item(j);
                    if (detail != null && detail.getNodeType() == Node.ELEMENT_NODE) {
                        switch (detail.getNodeName()) {
                            case "character":
                                dialogue.setCharacter(detail.getTextContent());
                                break;
                            case "text":
                                dialogue.setText(detail.getTextContent());
                                break;
                        }
                    }
                }
                dialogues.add(dialogue);
            }
        }
        return dialogues;
    }

    private List<Choice> loadChoices(NodeList choicesNodeList) {
        List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < choicesNodeList.getLength(); i++) {
            Node choiceNode = choicesNodeList.item(i);
            if (choiceNode != null && choiceNode.getNodeType() == Node.ELEMENT_NODE) {
                Choice choice = new Choice();
                NodeList choiceDetails = choiceNode.getChildNodes();
                for (int j = 0; j < choiceDetails.getLength(); j++) {
                    Node detail = choiceDetails.item(j);
                    if (detail != null && detail.getNodeType() == Node.ELEMENT_NODE) {
                        switch (detail.getNodeName()) {
                            case "id":
                                choice.setId(detail.getTextContent());
                                break;
                            case "action":
                                choice.setAction(detail.getTextContent());
                                break;
                            case "dialogues":
                                choice.setDialogues(loadDialogues(detail.getChildNodes()));
                                break;
                            case "effects":
                                choice.setEffects(loadEffects(detail.getChildNodes()));
                                break;
                            case "subChoices":
                                choice.setSubChoices(loadChoices(detail.getChildNodes()));
                                break;
                            case "checkpoint":
                                choice.setCheckpoint(Integer.parseInt(detail.getTextContent()));
                                break;
                        }
                    }
                }
                choices.add(choice);
            }
        }
        return choices;
    }

    private List<Effect> loadEffects(NodeList effectsNodeList) {
        List<Effect> effects = new ArrayList<>();
        for (int i = 0; i < effectsNodeList.getLength(); i++) {
            Node effectNode = effectsNodeList.item(i);
            if (effectNode != null && effectNode.getNodeType() == Node.ELEMENT_NODE) {
                Effect effect = new Effect();
                NodeList effectDetails = effectNode.getChildNodes();
                for (int j = 0; j < effectDetails.getLength(); j++) {
                    Node detail = effectDetails.item(j);
                    if (detail != null && detail.getNodeType() == Node.ELEMENT_NODE) {
                        switch (detail.getNodeName()) {
                            case "items":
                                effect.setItems(loadItems(detail.getChildNodes()));
                                break;
                            case "reputation":
                                effect.setReputation(Double.parseDouble(detail.getTextContent()));
                                break;
                            case "checkpoint":
                                effect.setCheckpoint(Integer.parseInt(detail.getTextContent()));
                                break;
                        }
                    }
                }
                effects.add(effect);
            }
        }
        return effects;
    }

    private List<String> loadItems(NodeList itemsNodeList) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < itemsNodeList.getLength(); i++) {
            Node itemNode = itemsNodeList.item(i);
            if (itemNode != null && itemNode.getNodeType() == Node.ELEMENT_NODE) {
                items.add(itemNode.getTextContent());
            }
        }
        return items;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }
}
