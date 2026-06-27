public class Student {
    private final int id;
    private final String name;
    private int score;

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        changeScore(score);
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

    public void updateScore(int score) {
        changeScore(score);
        this.score = score;
    }

    public PassStatus getPassStatus() {
        if (this.score >= 60) {
            return PassStatus.PASSED;
        } else {
            return PassStatus.FAILED;
        }
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
