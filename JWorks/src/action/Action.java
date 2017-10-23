package action;

public abstract class Action {
    /**
     * Executes this Action with the given parameters.
     * @param params The parameters to pass into the Action.
     */
    public abstract void Execute(ActionParameters params);
}