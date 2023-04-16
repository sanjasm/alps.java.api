package alps.java.api.StandardPASS.PassProcessModelElements;


import alps.java.api.ALPS.ALPSModelElements.IModelLayer;
import alps.java.api.StandardPASS.IPASSProcessModelElement;
import alps.java.api.StandardPASS.PASSProcessModelElement;
import alps.java.api.parsing.IParseablePASSProcessModelElement;
import alps.java.api.util.IIncompleteTriple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents an InteractionDescriptionComponten
 */
public class InteractionDescribingComponent extends PASSProcessModelElement implements IInteractionDescribingComponent {
    /**
     * Name of the class, needed for parsing
     */
    private final String className = "InteractionDescribingComponent";

    protected IModelLayer layer;

    @Override
    public String getClassName() {
        return className;
    }

    public void setContainedBy(IModelLayer layer) {
        if (layer == null || layer.equals(this.layer)) return;
        this.layer = layer;
        layer.addElement(this);
    }

    public boolean getContainedBy(IModelLayer subject) {
        subject = layer;
        return layer != null;
    }

    @Override
    public IParseablePASSProcessModelElement getParsedInstance() {
        return new InteractionDescribingComponent();
    }

    protected InteractionDescribingComponent() {
    }

    /**
     * @param layer
     * @param labelForID
     * @param comment
     * @param additionalLabel
     * @param additionalAttribute
     */
    public InteractionDescribingComponent(IModelLayer layer, String labelForID, String comment, String additionalLabel, List<IIncompleteTriple> additionalAttribute) {
        super(labelForID, comment, additionalLabel, additionalAttribute);
        setContainedBy(layer);
    }

    public InteractionDescribingComponent(IModelLayer layer) {
        super(null, null, null, null);
        setContainedBy(layer);
    }

    //TODO: out-Parameter
    @Override
    protected Map<String, IParseablePASSProcessModelElement> getDictionaryOfAllAvailableElements() {
        if (!getContainedBy(out IModelLayer layer)) return null;
        if (!layer.getContainedBy(out IPASSProcessModel model)) return null;
        Map<String, IPASSProcessModelElement> allElements = model.getAllElements();
        Map<String, IParseablePASSProcessModelElement> allParseableElements = new HashMap<String, IParseablePASSProcessModelElement>();
        for (Map.Entry<String, IPASSProcessModelElement> pair : allElements.entrySet()) {
            IPASSProcessModelElement value = pair.getValue();
            if (value instanceof IParseablePASSProcessModelElement) {
                IParseablePASSProcessModelElement parseable = (IParseablePASSProcessModelElement) value;
                allParseableElements.put(pair.getKey(), parseable);
            }
        }
        return allParseableElements;
    }

    //TODO: out-Parameter
    @Override
    public Set<IPASSProcessModelElement> getAllConnectedElements(ConnectedElementsSetSpecification specification) {
        Set<IPASSProcessModelElement> baseElements = super.getAllConnectedElements(specification);
        if (specification == ConnectedElementsSetSpecification.ALL)
            if (getContainedBy(out IModelLayer layer)) {
                baseElements.add(layer);
            }

        return baseElements;
    }

    public void removeFromContainer() {
        layer.removeContainedElement(getModelComponentID());
        layer = null;
    }

}

