package models;

public class SingleAnswerProblem extends Problem {

    private String question;
    private String answer;

    public SingleAnswerProblem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
