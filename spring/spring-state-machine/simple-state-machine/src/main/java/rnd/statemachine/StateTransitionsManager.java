package rnd.statemachine;

public interface StateTransitionsManager {

  ProcessData processEvent(ProcessData data) throws ProcessException;
}
