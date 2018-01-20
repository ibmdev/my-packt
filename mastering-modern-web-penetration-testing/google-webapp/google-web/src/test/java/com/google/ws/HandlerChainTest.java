package com.google.ws;

import org.junit.Test;
import org.w3c.dom.*;
import org.w3c.dom.Node;

import javax.xml.soap.*;

/**
 * Created by ibmdev on 20/01/2018.
 */
public class HandlerChainTest {


    @Test
    public void HandlerSoapMessageTest() {

        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPHeader soapHeader = soapEnvelope.getHeader();
            SOAPBody soapBody = soapEnvelope.getBody();
            // First Child
            Name firstChild = soapEnvelope.createName("First", "ns2", "http://example.com");
            // Second Child
            Name secondChild = soapEnvelope.createName("Second", "ns2", "http://example.com");

            // Third  Child
            Name thirdChild = soapEnvelope.createName("Third", "ns2", "http://example.com");

            // Fourth  Child
            Name fourthChild = soapEnvelope.createName("Fourth", "ns2", "http://example.com");

            soapBody.addBodyElement(firstChild);
            SOAPElement secondElement =  soapBody.addChildElement(secondChild);
            secondElement.addChildElement(thirdChild);
            secondElement.addChildElement(fourthChild);
            System.out.println("\n Before ...................\n");
            //soapMessage.writeTo(System.out);

            NodeList childSoapBody = soapMessage.getSOAPBody().getChildNodes();
            if(childSoapBody !=null && childSoapBody.getLength() >0) {
                for(int item = 0;item <childSoapBody.getLength();item ++) {
                    Node itemNode = childSoapBody.item(item);
                    removeNamespace(soapMessage, itemNode);
                }
            }


            System.out.println("\n After ...................\n");
            //soapMessage.writeTo(System.out);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Node removeNamespace(SOAPMessage soapMessage, Node node) {
        Node currentNode = node;
        try {
            if(currentNode.hasChildNodes()) {
                NodeList nodes  =  currentNode.getChildNodes();
                for(int i=0;i<nodes.getLength();i++) {
                    org.w3c.dom.Node n = nodes.item(i);
                    removeNamespace(soapMessage, n);
                }
            }
            // Processing Node
            System.out.println("Processing Node..... " + currentNode.getNodeName());
            // Sauvegarde
            soapMessage.saveChanges();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            return currentNode;
        }
    }






}
