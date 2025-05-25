package com.cantuaria.discover;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class XmlResourceReader {


    static Set<String> codigos = new HashSet<>();
    static Map<String,Element> mapeamentos = new HashMap<>();

    public static void juntarCodigos() throws IOException {
        String s = new String(XmlResourceReader.class.getResourceAsStream("/teste.txt").readAllBytes());
        String[] split = s.replace("\n","").split("\\|");

        for (String string : split) {
            codigos.add(string);
        }


        codigos.stream().sorted().toList().forEach(
                s1 -> System.out.println(s1)
        );



    }

    public static void main(String[] args) {
        try {
            juntarCodigos();

            // 1. Carrega o arquivo XML do resources
            String xmlFileName = "/descriptor.xml"; // Substitua pelo seu arquivo
            Document document = loadXmlFromResources(xmlFileName);

            // 2. Exemplo de iteração pelos elementos
            iterateXmlElements(document);

            // 3. Exemplo de busca específica
            searchElementByTag(document, "registro");

            int total = 0;
            for (String key : mapeamentos.keySet().stream().sorted().toList()) {
                int qnt = contarTagsCampoDiretas(mapeamentos.get(key));
                System.out.println(key+"=>"+qnt);
                total += qnt;
            }
            System.out.println("Total=>"+total);

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega um arquivo XML do diretório resources
     * @param fileName Nome do arquivo no resources
     * @return Document objeto DOM
     */
    public static Document loadXmlFromResources(String fileName) throws Exception {
        // Obtém o InputStream do arquivo no classpath
        InputStream inputStream = XmlResourceReader.class
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Arquivo não encontrado: " + fileName);
        }

        // Configura o parser de XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parseia o XML e retorna o Document
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();

        inputStream.close();
        return document;
    }

    /**
     * Itera por todos os elementos do XML
     * @param document Documento XML
     */
    public static void iterateXmlElements(Document document) {
        System.out.println("Iterando por todos os elementos:");

        // Obtém o elemento raiz
        Element root = document.getDocumentElement();
        System.out.println("Elemento raiz: " + root.getNodeName());

        // Obtém todos os elementos filhos
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            // Ignora nós de texto (quebra de linha, etc)
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Elemento: " + element.getNodeName());

                // Se o elemento tiver texto
                if (element.getTextContent() != null && !element.getTextContent().trim().isEmpty()) {
                    System.out.println("  Valor: " + element.getTextContent().trim());
                }

                // Se tiver atributos
                if (element.hasAttributes()) {
                    System.out.println("  Atributos:");
                    for (int j = 0; j < element.getAttributes().getLength(); j++) {
                        Node attr = element.getAttributes().item(j);
                        System.out.println("    " + attr.getNodeName() + " = " + attr.getNodeValue());
                    }
                }
            }
        }
    }

    /**
     * Busca elementos por tag name
     * @param document Documento XML
     * @param tagName Nome da tag a ser buscada
     */
    public static void searchElementByTag(Document document, String tagName) {
        System.out.println("\nBuscando elementos com tag: " + tagName);

        NodeList nodeList = document.getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getAttribute("id");
                if(codigos.contains(id)){
                    mapeamentos.put(id, element);
                }

            }
        }
    }

    /**
     * Gera o XPath aproximado do elemento
     * @param element Elemento XML
     * @return String com caminho XPath
     */
    private static String getXPath(Element element) {
        StringBuilder path = new StringBuilder();
        Node current = element;

        while (current != null && current.getNodeType() == Node.ELEMENT_NODE) {
            path.insert(0, "/" + current.getNodeName());
            current = current.getParentNode();
        }

        return path.toString();
    }

    /**
     * Versão alternativa que conta apenas filhos diretos (não recursiva)
     */
    public static int contarTagsCampoDiretas(Element elemento) {
        int count = 0;
        NodeList childNodes = elemento.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE &&
                    "campo".equals(node.getNodeName())) {
                count++;
            }
        }
        return count;
    }

}
