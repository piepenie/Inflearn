const express = require("express");

const app = express();
const port = 3000;

const students = [
  { id: 1, name: "김하나", score: 85 },
  { id: 2, name: "이준호", score: 58 },
  { id: 3, name: "박서연", score: 92 },
  { id: 4, name: "최민수", score: 76 },
];


app.get("/health", function (request, response) {
  response.json({
    status: "ok",
    message: "서버가 실행 중입니다.",
  });
});

app.get("/hello", function (request, response) {
  response.json({
    status: "ok",
    message: "저는 김파니입니다. 만나서 반가워요",
  });
});

app.listen(port, function () {
  console.log(`API 서버가 <http://localhost>:${port} 에서 실행 중입니다.`);
});

app.get("/students", function (request, response) {
  response.json(students);
});

app.get("/students/names", function (request, response) {
  const studentNames = students.map((student) => {
    return student.name;
  });

  response.json(studentNames);
});

app.get("/students/passed", function (request, response) {
  const passedStudents = students.filter((student) => {
    return student.score >= 60;
  });

  response.json(passedStudents);
});