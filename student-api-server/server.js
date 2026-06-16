const express = require("express");

const app = express();
const port = 3000;

app.use(express.json());

app.use(function (request, response, next) {
  console.log(`${request.method} ${request.url}`);
  next();
});

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

app.get("/students/help", function (request, response){
   response.json({
    message: "학생 API 연습 서버입니다.",
    routes: [
      "GET /students",
      "GET /students/search?minScore=70",
      "GET /students/1",
      "POST /students"
    ]
  });
});

app.get("/students/example", function (request, response){
  response.json({
    "id": 1,
    "name": "예시학생",
    "score": 90
  });
});

// app.get("/students/preview", function (request, response){
//   response.json({
    

//   });
// });

app.get("/students/top", function (request, response) {
  let topStudent = students[0];

  for (let index = 1; index < students.length; index++) {
    if (students[index].score > topStudent.score) {
      topStudent = students[index];
    }
  }

  response.json(topStudent);
});

app.get("/students/search", function (request, response) {
  const minScore = Number(request.query.minScore);

  if (!Number.isFinite(minScore)) {
    response.status(400).json({
      message: "minScore는 숫자여야 합니다.",
    });
    return;
  }

  const filteredStudents = students.filter((student) => {
    return student.score >= minScore;
  });

  response.json(filteredStudents);
});

app.get("/students/:id", function (request, response) {
  const id = Number(request.params.id);

  if (!Number.isInteger(id)) {
    response.status(400).json({
      message: "id는 숫자여야 합니다.",
    });
    return;
  }

  let student = undefined;

  for (let index = 0; index < students.length; index++) {
    if (students[index].id === id) {
      student = students[index];
    }
  }

  if (student === undefined) {
    response.status(404).json({
      message: "학생을 찾을 수 없습니다.",
    });
    return;
  }

  response.json(student);
});

app.post("/students", function (request, response) {
  const body = request.body || {};
  const name = body.name;
  const score = body.score;

  if (
    typeof name !== "string" ||
    name.trim() === "" ||
    typeof score !== "number" ||
    !Number.isFinite(score)
  ) {
    response.status(400).json({
      message: "name과 숫자 score를 입력해야 합니다.",
    });
    return;
  }

  const newStudent = {
    id: students.length + 1,
    name: name.trim(),
    score: score,
  };

  students.push(newStudent);

  response.status(201).json(newStudent);
});

app.put("/students/:id", function (request, response) {
  const id = Number(request.params.id);

  if (!Number.isInteger(id)) {
    response.status(400).json({
      message: "id는 숫자여야 합니다.",
    });
    return;
  }

  let studentIndex = -1;

  for (let index = 0; index < students.length; index++) {
    if (students[index].id === id) {
      studentIndex = index;
    }
  }

  if (studentIndex === -1) {
    response.status(404).json({
      message: "학생을 찾을 수 없습니다.",
    });
    return;
  }

  const body = request.body || {};
  const name = body.name;
  const score = body.score;

  if (
    typeof name !== "string" ||
    name.trim() === "" ||
    typeof score !== "number" ||
    !Number.isFinite(score)
  ) {
    response.status(400).json({
      message: "name과 숫자 score를 입력해야 합니다.",
    });
    return;
  }

  const updatedStudent = {
    id: id,
    name: name.trim(),
    score: score,
  };

  students[studentIndex] = updatedStudent;

  response.json(updatedStudent);
});

app.patch("/students/:id", function (request, response) {
  const id = Number(request.params.id);

  if (!Number.isInteger(id)) {
    response.status(400).json({
      message: "id는 숫자여야 합니다.",
    });
    return;
  }

  let student = undefined;

  for (let index = 0; index < students.length; index++) {
    if (students[index].id === id) {
      student = students[index];
    }
  }

  if (student === undefined) {
    response.status(404).json({
      message: "학생을 찾을 수 없습니다.",
    });
    return;
  }

  const body = request.body || {};
  const name = body.name;
  const score = body.score;
  const hasName = name !== undefined;
  const hasScore = score !== undefined;

  if (!hasName && !hasScore) {
    response.status(400).json({
      message: "수정할 name 또는 score를 입력해야 합니다.",
    });
    return;
  }

  if (hasName && (typeof name !== "string" || name.trim() === "")) {
    response.status(400).json({
      message: "name은 비어 있지 않은 문자열이어야 합니다.",
    });
    return;
  }

  if (hasScore && (typeof score !== "number" || !Number.isFinite(score))) {
    response.status(400).json({
      message: "score는 숫자여야 합니다.",
    });
    return;
  }

  if (hasName) {
    student.name = name.trim();
  }

  if (hasScore) {
    student.score = score;
  }

  response.json(student);
});

app.delete("/students/:id", function (request, response) {
  const id = Number(request.params.id);

  if (!Number.isInteger(id)) {
    response.status(400).json({
      message: "id는 숫자여야 합니다.",
    });
    return;
  }

  let studentIndex = -1;

  for (let index = 0; index < students.length; index++) {
    if (students[index].id === id) {
      studentIndex = index;
    }
  }

  if (studentIndex === -1) {
    response.status(404).json({
      message: "학생을 찾을 수 없습니다.",
    });
    return;
  }

  const deletedStudents = students.splice(studentIndex, 1);

  response.json({
    message: "학생을 삭제했습니다.",
    student: deletedStudents[0],
  });
});

app.use(function (request, response) {
  response.status(404).json({
    message: "요청한 API를 찾을 수 없습니다.",
  });
});

app.listen(port, function () {
  console.log(`API 서버가 <http://localhost>:${port} 에서 실행 중입니다.`);
});