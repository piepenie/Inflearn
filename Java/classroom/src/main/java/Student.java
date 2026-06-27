public class Student {
    private int id;
    private String name;
    private int score;

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void changeScore(int score) {
        if(score < 0 || score > 100) {
            throw new IllegalArgumentException("점수는 0보다 작거나 100보다 크면 안됩니다.");
        }
        this.score = score;
    }

    @Override
    public String toString() {
        return id + " / " + name + " / " + score + "점";
    }


}
