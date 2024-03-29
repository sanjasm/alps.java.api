package alps.java.api.StandardPASS.PassProcessModelElements;

import alps.java.api.StandardPASS.IPASSProcessModelElement;
import alps.java.api.StandardPASS.PASSProcessModelElement;
import alps.java.api.parsing.*;
import alps.java.api.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Class that represents a data describing component
 */
public class DataDescribingComponent extends PASSProcessModelElement implements IDataDescribingComponent {
    protected IPASSProcessModel model;

    /**
     * Name of the class, needed for parsing
     */
    private final String className = "DataDescribingComponent";

    @Override
    public String getClassName() {
        return className;
    }

    public void setContainedBy(IPASSProcessModel model) {
        if (model != null && model.equals(this.model)) return;
        this.model = model;
        if (model != null) {
            model.addElement(this);
        }
    }

    public IPASSProcessModel getContainedBy() {
        return this.model;
    }

    @Override
    public IParseablePASSProcessModelElement getParsedInstance() {
        return new DataDescribingComponent();
    }

    public DataDescribingComponent() {
    }

    /**
     * Constructor that creates a new fully specified instance of the data describing component class
     *
     * @param model
     * @param labelForID
     * @param comment
     * @param additionalLabel
     * @param additionalAttribute
     */
    public DataDescribingComponent(IPASSProcessModel model, String labelForID, String comment, String additionalLabel, List<IIncompleteTriple> additionalAttribute) {
        super(labelForID, comment, additionalLabel, additionalAttribute);
        setContainedBy(model);
    }

    public DataDescribingComponent(IPASSProcessModel model) {
        super(null, null, null, null);
        setContainedBy(model);
    }


    @Override
    protected Map<String, IParseablePASSProcessModelElement> getDictionaryOfAllAvailableElements() {
        if (model == null) return null;
        Map<String, IPASSProcessModelElement> allElements = model.getAllElements();
        Map<String, IParseablePASSProcessModelElement> allParseableElements = new HashMap<String, IParseablePASSProcessModelElement>();
        for (Map.Entry<String, IPASSProcessModelElement> pair : allElements.entrySet())
            if (pair.getValue() instanceof IParseablePASSProcessModelElement) {
                IParseablePASSProcessModelElement parseable = (IParseablePASSProcessModelElement) pair.getValue();
                allParseableElements.put(pair.getKey(), parseable);
            }
        return allParseableElements;

    }

    @Override
    public Set<IPASSProcessModelElement> getAllConnectedElements(ConnectedElementsSetSpecification specification) {
        Set<IPASSProcessModelElement> baseElements = super.getAllConnectedElements(specification);
        if (specification == ConnectedElementsSetSpecification.ALL) {
            IPASSProcessModel model = getContainedBy();
            if (model != null) baseElements.add(model);
        }
        return baseElements;
    }

    public void removeFromContainer() {
        if (model != null)
            model.removeElement(getModelComponentID());
        model = null;
    }
}

