package action;

public abstract class Action {
    /**
     * Executes this Action with the given parameters.
     * @param params The parameters to pass into the Action.
     * @return the output of the action
     */
    public abstract Object execute(Object... params);
}