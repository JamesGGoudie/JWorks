package models;

public class SingleAnswerProblem extends Problem {

    public SingleAnswerProblem(String question, String answer) {
        super();
        problemProperty.set(question);
        answerProperty.set(answer);
    }

    @Override
    public String getProblem() {
        return problemProperty.get();
    }

    @Override
    public String getAnswer() {
        return answerProperty.get();
    }

    public void setQuestion(String question) {
        problemProperty.set(question);
    }

    public void setAnswer(String answer) {
        answerProperty.set(answer);
    }
}
