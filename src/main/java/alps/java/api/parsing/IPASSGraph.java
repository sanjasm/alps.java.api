package alps.java.api.parsing;


import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import java.net.URI;

/**
 * This is an interface for a graph used by each model to back up data in form of triples.
 * The graph is used mainly for exporting, but could also be used for remote control of the model.
 * It is always kept up to date when something inside the model changes.
 */
public interface IPASSGraph {

    String getBaseURI();

    boolean containsNonBaseUri(String input);

    /**
     * Adds a triple to the triple store this graph contains
     *
     * @param t the triple
     */
    void addTriple(Triple t);

    /**
     * Removes a triple from the triple store this graph contains
     *
     * @param t the triple
     */
    void removeTriple(Triple t);

    /**
     * Creates a new Uri node inside the graph
     *
     * @return The new Uri node
     */
    Node createUriNode();

    /**
     * Creates a new Uri node from an Uri
     *
     * @param uri The correctly formatted uri
     * @return The new Uri node
     */
    Node createUriNode(URI uri);

    /**
     * Creates a new Uri node from an Uri
     *
     * @param uri The correctly formatted uri
     * @return The new Uri node
     */

    /**
     * Creates a new Uri node from a string name
     * This name should not be an uri/url (start with http: ...)
     * For this use {@link #createUriNode(String)}.
     *
     * @param name The name
     * @return The new Uri node
     */
    Node createUriNode(String name);

    Literal createLiteralNode(String literal);

    Literal createLiteralNode(String literal, URI datadef);

    Literal createLiteralNode(String literal, String langspec);


    /**
     * Registers a component to the graph.
     * When a triple is changed, the affected component will be notified and can react
     * to the change
     *
     * @param element the element that is registered
     */
    void register(IGraphCallback element);

    /**
     * Deregisteres a component previously registered via {@link #register(IGraphCallback)}
     *
     * @param element the element that is de-registered
     */
    void unregister(IGraphCallback element);

    /**
     * Should be called when a modelComponentID is changed.
     * The model component ids are like primary keys in a database, and many triples must be updated as result.
     * Also, the other components inside the model will be notified about the change when they are registered.
     *
     * @param oldID the old id
     * @param newID the new id
     */
    void modelComponentIDChanged(String oldID, String newID);

    /**
     * Exports the current graph as owl to the specified filename.
     *
     * @param filepath
     */
    void exportTo(String filepath);

    void changeBaseURI(String newUri);
}
